package com.digitalhouse.br.marvelapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.service.*
import kotlinx.coroutines.launch

class MainViewModel(val serviceCre: RepositoryCreators, val serviceCharacter: RepositoryCharacters, val serviceComic: RepositoryComics,
                    val serviceBusca: RepositoryBusca) : ViewModel() {

    var retornoAllCreators = MutableLiveData<ResCreators>()
    var retornoCreatorSeries = MutableLiveData<ResCreators>()
    var retornoCreatorComics = MutableLiveData<ResCreators>()
    var retornoCreatorEvents = MutableLiveData<ResCreators>()
    var retornoCreatorStories = MutableLiveData<ResCreators>()


    var retornoAllCharacters = MutableLiveData<ResCharacters>()
    var retornoCharacterSeries = MutableLiveData<ResCharacters>()
    var retornoCharacterComics = MutableLiveData<ResCharacters>()
    var retornoCharacterEvents = MutableLiveData<ResCharacters>()
    var retornoCharacterStories = MutableLiveData<ResCharacters>()


    var retornoAllComics = MutableLiveData<ResComics>()
    var retornoComicsCharacters = MutableLiveData<ResComics>()
    var retornoComicsSeries = MutableLiveData<ResComics>()
    var retornoComicsEvents = MutableLiveData<ResComics>()
    var retornoComicsStories = MutableLiveData<ResComics>()
    var retornoComicsCreators = MutableLiveData<ResComics>()

    var retornoCreators = MutableLiveData<ResCreators>()
    var retornoCharacters = MutableLiveData<ResCharacters>()
    var retornoComics = MutableLiveData<ResComics>()


    fun getAllCreators() {
        try {
            viewModelScope.launch {
                retornoAllCreators.value = serviceCr.getAllCreatorsRepo(
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("AllCreators", retornoAllCreators.value.toString())
            }
        } catch (e: Exception) {
            Log.i("AllCreators", e.toString())
        }


    }


    fun getCreatorComics(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreatorComics.value = serviceCre.getCreatorComicsRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )

                Log.i("getCreatorComics", retornoCreatorComics.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreatorComics", e.toString())
        }
    }


    fun getCreatorSeries(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreatorSeries.value = serviceCr.getCreatorSeriesRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCreatorSeries", retornoCreatorSeries.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreatorSeries", e.toString())
        }

    }


    fun getCreatorEvents(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreatorEvents.value = serviceCr.getCreatorEventsRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCreatorEvents", retornoCreatorEvents.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreatorEvents", e.toString())
        }
    }


    fun getCreatorStories(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreatorStories.value = serviceCr.getCreatorStoriesRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCreatorStories", retornoCreatorStories.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreatorStories", e.toString())
        }
    }

//    ..........................................................................................................
//    CHARACTERS CHAMADAS

    fun getAllCharacters() {
        try {
            viewModelScope.launch {
                retornoAllCharacters.value = serviceCharacter.getAllCharacters(
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getAllCharacters", retornoAllCharacters.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getAllCharacters", e.toString())
        }

    }


    fun getCharacterComics(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacterComics.value = serviceCharacter.getCharacterComicsRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCharacterComics", retornoCharacterComics.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacterComics", e.toString())
        }
    }


    fun getCharacterSeries(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacterSeries.value = serviceCharacter.getCharacterSeriesRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCharacterSeries", retornoCharacterSeries.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacterSeries", e.toString())
        }
    }


    fun getCharacterStories(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacterStories.value = serviceCharacter.getCharacterStoriesRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCharacterStories", retornoCharacterStories.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacterStories", e.toString())
        }
    }


    fun getCharacterEvents(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacterEvents.value = serviceCharacter.getCharacterEventsRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCharacterEvents", retornoCharacterEvents.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacterEvents", e.toString())
        }
    }

//    ..............................................................................................................
//        CHAMADAS DE COMICS


    fun getAllComics(){
        try {
            viewModelScope.launch {
                retornoAllComics.value = serviceComic.getAllComicsRepo(
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("AllComics", retornoAllComics.value.toString())
            }
        }catch (e: Exception){
            Log.i("AllComics", e.toString())
        }
    }


    fun getComicCharacters(id:Int){
        try {
            viewModelScope.launch {
                retornoComicsCharacters.value = serviceComic.getComicCharactersRepo(
                    id,
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComicCharacters", retornoComicsCharacters.value.toString())
            }
        }catch (e:Exception){
            Log.i("getComicCharacters", e.toString())
        }
    }


    fun getComicSeries(id:Int){
        try {
            viewModelScope.launch {
                retornoComicsSeries.value = serviceComic.getComicSeriesRepo(
                    id,
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComicSeries", retornoComicsSeries.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComicSeries", e.toString())
        }
    }

    fun getComicEvents(id:Int){
        try {
            viewModelScope.launch {
                retornoComicsEvents.value = serviceComic.getComicEventsRepo(
                    id,
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComicEvents", retornoComicsEvents.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComicEvents", e.toString())
        }
    }

    fun getComicStories(id:Int){
        try {
            viewModelScope.launch {
                retornoComicsStories.value = serviceComic.getComicStoriesRepo(
                    id,
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComicSeries", retornoComicsStories.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComicSeries", e.toString())
        }
    }

    fun getComicCreator(id:Int){
        try {
            viewModelScope.launch {
                retornoComicsCreators.value = serviceComic.getComicCreatorsRepo(
                    id,
                    1,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComicCreator", retornoComicsCreators.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComicCreator", e.toString())
        }
    }

    //    ..............................................................................................................
    //     CHAMADAS DE BUSCA

    fun getCreators(){
        try {
            viewModelScope.launch {
                retornoCreators.value = serviceBusca.getCreatorsRepo(
                        1,
                        10,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCreator", retornoComics.value.toString())
            }
        }catch (e: Exception){
            Log.i("getCreator", e.toString())
        }
    }

    fun getCharacters(){
        try {
            viewModelScope.launch {
                retornoCharacters.value = serviceBusca.getCharactersRepo(
                        1,
                        10,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCharacters", retornoCharacters.value.toString())
            }
        }catch (e: Exception){
            Log.i("getCharacters", e.toString())
        }
    }

    fun getComics(){
        try {
            viewModelScope.launch {
                retornoComics.value = serviceBusca.getComicsRepo(
                        1,
                        10,
                        "1601900859",
                        "da0b41050b1361bf58011d9e4bb93ec3",
                        "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComics", retornoComics.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComics", e.toString())
        }
    }



}