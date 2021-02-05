package com.digitalhouse.br.marvelapp.ui.favoritos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.digitalhouse.br.marvelapp.models.*
import com.google.firebase.firestore.CollectionReference

class FavoritoViewModel( val crFCo: CollectionReference,
                         val crFCh:CollectionReference,
                         val crFCr:CollectionReference):ViewModel() {


    var resListFavCr = MutableLiveData<ArrayList<UserFav?>>()
    var listFavCr = ArrayList<UserFav?>()

    var resListFavCh = MutableLiveData<ArrayList<UserFav?>>()
    var listFavCh = ArrayList<UserFav?>()

    var resListFavCo = MutableLiveData<ArrayList<UserFav?>>()
    var listFavCo = ArrayList<UserFav?>()




    fun getFavCr(userId:String){
        crFCr.whereEqualTo("idUser", userId).get().addOnSuccessListener { documents ->
            for (document in documents) {
                var creator = document.toObject(UserFavCreatorFrag::class.java)
                listFavCr.add(UserFav(creator.idUser, creator.idCreator, Fav(creator.favCreator.name,
                        creator.favCreator.extension,
                        creator.favCreator.path,
                        "Creator")))
            }
            resListFavCr.value = listFavCr

        }

    }



    fun getFavCh(userId:String){
        crFCh.whereEqualTo("idUser", userId).get().addOnSuccessListener { documents ->
            for (document in documents) {
                var character = document.toObject(UserFavCharacterFrag::class.java)
                listFavCh.add(UserFav(character.idUser, character.idCharacter, Fav(character.favCharacter.name,
                        character.favCharacter.extension,
                        character.favCharacter.path,
                        "Character")))

            }


        }
        resListFavCh.value = listFavCh

    }

    fun getFavCo(userId:String){
        crFCo.whereEqualTo("idUser", userId).get().addOnSuccessListener { documents ->
            for (document in documents) {
                var comic = document.toObject(UserFavComicFrag::class.java)
                listFavCo.add(UserFav(comic.idUser, comic.idComic, Fav(comic.favComic.name,
                        comic.favComic.extension,
                        comic.favComic.path,
                        "Comic")))

            }

        }
        resListFavCo.value = listFavCo


    }
}