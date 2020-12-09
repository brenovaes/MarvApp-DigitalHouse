package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.User
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setSupportActionBar(toolbarC)
        toolbarC.setNavigationOnClickListener(View.OnClickListener() {
            onBackPressed()
        })


        btnCadastrar.setOnClickListener(){
            getInformationUser()
        }


    }

    fun getInformationUser(): User {
        val name = tvUsuario.text.toString()
        val email = tvEmail.text.toString()
        val senha1 = etPasswordC.text.toString()
        val senha2 = etRPasswordC.text.toString()

        if (checkPassword(senha1, senha2)){
            showToast("Usuário cadastrado!")
            callLogin()
        }
        else{
            showToast("Senhas diferentes!")
        }

        return User(1, email,name, senha1)
    }

    fun checkPassword(s1: String, s2: String): Boolean {
        return s1 == s2
    }

    //imlementar passagem de putExtra user
    fun callLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}