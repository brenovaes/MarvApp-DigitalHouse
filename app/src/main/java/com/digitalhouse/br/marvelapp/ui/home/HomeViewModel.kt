package com.digitalhouse.br.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.characters.ResCharacters
import com.digitalhouse.br.marvelapp.service.RepositoryCharacters
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel (val serviceCharacters: RepositoryCharacters): ViewModel() {

    var retornoHeroiDia = MutableLiveData<ResCharacters>()

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

    fun random(): Int{
        var from = 1
        var to = 1942
        val rand = Random()
        return rand.nextInt(to - from) + from
    }



}