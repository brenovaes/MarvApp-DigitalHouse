package com.digitalhouse.br.marvelapp.ui.quiz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.models.PontosTrilhas
import com.digitalhouse.br.marvelapp.models.Pontuacao
import com.digitalhouse.br.marvelapp.models.Trilha
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(val crTri1: CollectionReference,
                    val crTri2: CollectionReference,
                    val crTri3:CollectionReference,
                    val crTri4: CollectionReference,
                    val crPontosTr: CollectionReference,
                    val crHank: CollectionReference) : ViewModel() {

    var retornoTrilha01= MutableLiveData<Trilha>()
    var retornoTrilha02= MutableLiveData<Trilha>()
    var retornoTrilha03= MutableLiveData<Trilha>()
    var retornoTrilha04= MutableLiveData<Trilha>()
    var checkH = MutableLiveData<Boolean>()
    var pontuacao = MutableLiveData<Pontuacao>()
    var pontuacaoRanking = MutableLiveData<ArrayList<Pontuacao>>()

    var emaill = FirebaseAuth.getInstance().currentUser!!.email!!
    var namee = FirebaseAuth.getInstance().currentUser!!.displayName!!

    fun getPergTrilha01(id: String) {
        crTri1.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoTrilha01.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun getPergTrilha02(id: String) {
        crTri2.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoTrilha02.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun getPergTrilha03(id: String) {
        crTri3.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoTrilha03.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun getPergTrilha04(id: String) {
        crTri4.get().addOnSuccessListener {
            it.forEach { document ->
                if (document.id == id)
                    retornoTrilha04.value = document.toObject(Trilha::class.java)
            }
        }
    }

    fun checkHancking(userEmail: String) {

        viewModelScope.launch {
            Log.i("CHECK H CHAMOU FORA", checkH.value.toString())

            crPontosTr.whereEqualTo("emailUser", userEmail).whereEqualTo("username", "Thalita Neri").get().addOnSuccessListener  { documents ->
                Log.i("CHECK USER", userEmail)

                if (!documents.isEmpty){
                    checkH.value = true
                    Log.i("CHECK H TRUE FINAL DENTRO", checkH.value.toString())
                }else{

                    addPontos(emaill, namee, 0,0)
                }
            }

        }

    }

    fun addPontos(email: String, username: String, trilha: Int, pontos: Int) {
        viewModelScope.launch {
            Log.i("ADD", "ENTROU")

            when(trilha){
                1 ->  crPontosTr.document().set(PontosTrilhas(email, username,  ptrilha1= pontos))
                2 ->  crPontosTr.document().set(PontosTrilhas(email, username,  ptrilha2= pontos))
                3 ->  crPontosTr.document().set(PontosTrilhas(email, username,  ptrilha3= pontos))
                4 ->  crPontosTr.document().set(PontosTrilhas(email, username,  ptrilha4= pontos))
                else -> crPontosTr.document().set(PontosTrilhas(email,username))

            }


        }

    }


    //o update ta atualizando tudo, ver como somar as trilhas sem uma interferir na outra
    //comparar se ponto anterior é maior ou menor, ficar com o maior
    fun update(userEmail: String, pontos: Int, trilha: Int) {
        viewModelScope.launch {

            var fieldName = "ptrilha${trilha}"
            Log.i("UPDATE NAMEField", "$fieldName")

            crPontosTr.whereEqualTo("emailUser", userEmail).whereEqualTo("username", "Thalita Neri").get().addOnSuccessListener { documents ->

//        crPontosTr.whereArrayContains("emailUser", userEmail).get().addOnSuccessListener { documents ->
//        crPontosTr.whereEqualTo("emailUser", userEmail).get().addOnSuccessListener { documents ->
                documents.forEach {
                    it.reference.update(
                            "$fieldName", pontos
                    )
                }
            }
        }


    }

    fun getPontuacao() {
        var lista = arrayListOf<Pontuacao>()

        crPontosTr.orderBy("pontos", Query.Direction.DESCENDING).get().addOnSuccessListener {
            it.forEach {
                var pessoa = it.toObject(Pontuacao::class.java)
                lista.add(pessoa)
            }

            pontuacaoRanking.value = lista

        }

    }

}