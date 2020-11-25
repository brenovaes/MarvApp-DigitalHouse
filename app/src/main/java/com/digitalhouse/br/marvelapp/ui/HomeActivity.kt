package com.digitalhouse.br.marvelapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.digitalhouse.br.marvelapp.QuizActivity
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.activity_home.*

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

        setSupportActionBar(toolbarH)


        rvMaisPopulares.adapter = adapterPopular
        rvMaisPopulares.setHasFixedSize(true)

        rvSugestoes.adapter = adapterSugestoes
        rvSugestoes.setHasFixedSize(true)

        rvHistorico.adapter = adapterHistorico
        rvHistorico.setHasFixedSize(true)


        btnNavigationHome.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_busca-> {
                    startActivity(Intent(this, BuscaActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_quiz -> {
                    startActivity(Intent(this, QuizActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                //mudar activity
                R.id.navigation_favorito -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                //mudar activity
                R.id.navigation_perfil -> {
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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView){
            R.id.navigaton_configuracao -> showToast("configurações")
        }
        return false
    }

    fun getPopular():ArrayList<EntesMarvel>{
        return arrayListOf(
            EntesMarvel("Nome1",R.drawable.teste),
            EntesMarvel("Nome2",R.drawable.teste),
            EntesMarvel("Nome3",R.drawable.teste),
            EntesMarvel("Nome4",R.drawable.teste),
            EntesMarvel("Nome5",R.drawable.teste),
            EntesMarvel("Nome6",R.drawable.teste)
        )
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun callDetalheCard(ente: EntesMarvel) {
//        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nome",ente.nome)
        startActivity( intent)

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
}