package com.digitalhouse.br.marvelapp.ui.personagens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import kotlinx.coroutines.launch


class CharactersViewModel(val serviceCharacters: RepositoryCharacters): ViewModel() {

    var retornoCharacter = MutableLiveData<ResCharacters>()
    var retornoCharactersComic = MutableLiveData<ResComics>()
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
                retornoCharactersComic.value = this@CharactersViewModel.serviceCharacters.getCharacterComicsRepo(
                        id = id,
                        ps1 = 0,
                        ps2 = 10,
                        ps3 = "1601900859",
                        ps4 = "da0b41050b1361bf58011d9e4bb93ec3",
                        ps5 = "cc144618fe69492faf88410cc664f62e"
                )
                Log.i("getCreator", retornoCharactersComic.value.toString())
            }
        }catch (e: Exception){
            Log.i("getCharactersComics", e.toString())
        }
    }


}

