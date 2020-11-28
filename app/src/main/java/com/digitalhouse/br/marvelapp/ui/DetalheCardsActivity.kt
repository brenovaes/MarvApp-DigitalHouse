package com.digitalhouse.br.marvelapp.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_busca.*

class DetalheCardsActivity : AppCompatActivity(), ContractDetalheCardsFragments {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)

        setUpTabs()

        btnNavigationBusca.selectedItemId = R.id.menu_busca

        //Transição entre activity's
        btnNavigationBusca.setOnNavigationItemSelectedListener {
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

    private fun setUpTabs(){
        tlBusca.addTab(tlBusca.newTab().setText("CHARACTERS"))
        tlBusca.addTab(tlBusca.newTab().setText("COMICS"))
        tlBusca.addTab(tlBusca.newTab().setText("CREATORS"))

        val adapter = ViewPagerBuscaAdapter(supportFragmentManager, tlBusca.tabCount)
        vpBusca.adapter = adapter
        vpBusca.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlBusca))

        tlBusca.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vpBusca.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun callDetalhesPCards() {
        startActivity(Intent(this,DetalhePersonagemActivity::class.java))
    }

    override fun callDetalhesHQCards() {
        startActivity(Intent(this,DetalheHqActivity::class.java))
    }

    override fun callDetalhesCCards() {
        startActivity(Intent(this,DetalheCriadorActivity::class.java))
    }


}

