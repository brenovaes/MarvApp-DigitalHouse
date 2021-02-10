package com.digitalhouse.br.marvelapp.ui.quiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digitalhouse.br.marvelapp.models.Pontuacao
import com.digitalhouse.br.marvelapp.models.Trilha
import com.digitalhouse.br.marvelapp.models.UserFavComic
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query

class QuizViewModel(val crTr1: CollectionReference, val crPont: CollectionReference) : ViewModel() {

    var retornoQuiz = MutableLiveData<Trilha>()
    var checkH = MutableLiveData<Boolean>()
    var pontuacao = MutableLiveData<Pontuacao>()
    var pontuacaoRanking = MutableLiveData<ArrayList<Pontuacao>>()


    fun getPerg(id: String) {
        crTr1.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoQuiz.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun checkHancking(userEmail: String) {
        Log.i("CHECK H CHAMOU", checkH.value.toString())

        crPont.whereEqualTo("userEmail", userEmail).get().addOnSuccessListener { documents ->
            for (document in documents) {
                var pont = document.toObject(Pontuacao::class.java)
                pontuacao.value = pont
                checkH.value = true
                Log.i("CHECK H TRUE FINAL", checkH.value.toString())
            }
        }
    }

    fun addPontos(pontuacao: Pontuacao) {
        Log.i("ADD", "ENTROU")

        crPont.document().set(pontuacao)
    }

    fun update(userEmail: String, pontos: Int) {
        crPont.whereEqualTo("userEmail", userEmail).get().addOnSuccessListener { documents ->
            documents.forEach {
                it.reference.update(
                    "userEmail", userEmail,
                    "pontos", pontos
                )
            }
        }

    }

    fun getPontuacao() {
        var lista = arrayListOf<Pontuacao>()

        crPont.orderBy("pontos", Query.Direction.DESCENDING).get().addOnSuccessListener {
            it.forEach {
                var pessoa = it.toObject(Pontuacao::class.java)
                lista.add(pessoa)
            }

            pontuacaoRanking.value = lista

        }

    }

}