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

    var pontosUser = MutableLiveData<Int>(0)
    var pontosTotalUser = MutableLiveData<Int>(0)


    var pontosTrilha01 = MutableLiveData<Int>()
    var pontosTrilha02 = MutableLiveData<Int>()
    var pontosTrilha03 = MutableLiveData<Int>()
    var pontosTrilha04 = MutableLiveData<Int>()
    var listaCheckSelo = MutableLiveData<Int>(0)


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

            crPontosTr.whereEqualTo("emailUser", userEmail).whereEqualTo("username", namee).get().addOnSuccessListener  { documents ->
                Log.i("CHECK USER", userEmail)

                if (!documents.isEmpty){
                    checkH.value = true
                    Log.i("CHECK H TRUE FINAL DENTRO", checkH.value.toString())

                }else{

                    addPontos(emaill, namee, 0,0)
                    addPontuacao()
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

            crPontosTr.whereEqualTo("emailUser", userEmail).whereEqualTo("username", namee).get().
            addOnSuccessListener { documents ->

//        crPontosTr.whereArrayContains("emailUser", userEmail).get().addOnSuccessListener { documents ->
//        crPontosTr.whereEqualTo("emailUser", userEmail).get().addOnSuccessListener { documents ->
                documents.forEach {
                    it.reference.update(
                            "$fieldName", pontos
                    )

//                    somaPontos(it.toObject(PontosTrilhas::class.java))
                }
            }

         updatePontuacao()
        }

    }

    fun getPontuacao() {
        var lista = arrayListOf<Pontuacao>()
        updatePontuacao()

        crHank.orderBy("pontos", Query.Direction.DESCENDING).get().addOnSuccessListener {
            it.forEach {
                var pessoa = it.toObject(Pontuacao::class.java)
                lista.add(pessoa)
            }

            pontuacaoRanking.value = lista

        }

    }

    fun addPontuacao() {

        crHank.document().set(Pontuacao(emaill,0, namee))
    }

    fun updatePontuacao(){
        somaPontos()
        crHank.whereEqualTo("userEmail", emaill).whereEqualTo("username", namee).get().addOnSuccessListener { documents ->
            documents.forEach { documentsPont ->
                documentsPont.reference.update(
                        "pontos", pontosTotalUser.value
                )
                Log.i("UPDATE PONTUACAO FOREACH doc", pontosTotalUser.value.toString())

            }
        }


    }

    fun somaPontos(){

        crPontosTr.whereEqualTo("emailUser", emaill).whereEqualTo("username", namee).get().
        addOnSuccessListener { documents ->
            documents.forEach {
                var pontosTrilhas = (it.toObject(PontosTrilhas::class.java))

                if ( pontosTrilhas.ptrilha1 != null){
                    pontosTotalUser.value = pontosTotalUser.value!! + pontosTrilhas.ptrilha1!!

                }
                if ( pontosTrilhas.ptrilha2 != null){
                    pontosTotalUser.value = pontosTotalUser.value!! + pontosTrilhas.ptrilha2!!

                }
                if ( pontosTrilhas.ptrilha3 != null){
                    pontosTotalUser.value = pontosTotalUser.value!! + pontosTrilhas.ptrilha3!!

                }
                if ( pontosTrilhas.ptrilha4 != null){
                    pontosTotalUser.value = pontosTotalUser.value!! + pontosTrilhas.ptrilha4!!

                }

            }
        }
            Log.i("SOMA1 ", pontosTotalUser.value.toString())


    }

    fun getPontosTrilhas(): ArrayList<Int?> {
        crPontosTr.whereEqualTo("emailUser", emaill).whereEqualTo("username", namee).get().
        addOnSuccessListener { documents ->
            documents.forEach {
                var pontosTrilhas = (it.toObject(PontosTrilhas::class.java))

                if ( pontosTrilhas.ptrilha1 != null){
                    pontosTrilha01.value = pontosTrilhas.ptrilha1!!

                }
                if ( pontosTrilhas.ptrilha2 != null){
                    pontosTrilha02.value = pontosTrilhas.ptrilha2!!
                }
                if ( pontosTrilhas.ptrilha3 != null){
                    pontosTrilha03.value = pontosTrilhas.ptrilha3!!

                }
                if ( pontosTrilhas.ptrilha4 != null){
                    pontosTrilha04.value = pontosTrilhas.ptrilha4!!

                }

            }
        }
        return arrayListOf<Int?>(pontosTrilha01.value, pontosTrilha02.value, pontosTrilha03.value, pontosTrilha04.value)
    }

    fun checkTrilhas(): ArrayList<Boolean?> {
        var listaPontos = getPontosTrilhas()
        var lista2 = arrayListOf<Boolean?>()

        listaPontos.forEach {
            if (it != 0){
                lista2.add(true)
            }else{
                lista2.add(null)
            }
        }
        return lista2
    }

    fun checkSelo(selo: Int){
        Log.i("CHECK SELO", "ENTROU")
        listaCheckSelo.value = listaCheckSelo.value!! + 1
        Log.i("CHECK SELO", "${listaCheckSelo.value}")

    }

}