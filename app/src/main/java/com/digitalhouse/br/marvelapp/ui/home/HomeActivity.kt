package com.digitalhouse.br.marvelapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.Suggestions
import com.digitalhouse.br.marvelapp.service.*
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.favoritos.FavoritoActivity
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.SplashActivity
import com.digitalhouse.br.marvelapp.ui.perfil.PerfilActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar_principal.*
import java.time.LocalDate

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
                    repositorySuggestions
                ) as T
            }
        }
    }


    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.i("NETWORK", netOnline(this).toString())


        initDB()
        repositoryHero = RepositoryImplHero(db.heroDayDao())
        repositoryHistory = RepositoryImplHistory(db.historyDao())
        repositorySuggestions = RepositoryImplSuggestions(db.suggestionsDao())

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        var context: Context = this
        viewModelHome.getAllH()
        viewModelHome.getHDay()




        viewModelHome.retornoHeroDB.observe(this) {
            when (it) {
                true -> viewModelHome.getCharacter()
                false -> {
                    viewModelHome.retornodataHSaved.observe(this) {

                        when (viewModelHome.compareDate(LocalDate.now(), it)) {
                            true -> {
                                viewModelHome.characterSaved.observe(this) {
                                    infoHeroDay(it)
                                }
                            }
                            false -> {
                                Log.i("Home", "DATA n é igual")
                                viewModelHome.delHero()
                                viewModelHome.getCharacter()

                            }
                        }

                    }
                }

            }

        }




//        viewModelHome.getAllCharactersSugestao()
//        viewModelHome.getAllComicsSugestao()
//        viewModelHome.getAllCreatorsSugestao()
//
//        viewModelHome.retornoCom.observe(this){
//            var lista = arrayListOf<ResSugestao>()
//            lista.addAll(it.data.results)
//            viewModelHome.retornoCrea.observe(this){
//                lista.add(it)
//                viewModelHome.retornoChar.observe(this){
//                    lista.add(it)
//                }
//            }
//            adapterSugestoes = SugestoesAdapter(lista, this)
//            rvSugestoes.adapter = adapterSugestoes
//            rvSugestoes.setHasFixedSize(true)
//        }


//            character.addAll(it.data.results)
//            var comics = character[0].comics.items
//            var series = character[0].series.items
//            var events = character[0].events.items
//
//            tvNomePersonagemDetalhe.text = character[0].name
//            tvDescricaoPersonagemDetalhe.text = character[0].description
//
//            var img = character[0].thumbnail.path + "." + character[0].thumbnail.extension
//            Picasso.get().load(img).resize(360,280).into(ivPersonagemDetalhe)

//        rvMaisPopulares.adapter = adapterPopular
//        rvMaisPopulares.setHasFixedSize(true)
//

//
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

        cvHeroiDoDia.setOnClickListener {
            viewModelHome.characterSaved.observe(this) {
                detalheHeroDay(it.idCharacter)
            }

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

    //arrumar o menu que nao esta abrindo os itens
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help -> {
                startActivity(Intent(this, LoginActivity::class.java))
                return true
            }
            R.id.itTema -> {
                showToast("Clicado para mudar o tema!!")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    fun getPopular():ArrayList<EntesMarvel>{
//        return arrayListOf(
//                Characters(1,R.drawable.omiranha,"Personagem1"),
//                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
//                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
//                Characters(2,R.drawable.omiranha,"Personagem1",),
//                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
//                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
//                Characters(3,R.drawable.omiranha,"Personagem1")
//        )
//    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


//    fun callDetalheCard(ente: EntesMarvel) {
//        //adicionar variavel ou outro identificador de tipo de obj para redirecionar pela maneira correta
//        var intent:Intent = Intent(this, DetalhePersonagemActivity::class.java)
//        intent.putExtra("nome",ente.nome)
//        startActivity(intent)
//
//    }

//    override fun popularClick(position: Int) {
//        val ente = listPopulares.get(position)
//        var nomeEnte = ente.nome
//        var imgEnte = ente.img
//        adapterPopular.notifyItemChanged(position)
//        callDetalheCard(ente)
//    }

//    override fun sugestoesClick(position: Int) {
//        val ente = listSugestoes.get(position)
//        var nomeEnte = ente.nome
//        var imgEnte = ente.img
//        adapterSugestoes.notifyItemChanged(position)
//        callDetalheCard(ente)
//    }

//    override fun historicoClick(position: Int) {
//        val ente = listHistorico.get(position)
//        var nomeEnte = ente.nome
//        var imgEnte = ente.img
//        adapterHistorico.notifyItemChanged(position)
//        callDetalheCard(ente)
//    }

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itTema ->
                    Toast.makeText(this@HomeActivity, "Changed", Toast.LENGTH_SHORT).show()
                R.id.help ->
                    startActivity(Intent(this, SplashActivity::class.java))
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
                intent.putExtra ("idCo",suggestionId)
                startActivity(intent)
            }
            "creator" -> {
                var intent = Intent(this, DetalheCriadorActivity::class.java)
                intent.putExtra ("id",suggestionId)
                startActivity(intent)
            }
            "character" -> {
                var intent = Intent(this, DetalhePersonagemActivity::class.java)
                intent.putExtra ("idCh",suggestionId)
                startActivity(intent)
            }
        }
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }

    fun infoHeroDay(character: Characters) {
        var img = character.path + "." + character.extension
        Picasso.get().load(img).resize(150, 150).into(ivHeroiDoDia)
        tvNomeHeroiDoDia.text = character.name
        tvComHeroiDoDia.text = "Comics: " + character.comics?.toString()
        tvSerHeroiDoDia.text = "Series: " + character.series?.toString()
        tvStoHeroiDoDia.text = "Stories: " + character.stories?.toString()
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


}

