package com.digitalhouse.br.marvelapp.models

data class UserFavCharacter(val idUser:String = "", val idCharacter:Int = 0, var favCharacter: FavCharacter)

data class FavCharacter( val idCharacter:Int = 0,
                         var name: String = "",
                         val extension: String = "",
                         val path: String = "",
                         var type: String = "Character")

data class UserFavCreator(val idUser:String = "", val idCreator:Int = 0, var favCreator: FavCreator)

data class FavCreator( val idCreator:Int = 0,
                         var name: String = "",
                         val extension: String = "",
                         val path: String = "",
                         var type: String = "Creator")

data class UserFavComic(val idUser:String = "", val idComic:Int = 0, var favComic: FavComic)

data class FavComic( val idComic:Int = 0,
                       var name: String = "",
                       val extension: String = "",
                       val path: String = "",
                       var type: String = "Creator")