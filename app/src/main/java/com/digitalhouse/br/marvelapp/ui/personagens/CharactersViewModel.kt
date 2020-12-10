package com.digitalhouse.br.marvelapp.ui.personagens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import com.digitalhouse.br.marvelapp.service.RepositoryCreators
import kotlinx.coroutines.launch


class CharactersViewModel(val serviceCharacters: RepositoryCharacters): ViewModel() {

    //    var retornoCreatorSeries = MutableLiveData<ResCreators>()
    var retornoCharacterComics = MutableLiveData<ResCharacters>()
//    var retornoCreatorEvents = MutableLiveData<ResCreators>()
//    var retornoCreatorStories = MutableLiveData<ResCreators>()
//    var retornoCreator = MutableLiveData<ResCreators>()


    fun getCharacter(id: Int) {
        try {
            viewModelScope.launch {
                retornoCharacterComics.value = serviceCharacters.getCharacterRepo(
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

}

