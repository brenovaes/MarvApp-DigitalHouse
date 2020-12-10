package com.digitalhouse.br.marvelapp.ui.hqs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.comics.SeriesCo
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.service.RepositoryComics
import kotlinx.coroutines.launch

class ComicsViewModel(val serviceComics: RepositoryComics) : ViewModel() {

    var retornoComicsSeries = MutableLiveData<ResSeries>()
    var retornoComic = MutableLiveData<ResComics>()

        var retornoComicsEvents = MutableLiveData<ResEvents>()
//    var retornoCreatorStories = MutableLiveData<ResCreators>()
    var retornoComicsCreator = MutableLiveData<ResCreators>()
    var retornoComicsCharacters = MutableLiveData<ResCharacters>()
    var idSerie = 0


    fun getComic(id: Int) {
        try {
            viewModelScope.launch {
                retornoComic.value = serviceComics.getComicRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )

//                Log.i("getCharacter", retornoComics.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacter", e.toString())
        }
    }


    fun getCreatorsComic(id: Int) {
        viewModelScope.launch {
            retornoComicsCreator.value = serviceComics.getComicCreatorsRepo(
                id,
                0,
                10,
                "1601900859",
                "da0b41050b1361bf58011d9e4bb93ec3",
                "cc144618fe69492faf88410cc664f62e"
            )
        }
    }

    fun getCharactersComic(id: Int) {
        viewModelScope.launch {
            retornoComicsCharacters.value = serviceComics.getComicCharactersRepo(
                id,
                0,
                10,
                "1601900859",
                "da0b41050b1361bf58011d9e4bb93ec3",
                "cc144618fe69492faf88410cc664f62e"
            )
        }
    }

    fun getSeriesComic(id: Int) {
        try {
            viewModelScope.launch {
                retornoComicsSeries.value = serviceComics.getComicSeriesRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
            }
        } catch (e: Exception) {
            Log.i("getSeriesComic", e.toString())
            retornoComicsSeries.value == null
        }
    }

    fun getEventsComic(id: Int) {
        try {
            viewModelScope.launch {
                retornoComicsEvents.value = serviceComics.getComicEventsRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
            }
        } catch (e: Exception) {
            Log.i("getSeriesComic", e.toString())
            retornoComicsSeries.value == null
        }
    }


}

