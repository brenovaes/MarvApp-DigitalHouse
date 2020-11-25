package com.digitalhouse.br.marvelapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.digitalhouse.br.marvelapp.R.color
import com.digitalhouse.br.marvelapp.ui.PerguntaActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.balao_pergunta_quiz.view.*
import kotlinx.android.synthetic.main.fragment_desafios.*
import kotlinx.android.synthetic.main.trilha_quiz.view.*


class DesafiosFragment : Fragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desafios, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //inicialziar os balões
        val balao2Numero: TextView = trilhaQuiz.balao2.tvNumeroBalao
        balao2Numero.text = "2"
        val balao2Nome: TextView = trilhaQuiz.balao2.tvNomeBalao
        balao2Nome.text = "Comics"

        val balao3Cor: CardView = trilhaQuiz.balao3.cvBalao
        balao3Cor.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaClaro))
        val balao3Numero: TextView = trilhaQuiz.balao3.tvNumeroBalao
        balao3Numero.text = "3"
        val balao3Nome: TextView = trilhaQuiz.balao3.tvNomeBalao
        balao3Nome.text = "Heroes"
        val balao3PontuacaoCard: CardView = trilhaQuiz.balao3.cvPontuacao
        balao3PontuacaoCard.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaEscuro))
        val balao3Pontuacao: TextView = trilhaQuiz.balao3.tvPontuacaoBalao
        balao3Pontuacao.text = "0"

        val balao4Cor: CardView = trilhaQuiz.balao4.cvBalao
        balao4Cor.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaClaro))
        val balao4Numero: TextView = trilhaQuiz.balao4.tvNumeroBalao
        balao4Numero.text = "4"
        val balao4Nome: TextView = trilhaQuiz.balao4.tvNomeBalao
        balao4Nome.text = "Villains"
        val balao4PontuacaoCard: CardView = trilhaQuiz.balao4.cvPontuacao
        balao4PontuacaoCard.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaEscuro))
        val balao4Pontuacao: TextView = trilhaQuiz.balao4.tvPontuacaoBalao
        balao4Pontuacao.text = "0"

        val balao5Cor: CardView = trilhaQuiz.balao5.cvBalao
        balao5Cor.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaClaro))
        val balao5Numero: TextView = trilhaQuiz.balao5.tvNumeroBalao
        balao5Numero.text = "5"
        val balao5Nome: TextView = trilhaQuiz.balao5.tvNomeBalao
        balao5Nome.text = "Events"
        val balao5PontuacaoCard: CardView = trilhaQuiz.balao5.cvPontuacao
        balao5PontuacaoCard.setCardBackgroundColor(ContextCompat.getColor(activity!!, color.cinzaEscuro))
        val balao5Pontuacao: TextView = trilhaQuiz.balao5.tvPontuacaoBalao
        balao5Pontuacao.text = "0"


        trilhaQuiz.balao1.setOnClickListener {
            val teste:Int = 1
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao1.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao2.setOnClickListener {
            val teste:Int = 1
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao1.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao3.setOnClickListener {
            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao1.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao4.setOnClickListener {
            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao1.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao5.setOnClickListener {
            val teste:Int = 5
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao1.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(activity,"Good Lucky", Toast.LENGTH_SHORT).show()
        startActivity(Intent(activity, PerguntaActivity::class.java))
    }
    
    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(activity,"Thank You", Toast.LENGTH_SHORT).show()
    }

    fun alertBalaoConcluido(msg: String){
        val builder = AlertDialog.Builder(activity)

        with(builder) {
            setMessage(msg)
            setNegativeButton("Close", negativeButtonClick)
            show()
        }
    }

    fun alertBalaoNaoConcluido(msg: String){
        val builder = AlertDialog.Builder(activity)

        with(builder) {
            setMessage(msg)
            setPositiveButton("START", DialogInterface.OnClickListener(function = this@DesafiosFragment.positiveButtonClick))
            setNegativeButton("Next Time", negativeButtonClick)
            show()
        }
    }

    companion object {
        fun newInstance() = DesafiosFragment()
    }

}

