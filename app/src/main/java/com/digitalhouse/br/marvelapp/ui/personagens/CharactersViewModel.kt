package com.digitalhouse.br.marvelapp.ui.personagens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.models.*
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import com.digitalhouse.br.marvelapp.service.RepositoryHistory
import com.digitalhouse.br.marvelapp.service.RepositorySuggestions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime


class CharactersViewModel(val serviceCharacters: RepositoryCharacters,
                          val repositoryHistory: RepositoryHistory,
                          val repositorySuggestions: RepositorySuggestions,
                          val crFCh:CollectionReference
): ViewModel() {

    var retornoCharacter = MutableLiveData<ResCharacters>()
    var retornoCharactersComic = MutableLiveData<ResComics>()
    var retornoCharactersEvents = MutableLiveData<ResEvents>()
    var retornoCharactesSeries = MutableLiveData<ResSeries>()

    var checkF = MutableLiveData<Boolean>()
    var checkC = MutableLiveData<Boolean?>()
    var checkIdC = MutableLiveData<Int>()




    fun getCharacter(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacter.value = serviceCharacters.getCharacterRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                var nome = retornoCharacter.value!!.data.results[0].name
                var path = retornoCharacter.value!!.data.results[0].thumbnail.path
                var extension = retornoCharacter.value!!.data.results[0].thumbnail.extension
                var tipo = "character"
                populateCharactersHistory(HistoryDB(id, nome, extension, path, tipo))

//                Log.i("getCreator", retornoCreator.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreator", e.toString())
        }
    }

    fun getCharactersComics(id: Int){
        try {
            viewModelScope.launch {
                retornoCharactersComic.value = serviceCharacters.getCharacterComicsRepo(
                        id,
                        0,
                        10,
                        "1601900859",
                         "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                //Log.i("getCharactersComics", retornoCharactersComic.value.toString())
                getResCharacterComics(retornoCharactersComic.value!!.data.results)
            }
        }catch (e: Exception){
            Log.i("getCharactersComics", e.toString())
        }
    }

   fun getCharactersEvents(id: Int){
        try{
            viewModelScope.launch {
                retornoCharactersEvents.value = serviceCharacters.getCharacterEventsRepo(
                        id,
                        0,
                        10,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                //Log.i("getCharactersEvents", retornoCharactersEvents.value.toString())
            }
        }catch (e: Exception){
            Log.i("getCharactersComics", e.toString())
        }
    }

    fun getCharacterSeries(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharactesSeries.value = serviceCharacters.getCharacterSeriesRepo(
                        id,
                        0,
                        10,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                //Log.i("getCharactersEvents", retornoCharactesSeries.value.toString())
            }
        }catch (e: Exception){
            Log.i("getCharactersEvents", e.toString())
        }
    }

    fun populateCharactersHistory(character: HistoryDB) {
        viewModelScope.launch {
            repositoryHistory.addHistoryTask(character)
        }
    }

    fun getResCharacterComics(listComics: ArrayList<ResultsCo>){
        if (listComics.isNotEmpty()) {
            var comic = listComics.random()
            var suggestion = Suggestions(comic.id, comic.title, comic.thumbnail.extension, comic.thumbnail.path, "comics")
            populateSuggestions(suggestion)
        }
    }

    fun populateSuggestions(suggestion: Suggestions) {
        viewModelScope.launch {
            repositorySuggestions.addSuggestionsTask(suggestion)
        }
    }

    fun checkFavoriteF(idCharacter:Int, userId:String){
        Log.i("FIREBASE", "ENTrOU check")

        crFCh.whereEqualTo("idUser",userId).whereEqualTo("idCharacter",idCharacter).get().addOnSuccessListener {
            it.documents.forEach {
                checkF.value = it.exists()
                checkIdC.value = 1
            }
        }
    }

    fun addCharacterFav(userFavCh: UserFavCharacter){
        crFCh.document().set(userFavCh)
        Log.i("FIREBASE ADD", userFavCh.idUser + " " + userFavCh.favCharacter.name)
    }

    fun deleteCharacterFav(userId: String, idCharacter: Int){
        crFCh.whereEqualTo("idUser",userId).whereEqualTo("idCharacter",idCharacter).get().addOnSuccessListener {
            it.documents.forEach {
                if( it.exists()){
                    it.reference.delete()
                }
            }
        }
    }

    fun checkCollection(){

        crFCh.get().addOnSuccessListener { documents ->
            checkC.value = documents.isEmpty
        }
    }

    fun deletIconCh(){
        checkIdC.value = 0

    }

    fun addIconCh(){
        checkIdC.value = 1
    }



}

