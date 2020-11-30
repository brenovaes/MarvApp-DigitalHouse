package com.digitalhouse.br.marvelapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.toolbar_principal.*


class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        setUpTabs()

        btnNavigationQuiz.selectedItemId = R.id.menu_quiz

        //Transição entre activity's
        btnNavigationQuiz.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_busca -> {
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

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->
                    Toast.makeText(this@QuizActivity, "Changed", Toast.LENGTH_SHORT).show()
                R.id.help ->
                    startActivity(Intent(this, SplashActivity::class.java))
            }
            true
        })
        popupMenu.show()
    }

}

