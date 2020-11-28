package com.digitalhouse.br.marvelapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.Alternativa
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ui.QuizActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_pergunta.*
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.enunciado_quiz.*
import kotlinx.android.synthetic.main.enunciado_quiz.view.*
import kotlinx.android.synthetic.main.fragment_selos.*

class PerguntaActivity : AppCompatActivity(), AlternativaAdapter.OnItemClickListener {
    private var listaAlternativa = getListaAlternativa()
    private var adapter = AlternativaAdapter(listaAlternativa, this)


    private fun getListaAlternativa(): ArrayList<Alternativa> {
        return arrayListOf<Alternativa>(
                Alternativa("Timely Comics", R.color.white),
                Alternativa("Atlas Comics",R.color.white),
                Alternativa("Goodman Comics", R.color.white),
                Alternativa("More Fun Comics", R.color.white),
        )
    }

    override fun onItemClick(position: Int) {
        listaAlternativa[position].corBackground = R.color.destaqueVermelho
        Log.i("PerguntaActivity", "MUDA DE COR")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)

        tvEnunciadoTexto.text = "Qual era o nome da editora predecessora da Marvel?"
        cvNumeroPergunta.tvNumeroDaPergunta.text = "1/5"

        rvAlternativa.adapter = adapter
        rvAlternativa.layoutManager = LinearLayoutManager(this)
        rvAlternativa.setHasFixedSize(true)

        btnAnterior.setOnClickListener(callActivity(QuizActivity::class.java))
        btnSalvar.setOnClickListener(callActivity(QuizActivity::class.java))
        btnDesistir.setOnClickListener(callActivity(QuizActivity::class.java))

    }


    private fun callActivity(activity: Class<out Activity>): View.OnClickListener? = View.OnClickListener {
        startActivity(Intent(this, activity))
    }

}