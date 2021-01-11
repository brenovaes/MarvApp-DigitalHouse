package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()


        btnLogin.setOnClickListener {
            callHome()
        }

        tvCadastre_se.setOnClickListener {
            callCadastro()
        }

        tvEsqueceuSenha.setOnClickListener(){
            callEsqueceuSenha()
        }

        //somente para ver se a tela de redefinição de senha está ok
        ivLogo.setOnClickListener(){
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }
    }

    fun callHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    //falta imlementar passagem de parâmetros do user
    fun callCadastro() {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun callEsqueceuSenha() {
        var intent = Intent(this, EsqueceuSenhaActivity::class.java)
        startActivity(intent)
    }

//    fun getInformationUser():User{
//        val email = tvUsuarioL.text.toString()
//        val senha = tvPasswordL.text.toString()
//
//        return User(1, email, senha)
//    }

}