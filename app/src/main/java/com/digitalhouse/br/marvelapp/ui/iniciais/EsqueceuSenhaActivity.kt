package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractRedefinirSenha
import kotlinx.android.synthetic.main.activity_esqueceu_senha.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class EsqueceuSenhaActivity : AppCompatActivity(), ContractRedefinirSenha {

    val fragRedefinirS = RedefinirSenhaFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction().apply{
            add(R.id.flEsqueceuSenha, fragRedefinirS)
            commit()}

    }


    override fun callFragVerificarEmail() {
        val fragVerificarE = VerificarEmailFragment.newInstance()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flEsqueceuSenha, fragVerificarE)
            commit()
        }
    }

    override fun erroRedefinirSenha() {
        showToast("Incorrect or not registered email. Please insert another one.")
    }

    override fun callLoginAct() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            var intent = Intent(this@EsqueceuSenhaActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun emailRedefBlank() {
        showToast("Insert some email to continue.")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}