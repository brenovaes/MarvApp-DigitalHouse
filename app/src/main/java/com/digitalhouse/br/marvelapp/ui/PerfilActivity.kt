package com.digitalhouse.br.marvelapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R

import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        //Setar Botão para tela atual
        btnNavigationPerfil.selectedItemId = R.id.menu_perfil

        btnNavigationPerfil.setOnNavigationItemSelectedListener {
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
                //mudar activity
                R.id.menu_favoritos -> {
                    startActivity(Intent(this, FavoritoActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                //mudar activity
                R.id.menu_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->
                    Toast.makeText(this@PerfilActivity, "Changed", Toast.LENGTH_SHORT).show()
                R.id.help ->
                    startActivity(Intent(this, SplashActivity::class.java))
            }
            true
        })
        popupMenu.show()
    }

}