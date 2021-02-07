package com.digitalhouse.br.marvelapp.models

data class UserFavCharacter(val idUser:String = "", val idCharacter:Int = 0, var favCharacter: FavCharacter)

data class FavCharacter( var name: String = "",
                         val extension: String = "",
                         val path: String = "",
                         var type: String = "Character")

data class UserFavCreator(val idUser:String = "", val idCreator:Int = 0, var favCreator: FavCreator)

data class FavCreator(   var name: String = "",
                         val extension: String = "",
                         val path: String = "",
                         var type: String = "Creator")

data class UserFavComic(val idUser:String = "", val idComic:Int = 0, var favComic: FavComic)

data class FavComic(   var name: String = "",
                       val extension: String = "",
                       val path: String = "",
                       var type: String = "Creator")


data class UserFav(val idUser:String = "", val id:Int = 0, var fav: Fav)

data class Fav(        var name: String = "",
                       val extension: String = "",
                       val path: String = "",
                       var type: String = "")

data class UserFavCreatorFrag(val idUser:String = "", val idCreator:Int = 0, var favCreator: FavCreator = FavCreator("","", "",""))
data class UserFavCharacterFrag(val idUser:String = "", val idCharacter:Int = 0, var favCharacter: FavCharacter = FavCharacter("","", "",""))
data class UserFavComicFrag(val idUser:String = "", val idComic: Int = 0, var favComic: FavComic = FavComic("","", "",""))