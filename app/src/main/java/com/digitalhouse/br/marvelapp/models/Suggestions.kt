package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "suggestions", indices = arrayOf(Index(value = ["tipo", "id"], unique = true)))
class Suggestions(
    val id:Int,
    var nome: String,
    val extension: String,
    val path: String,
    var tipo: String
    ) {
    @PrimaryKey(autoGenerate = true)
    var idS = 0
    }