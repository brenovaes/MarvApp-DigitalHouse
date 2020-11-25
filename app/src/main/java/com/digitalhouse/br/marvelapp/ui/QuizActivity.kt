package com.digitalhouse.br.marvelapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        setUpTabs()

        //Criar botão
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btnNavigationQuiz)

        //Setar Botão para tela atual
        bottomNavigationView.selectedItemId = R.id.menu_quiz

        //Transição entre activity's
        //TODO
        bottomNavigationView.setOnNavigationItemSelectedListener {
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
        tlQuiz.addTab(tlQuiz.newTab().setText("CHALLENGE"))
        tlQuiz.addTab(tlQuiz.newTab().setText("RANKING"))
        tlQuiz.addTab(tlQuiz.newTab().setText("STAMPS"))

        val adapter = ViewPagerAdapter(supportFragmentManager, tlQuiz.tabCount)
        vpQuiz.adapter = adapter
        vpQuiz.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlQuiz))

        tlQuiz.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vpQuiz.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }


}

