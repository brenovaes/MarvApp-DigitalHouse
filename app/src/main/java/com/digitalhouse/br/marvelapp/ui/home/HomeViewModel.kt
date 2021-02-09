package com.digitalhouse.br.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.sugest.ResSugestao
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.HeroDay
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.Suggestions
import com.digitalhouse.br.marvelapp.service.*
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class HomeViewModel(
        val serviceCharacters: RepositoryCharacters,
        val serviceSugestao: RepositorySugestao,
        val repositoryDB: RepositoryHero,
        val repositoryHistory: RepositoryHistory,
        val repositorySuggestions: RepositorySuggestions,
        val crH: CollectionReference
) : ViewModel() {

    var retornoHeroiDia = MutableLiveData<ResCharacters>()
    var retornoAllComics = MutableLiveData<ResComics>()
    var retornoAllCharacters = MutableLiveData<ResCharacters>()
    var retornoAllCreators = MutableLiveData<ResCreators>()
    var retornoResult = MutableLiveData<ResSugestao>()
    var retornoChar = MutableLiveData<ResCharacters>()
    var retornoCrea = MutableLiveData<ResCreators>()
    var retornoCom = MutableLiveData<ResComics>()
    var retornoHeroDB = MutableLiveData<Boolean>()

    var retornoHistory = MutableLiveData<List<HistoryDB>>()
    var retornoSuggestions = MutableLiveData<List<Suggestions>>()


    var retornodataHeroDB = MutableLiveData<String>()

    var characterSavedDB = MutableLiveData<Characters>()

    var docHeroDay = MutableLiveData<Boolean>()
    var retornoHeroDaySavedF = MutableLiveData<HeroDay>()
    var infoHeroD = MutableLiveData<HeroDay>()


    fun getCharacter() {
        var id = random()

        try {
            viewModelScope.launch {
                retornoHeroiDia.value = serviceCharacters.getAllCharacterRepo(
                        id,
                        1,
//                        "1601900859",
//                        "da0b41050b1361bf58011d9e4bb93ec3",
//                        "cc144618fe69492faf88410cc664f62e"

                )

                var idC = retornoHeroiDia.value!!.data.results[0].id
                var name = retornoHeroiDia.value!!.data.results[0].name
                var extension = retornoHeroiDia.value!!.data.results[0].thumbnail.extension
                var path = retornoHeroiDia.value!!.data.results[0].thumbnail.path
                var comics = retornoHeroiDia.value!!.data.results[0].comics.available
                var series = retornoHeroiDia.value!!.data.results[0].series.available
                var stories = retornoHeroiDia.value!!.data.results[0].stories.available
                var dateN = LocalDate.now()
//                var dateN = transDate(LocalDate.now())

//                addHero(idC, name, extension, path, comics, series, stories, dateN.toString())
//                addHeroDayF(idC, name, extension, path, comics, series, stories, dateN.toString())

                infoHero(idC, name, extension, path, comics, series, stories, dateN.toString())


//                transDate(dateN.toString())


//                Log.i("getCharacter", retornoHeroiDia.value.toString())
            }

        } catch (e: Exception) {
            Log.i("getCharacter", e.toString())
        }

    }


    fun delHeroDB() {
        viewModelScope.launch {
            repositoryDB.deleteHeroDay()
        }
    }

    fun getAllH() {
        viewModelScope.launch {
            retornoHeroDB.value = repositoryDB.getAll()== null
            if (retornoHeroDB.value != null)
                characterSavedDB.value = repositoryDB.getAll()

        }
    }

    fun addHeroDB(idC: Int, name: String, extension: String, path: String, comics: Int, series: Int, stories: Int, dateN: String) {
        viewModelScope.launch {
            repositoryDB.addHeroDay(Characters(idC, 0, name, extension, path, comics, series, stories, dateN))
        }
    }

    //Pega data que o heroi foi gerado
    fun getHDayDB() {
        viewModelScope.launch {
            retornodataHeroDB.value = repositoryDB.getHeroDay()
        }
    }

    //    fun getIdH():Int{
//        return id
//    }
//
    fun compareDate(dateNow: LocalDate, dateSaved: String): Boolean {

        var dateN = dateNow.toString()
        if (dateN > dateSaved) {

            Log.i("COMPARE", "DATA Maior")
            return true
        } else {
            Log.i("COMPARE", "DATA n é maior")
            return false
        }


        Log.i("DATAS", dateN + " " + dateSaved)


    }

    fun transDate(date: String?):LocalDate?{
        var dataData: LocalDate?
        if (date != null){
            dataData = LocalDate.parse(date)

        }else{
            dataData = null
        }
        Log.i("TESTE DATA",  " $dataData ")
        return dataData
    }

    fun compareDateHeroD(dateHeroFirebase: String, dateHeroDB: String):Boolean{
        var dataFirebase = transDate(dateHeroFirebase)
        var dataDB = transDate(dateHeroDB)
        return dataFirebase!! < dataDB
    }

    fun random(): Int {
        var from = 1
        var to = 1492
        val rand = Random()
        var id = rand.nextInt(to - from) + from

//        CoroutineScope()delay(1000)

        return id
    }

    fun getAllHistory() {
        viewModelScope.launch {
            retornoHistory.value = repositoryHistory.getAllHistoryTask()
        }
    }

    fun getAllSuggestions() {
        viewModelScope.launch {
            retornoSuggestions.value = repositorySuggestions.getAllSuggestionsTask()
        }
    }

    fun updateSuggestions() {
        viewModelScope.launch {
            repositorySuggestions.updateNewSuggestionTask()
        }
    }

    fun update() {
        viewModelScope.launch {
            repositoryHistory.updateNewHistoryTask()
        }

    }

    fun checkHeroDayF() {
        crH.get().addOnSuccessListener { documents ->
            docHeroDay.value = documents.isEmpty
            if (documents.isEmpty) {
                Log.i("FIREBASEHERO", "empty")
            } else {
                documents.forEach {
                    Log.i("FIREBASEHERO", it.toString())
                }
            }

//            if ( crH.get().result != null)
//                Log.i("FIREBASEHERO", "VAZIO")
//            else
//                Log.i("FIREBASEHERO", "preenchido")

        }


    }

    fun addHeroDayF(hero: HeroDay) {
        crH.get().addOnSuccessListener { documents ->
            if (documents.isEmpty)
                crH.document().set(hero)
        }
    }

    fun getHeroDayF() {
        viewModelScope.launch {
            crH.get().addOnSuccessListener { documents ->
                var hero = documents.documents[0]
                retornoHeroDaySavedF.value = hero.toObject(HeroDay::class.java)
            }
        }

    }

    fun updateHeroDayF(hero: HeroDay) {

//DELETE
//        crH.get().addOnSuccessListener {
//            it.forEach {
//                it.reference.delete()
//            }
//        }

//        adiciona
//        crH.get().addOnSuccessListener { documents ->
//            if(documents.isEmpty)
//                crH.document().set( HeroDay(1,"", "", "", 1, 1,1, "t") )
//        }

        crH.get().addOnSuccessListener { documents ->
            documents.forEach {
                it.reference.update("idCharacter", hero.idCharacter, "name", hero.name,
                        "extension", hero.extension,
                        "path", hero.path,
                        "comics", hero.comics,
                        "series", hero.series,
                        "stories", hero.stories,
                        "dateT", hero.dateT)
            }
        }


    }

    fun infoHero(idC: Int, name: String, extension: String, path: String, comics: Int, series: Int, stories: Int, dateN: String) {
        infoHeroD.value = HeroDay(idC, name, extension, path, comics, series, stories, dateN)
    }


//    fun getAllCreatorsSugestao() {
//        viewModelScope.launch {
//                    retornoCrea.value = serviceSugestao.getCreatorsRepoS(
//                        0,
//                        20,
//                        "1601900859",
//                        "da0b41050b1361bf58011d9e4bb93ec3",
//                        "cc144618fe69492faf88410cc664f62e"
//                    )
//        }
//    }
//
//    fun getAllCharactersSugestao() {
//        viewModelScope.launch {
//                    retornoChar.value = serviceSugestao.getCharactersRepoS(
//                        0,
//                        20,
//                        "1601900859",
//                        "da0b41050b1361bf58011d9e4bb93ec3",
//                        "cc144618fe69492faf88410cc664f62e"
//                    )
//        }
//    }
//
//    fun getAllComicsSugestao() {
//        viewModelScope.launch {
//                    retornoCom.value = serviceSugestao.getComicsRepoS(
//                        1,
//                        20,
//                        "1601900859",
//                        "da0b41050b1361bf58011d9e4bb93ec3",
//                        "cc144618fe69492faf88410cc664f62e"
//                    )
//        }
//    }


}


