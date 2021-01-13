package com.digitalhouse.br.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.sugest.ResSugestao
import com.digitalhouse.br.marvelapp.service.RepositoryBusca
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import com.digitalhouse.br.marvelapp.service.RepositorySugestao
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    val serviceCharacters: RepositoryCharacters,
    val serviceSugestao: RepositorySugestao
) : ViewModel() {

    var retornoHeroiDia = MutableLiveData<ResCharacters>()
    var retornoAllComics = MutableLiveData<ResComics>()
    var retornoAllCharacters = MutableLiveData<ResCharacters>()
    var retornoAllCreators = MutableLiveData<ResCreators>()
    var retornoResult = MutableLiveData<ResSugestao>()
    var retornoChar = MutableLiveData<ResCharacters>()
    var retornoCrea = MutableLiveData<ResCreators>()
    var retornoCom = MutableLiveData<ResComics>()

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

//                Log.i("getCharacter", retornoHeroiDia.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCharacter", e.toString())
        }
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


