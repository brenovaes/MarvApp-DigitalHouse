package com.digitalhouse.br.marvelapp.ui.home

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.MyPreferences
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.crH
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.HeroDay
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.Suggestions
import com.digitalhouse.br.marvelapp.service.*
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.favoritos.FavoritoActivity
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.perfil.PerfilActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_principal.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class HomeActivity : AppCompatActivity(),
//    PopularAdapter.OnPopularClickListener,
    SugestoesAdapter.OnSugestoesClickListener,
    HistoryAdapter.OnHistoricoClickListener {


    private lateinit var db: AppDataBase
    private lateinit var repositoryHero: RepositoryHero

    private lateinit var repositoryHistory: RepositoryHistory
    private lateinit var adapterHistory: HistoryAdapter
    var listHistory = arrayListOf<HistoryDB>()

    private lateinit var repositorySuggestions: RepositorySuggestions
    private lateinit var adapterSuggestions: SugestoesAdapter
    var listSuggestions = arrayListOf<Suggestions>()


    //    var listPopulares: ArrayList<EntesMarvel> = getPopular()
//    var adapterPopular = PopularAdapter(listPopulares, this)
//    //modigicar funcao de pegar tamanho sugestões

//    //modigicar funcao de pegar tamanho do historico


    val viewModelHome by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    serviceCh,
                    serviceS,
                    repositoryHero,
                    repositoryHistory,
                    repositorySuggestions,
                    crH
                ) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


//        viewModelHome.transDate()

//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()


        Log.i("NETWORK", netOnline(this).toString())


        initDB()

        repositoryHero = RepositoryImplHero(db.heroDayDao())
        repositoryHistory = RepositoryImplHistory(db.historyDao())
        repositorySuggestions = RepositoryImplSuggestions(db.suggestionsDao())

//        viewModelHome.transDate(LocalDate.now())
        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        var context: Context = this
        viewModelHome.getAllH()
        viewModelHome.getHDayDB()


        //HERO DAY LOCAL
//        viewModelHome.retornoHeroDB.observe(this) {
//            when (it) {
//                true -> {
//                    viewModelHome.getCharacter()
//                }
//                false -> {
//                    viewModelHome.retornodataHSaved.observe(this) {
//
//                        when (viewModelHome.compareDate(LocalDate.now(), it)) {
//                            true -> {
//                                viewModelHome.characterSaved.observe(this) {
//                                    infoHeroDay(it)
//                                }
//                            }
//                            false -> {
//                                Log.i("Home", "DATA n é igual")
//                                viewModelHome.delHero()
//                                viewModelHome.getCharacter()
//
//                            }
//                        }
//
//                    }
//                }
//
//            }
//
//        }

        viewModelHome.checkHeroDayF()
        viewModelHome.docHeroDay.observe(this) {
            when (it) {
                true -> {
                    viewModelHome.getCharacter()
                    viewModelHome.infoHeroD.observe(this) { hero ->
                        viewModelHome.addHeroDayF(hero)
                        viewModelHome.delHeroDB()
                        infoHeroDay(hero, null)
                    }
                }
                false -> {
                    //maior muda
//                    menor pega do firebase

                    viewModelHome.getHeroDayF()
                    viewModelHome.retornoHeroDaySavedF.observe(this) { heroFirebase ->

                        viewModelHome.retornoHeroDB.observe(this) {
                            if (it == true) {
                                if (viewModelHome.transDate(heroFirebase.dateT)!! < LocalDate.now()) {
                                    viewModelHome.getCharacter()
                                    viewModelHome.infoHeroD.observe(this) { hero ->
                                        viewModelHome.addHeroDayF(hero)
                                        viewModelHome.addHeroDB(
                                            hero.idCharacter,
                                            hero.name,
                                            hero.extension,
                                            hero.path,
                                            hero.comics,
                                            hero.series,
                                            hero.stories,
                                            hero.dateT
                                        )
                                    }
                                } else {
                                    viewModelHome.retornoHeroDaySavedF.observe(this) { hero ->

                                        viewModelHome.addHeroDB(
                                            hero.idCharacter,
                                            hero.name,
                                            hero.extension,
                                            hero.path,
                                            hero.comics,
                                            hero.series,
                                            hero.stories,
                                            hero.dateT
                                        )
                                    }
                                }

                            }
                        }
                        if (heroFirebase.dateT != null && viewModelHome.retornodataHeroDB.value != null) {
                            var dateFirebase = viewModelHome.transDate(heroFirebase.dateT)
                            var dateDB = viewModelHome.transDate(viewModelHome.retornodataHeroDB.value )
                            if (dateFirebase != dateDB) {

                                //se a data do firebase é menor = true
//                            pega do firebase e joga no db
//                                se é menor então faz tudo de nv

//                            firebase é menor então chama tudo e atualiza fb e db
                                viewModelHome.getHeroDayF()
                                if (viewModelHome.compareDateHeroD(
                                        heroFirebase.dateT,
                                        viewModelHome.retornodataHeroDB.value!!
                                    )
                                ) {
                                    viewModelHome.getCharacter()
                                    viewModelHome.infoHeroD.observe(this) { hero ->
                                        viewModelHome.updateHeroDayF(hero)
                                        viewModelHome.delHeroDB()
                                        viewModelHome.addHeroDB(
                                            hero.idCharacter,
                                            hero.name,
                                            hero.extension,
                                            hero.path,
                                            hero.comics,
                                            hero.series,
                                            hero.stories,
                                            hero.dateT
                                        )
                                        infoHeroDay(hero, null)
                                    }

                                } else {
                                    viewModelHome.retornoHeroDaySavedF.observe(this) {
                                        viewModelHome.delHeroDB()
                                        viewModelHome.addHeroDB(
                                            it.idCharacter,
                                            it.name,
                                            it.extension,
                                            it.path,
                                            it.comics,
                                            it.series,
                                            it.stories,
                                            it.dateT
                                        )
                                        infoHeroDay(heroFirebase, null)
                                    }
                                }
                            } else {
                                viewModelHome.characterSavedDB.observe(this) { hero ->
                                    infoHeroDay(null, hero)
                                }
                            }
                        }
                    }
                }
            }

            Log.i("datanow", LocalDate.now().toString())
        }
//-----------------------------------------------------


        cvHeroiDoDia.setOnClickListener {
            viewModelHome.retornoHeroDaySavedF.observe(this) {
                detalheHeroDay(it.idCharacter)
            }

        }

        viewModelHome.getAllHistory()
        viewModelHome.updateSuggestions()
        viewModelHome.update()
        viewModelHome.getAllSuggestions()

        viewModelHome.retornoHistory.observe(this) {
            listHistory.addAll(it)
            adapterHistory = HistoryAdapter(listHistory, this)
            adapterHistory.notifyDataSetChanged()
            rvHistorico.adapter = adapterHistory
            rvHistorico.setHasFixedSize(true)
        }


        viewModelHome.retornoSuggestions.observe(this) {
            listSuggestions.addAll(it)
            adapterSuggestions = SugestoesAdapter(listSuggestions, this)
            rvSugestoes.adapter = adapterSuggestions
            rvSugestoes.setHasFixedSize(true)
        }



        btnNavigationHome.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_busca -> {
                    startActivity(Intent(this, BuscaActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_quiz -> {
                    startActivity(Intent(this, QuizActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_favoritos -> {
                    startActivity(Intent(this, FavoritoActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

    }

    override fun historicoClick(position: Int) {
        var historyType = viewModelHome.retornoHistory.value!![position].type
        var historyId = viewModelHome.retornoHistory.value!![position].id
        when (historyType) {
            "Comics" -> {
                var intent = Intent(this, DetalheHqActivity::class.java)
                intent.putExtra("idCo", historyId)
                startActivity(intent)
            }
            "Creator" -> {
                var intent = Intent(this, DetalheCriadorActivity::class.java)
                intent.putExtra("id", historyId)
                startActivity(intent)
            }
            "Character" -> {
                var intent = Intent(this, DetalhePersonagemActivity::class.java)
                intent.putExtra("idCh", historyId)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itTema -> {
                    chooseThemeDialog(MyPreferences(this).darkMode)
                    Toast.makeText(this@HomeActivity, "Changed theme.", Toast.LENGTH_SHORT).show()

                }

                R.id.help -> {
                    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                    var mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
                    mGoogleSignInClient.signOut().addOnCompleteListener {
                        FirebaseAuth.getInstance().signOut()
                    }
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()

                }

            }
            true
        })
        popupMenu.show()
    }


    override fun sugestoesClick(position: Int) {
        var suggestionType = viewModelHome.retornoSuggestions.value!![position].tipo
        var suggestionId = viewModelHome.retornoSuggestions.value!![position].id
        when (suggestionType) {
            "Comics" -> {
                var intent = Intent(this, DetalheHqActivity::class.java)
                intent.putExtra("idCo", suggestionId)
                startActivity(intent)
            }
            "Creator" -> {
                var intent = Intent(this, DetalheCriadorActivity::class.java)
                intent.putExtra("id", suggestionId)
                startActivity(intent)
            }
            "Character" -> {
                var intent = Intent(this, DetalhePersonagemActivity::class.java)
                intent.putExtra("idCh", suggestionId)
                startActivity(intent)
            }
        }
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }

    fun infoHeroDay(heroDayF: HeroDay?, heroDayDb: Characters?) {

        if (heroDayDb != null) {
            var heroDay = heroDayDb

            Log.i("HERODAY", heroDay.dateT.toString() )
            var img = heroDay.path + "." + heroDay.extension


            Picasso.get().load(img).fit().into(ivHeroiDoDia)
            tvNomeHeroiDoDia.text = heroDay.name
            tvComHeroiDoDia.text = "Comics: " + heroDay.comics?.toString()
            tvSerHeroiDoDia.text = "Series: " + heroDay.series?.toString()
            tvStoHeroiDoDia.text = "Stories: " + heroDay.stories?.toString()

        } else if (heroDayF != null) {


            var heroDay = heroDayF

            Log.i("HERODAY", heroDay.dateT.toString() )
            var img = heroDay.path + "." + heroDay.extension


            Picasso.get().load(img).fit().into(ivHeroiDoDia)
            tvNomeHeroiDoDia.text = heroDay.name
            tvComHeroiDoDia.text = "Comics: " + heroDay.comics?.toString()
            tvSerHeroiDoDia.text = "Series: " + heroDay.series?.toString()
            tvStoHeroiDoDia.text = "Stories: " + heroDay.stories?.toString()
        }


        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        Log.i("calender", "$day /$month /$year")

    }

    fun detalheHeroDay(id: Int) {
        var intent: Intent = Intent(this, DetalhePersonagemActivity::class.java)
        intent.putExtra("idCh", id)
        startActivity(intent)
    }

    fun netOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun chooseThemeDialog(preferenceUser: Int?) {


        when (preferenceUser) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                MyPreferences(this).darkMode = 0
                delegate.applyDayNight()


            }
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                MyPreferences(this).darkMode = 1
                delegate.applyDayNight()


            }

        }

    }

}

