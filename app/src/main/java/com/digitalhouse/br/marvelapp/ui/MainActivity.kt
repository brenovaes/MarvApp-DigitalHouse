package com.digitalhouse.br.marvelapp.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.creators.Results
import com.digitalhouse.br.marvelapp.service.serviceB
import com.digitalhouse.br.marvelapp.service.serviceCh
import com.digitalhouse.br.marvelapp.service.serviceCo
import com.digitalhouse.br.marvelapp.service.serviceCr



class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(serviceCr, serviceCh, serviceCo, serviceB) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retornoCreator = arrayListOf<Results>()
//        var retornoCh = MutableLiveData<BaseCh>()
//        var retornoCo = MutableLiveData<BaseCo>()


        var iCr = 30
        var iCh = 1010354
        var iCo = 10021
//                82970


        // FUNÇÕES DE CREATORS
//        viewModel.getAllCreators()
//        viewModel.getCreatorComics(iCr)
//        viewModel.getCreatorSeries(iCr)
//        viewModel.getCreatorStories(iCr)
//        viewModel.getCreatorEvents(iCr)
//
        // FUNÇÕES DE CHARACTERS
//        viewModel.getAllCharacters()
//        viewModel.getCharacterComics(iCh)
//        viewModel.getCharacterEvents(iCh)
//        viewModel.getCharacterSeries(iCh)
//        viewModel.getCharacterStories(iCh)

//        viewModel.getAllComics()
//
//        viewModel.getComicCharacters(iCo)
//        viewModel.getComicEvents(iCo)
        viewModel.getComicCreator(iCo)

        // FUNÇÕES DE BUSCA COMICS CHARACTERS CREATORS
//        viewModel.getComics()
//        viewModel.getCharacters()
//        viewModel.getCreators()


    }
}