package com.digitalhouse.br.marvelapp.ui.iniciais

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractRedefinirSenha
import kotlinx.android.synthetic.main.activity_esqueceu_senha.*

class EsqueceuSenha : AppCompatActivity(), ContractRedefinirSenha {

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


}