package com.digitalhouse.br.marvelapp.models

data class UserFavCharacter(val idUser:String = "", val idCharacter:Int = 0, var favCharacter: FavCharacter)

data class FavCharacter( val idCharacter:Int = 0,
                         var name: String = "",
                         val extension: String = "",
                         val path: String = "",
                         var type: String = "Character")


data class IconH(var idUser: String = "", var idCharacter: Int = 0, var iconC:Int = 0)