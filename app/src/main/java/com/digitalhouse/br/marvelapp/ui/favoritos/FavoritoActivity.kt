package com.digitalhouse.br.marvelapp.ui.favoritos

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.ui.perfil.PerfilActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.digitalhouse.br.marvelapp.ui.busca.ViewPagerBuscaAdapter
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.SplashActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_favorito.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class FavoritoActivity: AppCompatActivity(), ContractDetalheCardsFragments {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorito)


        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        setUpTabs()

        btnNavigationFavoritoBusca.selectedItemId = R.id.menu_favoritos

        //Transição entre activity's
        btnNavigationFavoritoBusca.setOnNavigationItemSelectedListener {
            when(it.itemId){
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

    private fun setUpTabs(){
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("CHARACTERS"))
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("COMICS"))
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("CREATORS"))

        val adapter = ViewPagerBuscaAdapter(supportFragmentManager, tlFavoritoBusca.tabCount)
        vpFavorito.adapter = adapter
        vpFavorito.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlFavoritoBusca))

        tlFavoritoBusca.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vpFavorito.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun callDetalhesPCards(idCharacter:Int) {
        startActivity(Intent(this, DetalhePersonagemActivity::class.java))
    }

    override fun callDetalhesHQCards(idComic:Int) {
        startActivity(Intent(this, DetalheHqActivity::class.java))
    }

    override fun callDetalhesCCards(idCreator:Int) {
        startActivity(Intent(this, DetalheCriadorActivity::class.java))
    }

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->
                    Toast.makeText(this@FavoritoActivity, "Changed", Toast.LENGTH_SHORT).show()
                R.id.help ->
                    startActivity(Intent(this, SplashActivity::class.java))
            }
            true
        })
        popupMenu.show()
    }
}