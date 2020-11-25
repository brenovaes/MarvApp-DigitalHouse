package com.digitalhouse.br.marvelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Criar botão
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btnNavigationMain)

        //Setar Botão para tela atual
        bottomNavigationView.selectedItemId = R.id.navigation_home

        //Transição entre activity's
        //TODO
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_quiz -> {
                    startActivity(Intent(this, QuizActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}
