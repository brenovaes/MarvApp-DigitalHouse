package com.digitalhouse.br.marvelapp.ui.busca

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.service.RepositoryBusca
import com.digitalhouse.br.marvelapp.service.serviceCr
import kotlinx.coroutines.launch

class BuscaViewModel(val serviceBusca: RepositoryBusca):ViewModel() {

    var retornoAllComics = MutableLiveData<ResComics>()
    var retornoAllCharacters = MutableLiveData<ResCharacters>()
    var retornoAllCreators = MutableLiveData<ResCreators>()


    fun getAllCreatorsBusca() {
        try {
            viewModelScope.launch {
                retornoAllCreators.value = serviceBusca.getCreatorsRepo(
                    0,
                    20,
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

    fun getAllCharactersBusca() {
        try {
            viewModelScope.launch {
                retornoAllCharacters.value = serviceBusca.getCharactersRepo(
                    0,
                    20,
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

    fun getAllComicsBusca(){
        try {
            viewModelScope.launch {
                retornoAllComics.value = serviceBusca.getComicsRepo(
                    1,
                    20,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getComics", retornoAllComics.value.toString())
            }
        }catch (e: Exception){
            Log.i("getComics", e.toString())
        }
    }
}