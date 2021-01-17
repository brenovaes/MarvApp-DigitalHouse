package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suggestions")
class Suggestions(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    var nome: String,
    val extension: String,
    val path: String,
    var tipo: String
    ) {
    }