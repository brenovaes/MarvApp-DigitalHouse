package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryDB(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    var nome: String,
    val extension: String,
    val path: String,
    var tipo: String
    ) {
}