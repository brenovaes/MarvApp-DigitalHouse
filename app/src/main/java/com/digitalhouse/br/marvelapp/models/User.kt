package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var username: String = "",
    var password: String = "",
    var email: String = ""){
    var positionQuiz: Int = 0
}