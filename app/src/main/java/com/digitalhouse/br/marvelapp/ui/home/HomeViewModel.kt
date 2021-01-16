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
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import com.digitalhouse.br.marvelapp.service.RepositoryHero
import com.digitalhouse.br.marvelapp.service.RepositoryHistory
import com.digitalhouse.br.marvelapp.service.RepositorySugestao
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class HomeViewModel(
    val serviceCharacters: RepositoryCharacters,
    val serviceSugestao: RepositorySugestao,
    val repositoryDB: RepositoryHero,
    val repositoryHistory: RepositoryHistory
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
    var retornoHistory = MutableLiveData<HistoryDB>()


    var retornodataHSaved = MutableLiveData<String>()

    var characterSaved = MutableLiveData<Characters>()


    fun getCharacter() {
        try {
            viewModelScope.launch {
                retornoHeroiDia.value = serviceCharacters.getAllCharacterRepo(
                    random(),
                    1,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"

                )

                var idC = retornoHeroiDia.value!!.data.results[0].id
                var name =  retornoHeroiDia.value!!.data.results[0].name
                var extension = retornoHeroiDia.value!!.data.results[0].thumbnail.extension
                var path = retornoHeroiDia.value!!.data.results[0].thumbnail.path
                var comics = retornoHeroiDia.value!!.data.results[0].comics.available
                var series = retornoHeroiDia.value!!.data.results[0].series.available
                var stories = retornoHeroiDia.value!!.data.results[0].stories.available
                var dateN = LocalDate.now()


                addHero(idC, name, extension, path, comics, series, stories, dateN.toString())

//                Log.i("getCharacter", retornoHeroiDia.value.toString())
            }

        } catch (e: Exception) {
            Log.i("getCharacter", e.toString())
        }

    }

    fun delHero(){
        viewModelScope.launch {
            repositoryDB.deleteHeroDay()
        }
    }

    fun getAllH(){
        viewModelScope.launch {
             retornoHeroDB.value = repositoryDB.getAll() == null
            if (retornoHeroDB.value != null)
                characterSaved.value = repositoryDB.getAll()

        }
    }

    fun addHero(idC: Int, name:String, extension:String, path:String, comics:Int, series: Int, stories:Int, dateN: String){
        viewModelScope.launch {
            repositoryDB.addHeroDay(Characters(idC,0, name, extension, path, comics, series, stories, dateN))
        }
    }

    //Pega data que o heroi foi gerado
    fun getHDay(){
      viewModelScope.launch {
          retornodataHSaved.value = repositoryDB.getHeroDay()
      }
    }

//    fun getIdH():Int{
//        return id
//    }
//
    fun compareDate(dateNow: LocalDate, dateSaved: String):Boolean {
        var dateN = dateNow.toString()
        if (dateN.equals(dateSaved)){

            Log.i("COMPARE", "DATA igual")
            return true
        }
        else{
            Log.i("COMPARE", "DATA n é igual")
            return false
        }


        Log.i("DATAS", dateN + " " + dateSaved)


    }

    fun random(): Int {
        var from = 1
        var to = 1942
        val rand = Random()
        return rand.nextInt(to - from) + from
    }



    fun getAllCreatorsSugestao() {
        viewModelScope.launch {
                    retornoCrea.value = serviceSugestao.getCreatorsRepoS(
                        0,
                        20,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                    )
        }
    }

    fun getAllCharactersSugestao() {
        viewModelScope.launch {
                    retornoChar.value = serviceSugestao.getCharactersRepoS(
                        0,
                        20,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                    )
        }
    }

    fun getAllComicsSugestao() {
        viewModelScope.launch {
                    retornoCom.value = serviceSugestao.getComicsRepoS(
                        1,
                        20,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                    )
        }
    }
}


