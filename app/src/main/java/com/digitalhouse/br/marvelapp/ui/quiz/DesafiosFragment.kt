package com.digitalhouse.br.marvelapp.ui.quiz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.*
import com.digitalhouse.br.marvelapp.R.color
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.balao_pergunta_quiz.view.*
import kotlinx.android.synthetic.main.barra_selos_quiz.view.*
import kotlinx.android.synthetic.main.fragment_desafios.*
import kotlinx.android.synthetic.main.fragment_desafios.infoQuiz
import kotlinx.android.synthetic.main.fragment_desafios.trilhaQuiz
import kotlinx.android.synthetic.main.info_quiz.view.*
import kotlinx.android.synthetic.main.trilha_quiz.view.*


class DesafiosFragment : Fragment() {

    val viewModelQuiz by viewModels<QuizViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return QuizViewModel(
                        crTri1,
                        crTri2,
                        crTri3,
                        crTri4,
                        crPontosTr,
                        crHank
                ) as T
            }
        }
    }

    lateinit var intentPergunta:Intent
    var trilha = 0
    var email = FirebaseAuth.getInstance().currentUser!!.email
    var user = FirebaseAuth.getInstance().currentUser!!.displayName

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//            viewModelQuiz.checkH.observe(viewLifecycleOwner){
//            if (viewModelQuiz.checkH.value == null || viewModelQuiz.checkH.value == false){
//                viewModelQuiz.addPontos(email!!, user!!,0, 0 )
//            }
//        }
        intentPergunta = Intent(activity, PerguntaActivity::class.java)
        return inflater.inflate(R.layout.fragment_desafios, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //inicialziar os balões
        inicializarBaloes()
//        viewModelQuiz.getPontuacao(email!!)
        viewModelQuiz.checkH.observe(viewLifecycleOwner){
            if (it == true){
                viewModelQuiz.pontuacao.observe(viewLifecycleOwner){
                    trilhaQuiz.balao1.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.destaqueVermelho))
                    trilhaQuiz.balao1.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
                    trilhaQuiz.balao1.tvPontuacaoBalao.text = it.pontos.toString()
                    trilhaQuiz.balao1.tvNumeroBalao.text = "1"
                    trilhaQuiz.balao1.tvNomeBalao.text = "Marvel History"

                    infoQuiz.tvPontuacao.text = it.pontos.toString()
                    infoQuiz.tvSelosConquistados.text = "1/6"
                }

            }else{
                trilhaQuiz.balao1.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
                trilhaQuiz.balao1.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
                trilhaQuiz.balao1.tvPontuacaoBalao.text = "0"
                trilhaQuiz.balao1.tvNumeroBalao.text = "1"
                trilhaQuiz.balao1.tvNomeBalao.text = "Marvel History"
            }
        }

        trilhaQuiz.balao1.setOnClickListener {
            intentPergunta.putExtra("trilha",1)
            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao2.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao2.setOnClickListener {
            intentPergunta.putExtra("trilha",2)

            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao2.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao3.setOnClickListener {
            intentPergunta.putExtra("trilha",3)
            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao3.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

        trilhaQuiz.balao4.setOnClickListener {
            intentPergunta.putExtra("trilha",4)
            val teste:Int = 2
            val msg: String
            if (teste == 1){
                msg = trilhaQuiz.balao4.tvNomeBalao.toString()
                alertBalaoConcluido(msg)
            }else{
                msg = "You haven't started this challenge yet"
                alertBalaoNaoConcluido(msg)
            }
        }

    }

    private fun inicializarBaloes() {

        barraSeloQuiz.cvBarraSeloConquistado1.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        barraSeloQuiz.cvBarraSeloConquistado2.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        barraSeloQuiz.cvBarraSeloConquistado3.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        barraSeloQuiz.cvBarraSeloConquistado4.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))

//        infoQuiz.tvPontuacao.text = "0"
//        infoQuiz.tvSelosConquistados.text = "0/5"

//        trilhaQuiz.balao1.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
//        trilhaQuiz.balao1.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
//        trilhaQuiz.balao1.tvPontuacaoBalao.text = "0"
//        trilhaQuiz.balao1.tvNumeroBalao.text = "1"
//        trilhaQuiz.balao1.tvNomeBalao.text = "Marvel History"

        trilhaQuiz.balao2.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        trilhaQuiz.balao2.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
        trilhaQuiz.balao2.tvPontuacaoBalao.text = "0"
        trilhaQuiz.balao2.tvNumeroBalao.text = "2"
        trilhaQuiz.balao2.tvNomeBalao.text = "Comics"

        trilhaQuiz.balao3.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        trilhaQuiz.balao3.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
        trilhaQuiz.balao3.tvPontuacaoBalao.text = "0"
        trilhaQuiz.balao3.tvNumeroBalao.text = "3"
        trilhaQuiz.balao3.tvNomeBalao.text = "Heroes"

        trilhaQuiz.balao4.cvBalao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaClaro))
        trilhaQuiz.balao4.cvPontuacao.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), color.cinzaEscuro))
        trilhaQuiz.balao4.tvPontuacaoBalao.text = "0"
        trilhaQuiz.balao4.tvNumeroBalao.text = "4"
        trilhaQuiz.balao4.tvNomeBalao.text = "Villains"
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Log.i("PASSANDO TRILHA", trilha.toString())
        Toast.makeText(activity,"Good Lucky", Toast.LENGTH_SHORT).show()
        startActivity(intentPergunta)

    }

    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(activity,"Thank You", Toast.LENGTH_SHORT).show()
    }


    fun alertBalaoNaoConcluido(msg: String){
        val builder = AlertDialog.Builder(activity)

        with(builder) {
            setMessage(msg)
            setPositiveButton("Start", DialogInterface.OnClickListener(function = this@DesafiosFragment.positiveButtonClick))
            setNegativeButton("Next Time", negativeButtonClick)
            show()
        }
    }

    fun alertBalaoConcluido(msg: String){
        val builder = AlertDialog.Builder(activity)
//        Log.i("DESAFIO FRAGMENT", "ALERTA BALAO CONCLUIDO")

        with(builder) {
            setMessage(msg)
            setNegativeButton("Close", negativeButtonClick)
            show()
        }
    }

    companion object {
        fun newInstance() = DesafiosFragment()
    }

}

