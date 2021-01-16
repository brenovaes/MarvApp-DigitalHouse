package com.digitalhouse.br.marvelapp.ui.criadores

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.events.ResEvents
import com.digitalhouse.br.marvelapp.entities.series.ResSeries
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryCreators
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryHistory
import com.digitalhouse.br.marvelapp.service.RepositoryImplHistory
import kotlinx.coroutines.launch

class CreatorsViewModel(
    val serviceCreators: RepositoryCreators,
    val repositoryHistory: RepositoryHistory
) : ViewModel() {

    var retornoCreator = MutableLiveData<ResCreators>()
    var retornoCreatorSeries = MutableLiveData<ResSeries>()
    var retornoCreatorComics = MutableLiveData<ResComics>()
    var retornoCreatorEvents = MutableLiveData<ResEvents>()
    //var retornoCreatorStories = MutableLiveData<ResCreators>()


    fun getCreator(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreator.value = serviceCreators.getCreatorRepo(
                    id,
                    0,
                    10,
                    "1601900859",
                    "da0b41050b1361bf58011d9e4bb93ec3",
                    "cc144618fe69492faf88410cc664f62e"
                )
                var nome = retornoCreator.value!!.data.results[0].fullName
                var path = retornoCreator.value!!.data.results[0].thumbnail.path
                var extension = retornoCreator.value!!.data.results[0].thumbnail.extension
                var tipo = "creator"
                populateCreatorHistory(HistoryDB(id, nome, extension, path, tipo))

                Log.i("getCreator", retornoCreator.value.toString())
            }
        } catch (e: Exception) {
            Log.i("getCreator", e.toString())
        }
    }

    fun getCreatorComics(id: Int) {
        try {
            viewModelScope.launch {
                retornoCreatorComics.value = serviceCreators.getCreatorComicsRepo(
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
                retornoCreatorSeries.value = serviceCreators.getCreatorSeriesRepo(
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
                retornoCreatorEvents.value = serviceCreators.getCreatorEventsRepo(
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

    fun populateCreatorHistory(creator: HistoryDB) {
        viewModelScope.launch {
            repositoryHistory.addHistoryTask(creator)
        }
    }


}