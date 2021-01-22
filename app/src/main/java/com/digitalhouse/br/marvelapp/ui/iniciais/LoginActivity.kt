package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryImpl
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var repository: RepositoryDB
    private lateinit var db: AppDataBase

    val loginViewModel by viewModels<LoginViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        initDB()
        repository = RepositoryImpl(db.userDao())

        btnLogin.setOnClickListener {
            loginViewModel.checkUserEmail(etUsuarioL.text.toString())
            loginViewModel.result.observe(this) {
                if (it != null) {
                    loginViewModel.checkUserPassword(it)
                    loginViewModel.password.observe(this) {
                        if (it == etPasswordL.text.toString()) {
                            callHome()
                        } else {
                            showToast("Incorrect email or password")
                        }
                    }
                } else {
                    showToast("Incorrect email or password")
                }
            }
        }

        tvCadastre_se.setOnClickListener {
            callCadastro()
        }

        tvEsqueceuSenha.setOnClickListener() {
            callEsqueceuSenha()
        }

        //somente para ver se a tela de redefinição de senha está ok
        ivLogo.setOnClickListener() {
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

    fun initDB() {
        db = AppDataBase.invoke(this)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

//    fun getInformationUser():User{
//        val email = tvUsuarioL.text.toString()
//        val senha = tvPasswordL.text.toString()
//
//        return User(1, email, senha)
//    }

}