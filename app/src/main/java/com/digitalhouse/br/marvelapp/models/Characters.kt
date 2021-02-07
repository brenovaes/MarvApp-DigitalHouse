package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.digitalhouse.br.marvelapp.Converters
import java.sql.Date
import java.time.LocalDate

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
    val dateT:String = " "
)