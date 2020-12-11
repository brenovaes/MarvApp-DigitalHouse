package com.digitalhouse.br.marvelapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.Comics
import com.digitalhouse.br.marvelapp.models.Creators
import com.digitalhouse.br.marvelapp.models.EntesMarvel
import com.digitalhouse.br.marvelapp.service.serviceCh
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.favoritos.FavoritoActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.SplashActivity
import com.digitalhouse.br.marvelapp.ui.perfil.PerfilActivity
import com.digitalhouse.br.marvelapp.ui.personagens.CharactersComicsAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.CharactersViewModel
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.card_perfil_ranking.*
import kotlinx.android.synthetic.main.toolbar_principal.*
import java.util.*

class HomeActivity : AppCompatActivity()
//    PopularAdapter.OnPopularClickListener,
//    SugestoesAdapter.OnSugestoesClickListener
//    HistoricoAdapter.OnHistoricoClickListener

{

//    var listPopulares: ArrayList<EntesMarvel> = getPopular()
//    var adapterPopular = PopularAdapter(listPopulares, this)
//    //modigicar funcao de pegar tamanho sugestões
//    var listSugestoes: ArrayList<EntesMarvel> = getPopular()
//    lateinit var adapterSugestoes: SugestoesAdapter
//    //modigicar funcao de pegar tamanho do historico
//    var listHistorico: ArrayList<EntesMarvel> = getPopular()
//    var adapterHistorico = HistoricoAdapter(listHistorico, this)

    val viewModelHome by viewModels<HomeViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(serviceCh) as T
            }
        }
    }

    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }
        viewModelHome.getCharacter()

        viewModelHome.retornoHeroiDia.observe(this){

            var char = viewModelHome.retornoHeroiDia.value?.data
            var img = char?.results?.get(0)?.thumbnail?.path + "." + char?.results?.get(0)?.thumbnail?.extension
            Picasso.get().load(img).resize(150,150).into(ivHeroiDoDia)
            tvNomeHeroiDoDia.text = char?.results?.get(0)?.name
            tvComHeroiDoDia.text = "Comics: " + char?.results?.get(0)?.comics?.available?.toString()
            tvSerHeroiDoDia.text = "Series: " + char?.results?.get(0)?.series?.available?.toString()
            tvStoHeroiDoDia.text = "Stories: " + char?.results?.get(0)?.stories?.available?.toString()
        }

        cvHeroiDoDia.setOnClickListener {
            viewModelHome.retornoHeroiDia.observe(this) {
                var id = it.data.results[0].id
                var intent:Intent = Intent(this, DetalhePersonagemActivity::class.java)
                intent.putExtra("idCh",id)
                startActivity(intent)
            }
        }

//        viewModelHome.retornoHeroiDia.observe(this){
//            adapterSugestoes = SugestoesAdapter(it.data.results, this)
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
//        rvHistorico.adapter = adapterHistorico
//        rvHistorico.setHasFixedSize(true)


        btnNavigationHome.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_busca-> {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //arrumar o menu que nao esta abrindo os itens
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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

    fun showToast(msg:String){
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
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->
                    Toast.makeText(this@HomeActivity, "Changed", Toast.LENGTH_SHORT).show()
                R.id.help ->
                    startActivity(Intent(this, SplashActivity::class.java))
            }
            true
        })
        popupMenu.show()
    }


}