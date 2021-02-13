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
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
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
        infoHeroDay()

        viewModelHome.checkHeroDayF()
        viewModelHome.docHeroDay.observe(this) {
            when (it) {
                true -> {
                    viewModelHome.getCharacter()
                    viewModelHome.infoHeroD.observe(this) { hero ->
                        viewModelHome.addHeroDayF(hero)
                        viewModelHome.delHeroDB()
                    }
                }
                false -> {

                    viewModelHome.getHeroDayF()
                    viewModelHome.retornoHeroDaySavedF.observe(this) { heroDayFirebaseRetorno ->

                        viewModelHome.retornoHeroDB.observe(this) {retornoHeroDB ->
                            when (retornoHeroDB) {

                                true -> {
                                    var diaHeroF =
                                            viewModelHome.transDate(heroDayFirebaseRetorno.dateT)
                                    if (diaHeroF!! < LocalDate.now()) {
                                        Log.i(
                                                "VER DB VAZIO Datas são dif firebase menor",
                                                diaHeroF.toString() + " .." + LocalDate.now().toString()
                                        )
                                        viewModelHome.getCharacter()
                                        viewModelHome.infoHeroD.observe(this) { hero ->
                                            viewModelHome.addHeroDayF(hero)
                                            viewModelHome.addHeroDB(
                                                    heroDayFirebaseRetorno.idCharacter,
                                                    heroDayFirebaseRetorno.name,
                                                    heroDayFirebaseRetorno.extension,
                                                    heroDayFirebaseRetorno.path,
                                                    heroDayFirebaseRetorno.comics,
                                                    heroDayFirebaseRetorno.series,
                                                    heroDayFirebaseRetorno.stories,
                                                    heroDayFirebaseRetorno.dateT
                                            )
                                        }
                                    } else {
                                        Log.i(
                                                "VER DB VAZIO Datas são dif firebase maior",
                                                diaHeroF.toString() + " .." + LocalDate.now().toString()
                                        )

                                        viewModelHome.addHeroDB(
                                                heroDayFirebaseRetorno.idCharacter,
                                                heroDayFirebaseRetorno.name,
                                                heroDayFirebaseRetorno.extension,
                                                heroDayFirebaseRetorno.path,
                                                heroDayFirebaseRetorno.comics,
                                                heroDayFirebaseRetorno.series,
                                                heroDayFirebaseRetorno.stories,
                                                heroDayFirebaseRetorno.dateT
                                        )

                                    }

                                }
                            }
                        }
                        if (heroDayFirebaseRetorno.dateT != null && viewModelHome.retornodataHeroDB.value != null) {
                            var dateFirebase = viewModelHome.transDate(heroDayFirebaseRetorno.dateT!!)
                            var dateDB = viewModelHome.transDate(viewModelHome.retornodataHeroDB.value)

                            var heroiDBL = viewModelHome.characterSavedDB.value!!
                            if (dateFirebase != dateDB) {
                                Log.i(
                                        "VER tudo preenchido Datas são dif ",
                                        "f $dateFirebase, db $dateDB"
                                )


                                viewModelHome.getHeroDayF()
                                if (viewModelHome.compareDateHeroD(
                                                heroDayFirebaseRetorno.dateT,
                                                viewModelHome.retornodataHeroDB.value!!
                                        )
                                ) {
                                    Log.i("VER preenchido Datas são dif firebase menor", "Q")

                                    viewModelHome.getCharacter()
                                    viewModelHome.infoHeroD.observe(this) { hero ->
                                        viewModelHome.updateHeroDayF(hero)
                                        viewModelHome.delHeroDB()
                                        viewModelHome.addHeroDB(
                                                heroDayFirebaseRetorno.idCharacter,
                                                heroDayFirebaseRetorno.name,
                                                heroDayFirebaseRetorno.extension,
                                                heroDayFirebaseRetorno.path,
                                                heroDayFirebaseRetorno.comics,
                                                heroDayFirebaseRetorno.series,
                                                heroDayFirebaseRetorno.stories,
                                                heroDayFirebaseRetorno.dateT
                                        )
                                    }

                                } else {
                                    Log.i("VER preenchido Datas são dif firebase maior ==", "Q")
                                    viewModelHome.delHeroDB()
                                    viewModelHome.addHeroDB(
                                            heroDayFirebaseRetorno.idCharacter,
                                            heroDayFirebaseRetorno.name,
                                            heroDayFirebaseRetorno.extension,
                                            heroDayFirebaseRetorno.path,
                                            heroDayFirebaseRetorno.comics,
                                            heroDayFirebaseRetorno.series,
                                            heroDayFirebaseRetorno.stories,
                                            heroDayFirebaseRetorno.dateT
                                    )

                                }
                            } else if (dateFirebase == LocalDate.now()) {
                                Log.i("VER preenchido Datas são iguais ate com agr", "Q")

                            } else if (dateFirebase!! < LocalDate.now()) {
                                Log.i("VER preenchido Datas são iguais fire menor q agr", "Q")

                                viewModelHome.getCharacter()
                                viewModelHome.infoHeroD.observe(this) { hero ->
                                    viewModelHome.updateHeroDayF(hero)
                                    viewModelHome.delHeroDB()
                                    viewModelHome.addHeroDB(
                                            heroDayFirebaseRetorno.idCharacter,
                                            heroDayFirebaseRetorno.name,
                                            heroDayFirebaseRetorno.extension,
                                            heroDayFirebaseRetorno.path,
                                            heroDayFirebaseRetorno.comics,
                                            heroDayFirebaseRetorno.series,
                                            heroDayFirebaseRetorno.stories,
                                            heroDayFirebaseRetorno.dateT
                                    )
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
            viewModelHome.characterSavedDB.observe(this) {
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
            "comics" -> {
                var intent = Intent(this, DetalheHqActivity::class.java)
                intent.putExtra("idCo", historyId)
                startActivity(intent)
            }
            "creator" -> {
                var intent = Intent(this, DetalheCriadorActivity::class.java)
                intent.putExtra("id", historyId)
                startActivity(intent)
            }
            "character" -> {
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
            "comics" -> {
                var intent = Intent(this, DetalheHqActivity::class.java)
                intent.putExtra("idCo", suggestionId)
                startActivity(intent)
            }
            "creator" -> {
                var intent = Intent(this, DetalheCriadorActivity::class.java)
                intent.putExtra("id", suggestionId)
                startActivity(intent)
            }
            "character" -> {
                var intent = Intent(this, DetalhePersonagemActivity::class.java)
                intent.putExtra("idCh", suggestionId)
                startActivity(intent)
            }
        }
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }

    fun infoHeroDay() {

        viewModelHome.characterSavedDB.observe(this){heroDay ->
            if (heroDay != null){
                var img = heroDay.path + "/portrait_small" + "." + heroDay.extension
                Picasso.get().load(img).fit().into(ivHeroiDoDia)
                tvNomeHeroiDoDia.text = heroDay.name
                tvComHeroiDoDia.text = "Comics: " + heroDay.comics?.toString()
                tvSerHeroiDoDia.text = "Series: " + heroDay.series?.toString()
                tvStoHeroiDoDia.text = "Stories: " + heroDay.stories?.toString()
            }

        }

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

