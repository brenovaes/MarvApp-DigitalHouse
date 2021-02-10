package com.digitalhouse.br.marvelapp.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digitalhouse.br.marvelapp.models.Trilha
import com.google.firebase.firestore.CollectionReference

class QuizViewModel(val crTr1:CollectionReference):ViewModel() {

    var retornoQuiz = MutableLiveData<Trilha>()


    fun getPerg(id: String){
        crTr1.get().addOnSuccessListener {
            it.forEach {document ->
                if (document.id == id)
                    retornoQuiz.value = document.toObject(Trilha::class.java)
            }
        }
    }
}