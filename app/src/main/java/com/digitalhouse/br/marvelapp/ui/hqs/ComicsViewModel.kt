package com.digitalhouse.br.marvelapp.ui.hqs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.service.RepositoryComics
import kotlinx.coroutines.launch

class ComicsViewModel(val serviceComics: RepositoryComics): ViewModel() {

    //    var retornoCreatorSeries = MutableLiveData<ResCreators>()
    var retornoComic = MutableLiveData<ResComics>()
//    var retornoCreatorEvents = MutableLiveData<ResCreators>()
//    var retornoCreatorStories = MutableLiveData<ResCreators>()
    var retornoComicsCreator = MutableLiveData<ResCreators>()


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

    fun getCreatorsComic(id: Int){
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

}