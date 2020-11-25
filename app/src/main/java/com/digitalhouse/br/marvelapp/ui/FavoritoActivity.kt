package com.digitalhouse.br.marvelapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digitalhouse.br.marvelapp.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_favorito.*

class FavoritoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorito)

        setUpTabs()

        btnNavigationFavoritoBusca.selectedItemId = R.id.menu_favoritos

        //Transição entre activity's
        //TODO
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
}