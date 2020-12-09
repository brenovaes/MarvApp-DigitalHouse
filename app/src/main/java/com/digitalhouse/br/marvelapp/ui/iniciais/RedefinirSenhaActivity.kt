package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.activity_redefinir_senha.*


class RedefinirSenhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redefinir_senha)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(){
            onBackPressed()
        }

        btnRedefinirSenha.setOnClickListener(){
            getPasswordUser()
        }
    }

    fun getPasswordUser(){
        val senha1 = etPasswordRS.text.toString()
        val senha2 = etRPasswordRS.text.toString()

        if (checkPassword(senha1, senha2)){
            showToast("Senha redefinida com sucesso!")
            callLogin()
        }
        else{
            showToast("Senhas diferentes!")
        }
    }

    fun checkPassword(s1: String, s2: String): Boolean {
        return s1 == s2
    }

    fun callLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}