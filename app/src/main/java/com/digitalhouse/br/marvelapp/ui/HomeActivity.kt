package com.digitalhouse.br.marvelapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), PopularAdapter.OnPopularClickListener, SugestoesAdapter.OnSugestoesClickListener, HistoricoAdapter.OnHistoricoClickListener {

    var listPopulares: ArrayList<Characters> = getPopular()
    var adapterPopular = PopularAdapter(listPopulares, this)
   //modigicar funcao de pegar tamanho sugestões
    var listSugestoes: ArrayList<Characters> = getPopular()
    var adapterSugestoes = SugestoesAdapter(listSugestoes, this)
    //modigicar funcao de pegar tamanho do historico
    var listHistorico: ArrayList<Characters> = getPopular()
    var adapterHistorico = HistoricoAdapter(listHistorico, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
                    startActivity(Intent(this, DetalheCardsActivity::class.java))
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
        inflater.inflate(R.menu.menu_configuracao, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //arrumar o menu que nao esta abrindo os itens
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView){
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

    fun getPopular():ArrayList<Characters>{
        return arrayListOf(
            Characters(1,R.drawable.omiranha,"Personagem1",),
            Characters(2,R.drawable.omiranha,"Personagem1",),
            Characters(3,R.drawable.omiranha,"Personagem1",),
            Characters(4,R.drawable.omiranha,"Personagem1",),
            Characters(5,R.drawable.omiranha,"Personagem1",),
            Characters(6,R.drawable.omiranha,"Personagem1",),
            Characters(7,R.drawable.omiranha,"Personagem1",)
        )
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun callDetalheCard(ente: Characters) {
        var intent:Intent = Intent(this,DetalhePersonagemActivity::class.java)
        intent.putExtra("nome",ente.nomeCharacter)
        startActivity( intent)

    }

    override fun popularClick(position: Int) {
        val ente = listPopulares.get(position)
        var nomeEnte = ente.nomeCharacter
        var imgEnte = ente.imagemCharacter
        adapterPopular.notifyItemChanged(position)
        callDetalheCard(ente)
    }

    override fun sugestoesClick(position: Int) {
        val ente = listSugestoes.get(position)
        var nomeEnte = ente.nomeCharacter
        var imgEnte = ente.imagemCharacter
        adapterSugestoes.notifyItemChanged(position)
        callDetalheCard(ente)
    }

    override fun historicoClick(position: Int) {
        val ente = listHistorico.get(position)
        var nomeEnte = ente.nomeCharacter
        var imgEnte = ente.imagemCharacter
        adapterHistorico.notifyItemChanged(position)
        callDetalheCard(ente)
    }
}