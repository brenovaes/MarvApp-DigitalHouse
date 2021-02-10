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
        Log.i("CHECK", "ENTrOU FUNÇÃO")
//                crPont.get().addOnSuccessListener { documents ->
//                    Log.i("CHECK TESTE ANTES FOR", checkH.value.toString())
//                    documents.forEach { document ->
//                        if (userEmail == document.get("userEmail")) {
//                            Log.i("CHECK H DENTRO DO IF", checkH.value.toString())
//                            var getP = document.reference.path
//                            var splitItem = getP.split("/")
//                            var itemPath = "/" + splitItem[1]
//                            var doc = crPont.document(itemPath).get().addOnSuccessListener {
//                                pontuacao.value = document.toObject(Pontuacao::class.java)
//                            }
//                        }
//                        Log.i("CHECK H FORA IF", checkH.value.toString())
//                    }
//                }

        crPont.whereEqualTo("userEmail", userEmail).get().addOnSuccessListener {

            it.forEach {
                pontuacao.value = it.toObject(Pontuacao::class.java)
                checkH.value = true

            }

            it.documents.forEach { document ->
                document.getData()?.entries?.forEach {
                    if (it.value == userEmail) {
                        checkH.value = true
                        getPontuacao(document.reference.path)
                        Log.i("CHECK H TRUE", userEmail.toString())
                    }

                }

            }


        Log.i("CHECK H TRUE FINAL", checkH.value.toString())
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