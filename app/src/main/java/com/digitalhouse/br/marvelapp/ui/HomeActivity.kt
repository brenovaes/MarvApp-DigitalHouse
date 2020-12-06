package com.digitalhouse.br.marvelapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class HomeActivity : AppCompatActivity(), PopularAdapter.OnPopularClickListener, SugestoesAdapter.OnSugestoesClickListener, HistoricoAdapter.OnHistoricoClickListener {

    var listPopulares: ArrayList<EntesMarvel> = getPopular()
    var adapterPopular = PopularAdapter(listPopulares, this)
    //modigicar funcao de pegar tamanho sugestões
    var listSugestoes: ArrayList<EntesMarvel> = getPopular()
    var adapterSugestoes = SugestoesAdapter(listSugestoes, this)
    //modigicar funcao de pegar tamanho do historico
    var listHistorico: ArrayList<EntesMarvel> = getPopular()
    var adapterHistorico = HistoricoAdapter(listHistorico, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        rvMaisPopulares.adapter = adapterPopular
        rvMaisPopulares.setHasFixedSize(true)

        rvSugestoes.adapter = adapterSugestoes
        rvSugestoes.setHasFixedSize(true)

        rvHistorico.adapter = adapterHistorico
        rvHistorico.setHasFixedSize(true)


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

    fun getPopular():ArrayList<EntesMarvel>{
        return arrayListOf(
                Characters(1,R.drawable.omiranha,"Personagem1"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Characters(2,R.drawable.omiranha,"Personagem1",),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Characters(3,R.drawable.omiranha,"Personagem1")
        )
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun callDetalheCard(ente: EntesMarvel) {
        //adicionar variavel ou outro identificador de tipo de obj para redirecionar pela maneira correta
        var intent:Intent = Intent(this,DetalhePersonagemActivity::class.java)
        intent.putExtra("nome",ente.nome)
        startActivity(intent)

    }

    override fun popularClick(position: Int) {
        val ente = listPopulares.get(position)
        var nomeEnte = ente.nome
        var imgEnte = ente.img
        adapterPopular.notifyItemChanged(position)
        callDetalheCard(ente)
    }

    override fun sugestoesClick(position: Int) {
        val ente = listSugestoes.get(position)
        var nomeEnte = ente.nome
        var imgEnte = ente.img
        adapterSugestoes.notifyItemChanged(position)
        callDetalheCard(ente)
    }

    override fun historicoClick(position: Int) {
        val ente = listHistorico.get(position)
        var nomeEnte = ente.nome
        var imgEnte = ente.img
        adapterHistorico.notifyItemChanged(position)
        callDetalheCard(ente)
    }

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