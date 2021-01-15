package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

//Caso n ponha o update, deletar o posit
@Entity(tableName = "characters")
data class Characters(
    @PrimaryKey(autoGenerate = false)
    val idCharacter: Int,
    val posit: Int = 0,
    val name: String,
    val extension: String,
    val path: String,
    val comics: Int,
    val series: Int,
    val stories: Int,
    val dateT:String
)