package com.digitalhouse.br.marvelapp.ui.personagens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import kotlinx.coroutines.launch


class CharactersViewModel(val serviceCharacters: RepositoryCharacters): ViewModel() {

    var retornoCharacter = MutableLiveData<ResCharacters>()
    var retornoCharactersComic = MutableLiveData<ResComics>()
    var retornoCharactersEvents = MutableLiveData<ResEvents>()
    var retornoCharactesSeries = MutableLiveData<ResSeries>()
//    var retornoCharactersSerie = MutableLiveData<ResComics>()
//    var retornoCharactersEvents = MutableLiveData<ResComics>()


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


}

