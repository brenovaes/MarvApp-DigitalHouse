package com.digitalhouse.br.marvelapp.ui.quiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digitalhouse.br.marvelapp.models.Pontuacao
import com.digitalhouse.br.marvelapp.models.Trilha
import com.digitalhouse.br.marvelapp.models.UserFavComic
import com.google.firebase.firestore.CollectionReference

class QuizViewModel(val crTr1: CollectionReference, val crPont: CollectionReference) : ViewModel() {

    var retornoQuiz = MutableLiveData<Trilha>()
    var checkH = MutableLiveData<Boolean>()
    var pontuacao = MutableLiveData<Pontuacao>()


    fun getPerg(id: String) {
        crTr1.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoQuiz.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun checkHancking(userEmail: String) {
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
            it.reference.update("userEmail", userEmail,
                    "pontos", pontos)
        }
    }

}

fun getPontuacao(path: String) {
    crPont.document(path).get().addOnSuccessListener {
        pontuacao.value = it.toObject(Pontuacao::class.java)

    }

//        crPont.get().addOnSuccessListener {
//            it.forEach {
//                if (it.get("userEmail") == userEmail) {
//                    pontuacao.value = it.toObject(Pontuacao::class.java)
//                    Log.i("GET PONT", pontuacao.value!!.userEmail)
//                }
//            }
//        }
}

}