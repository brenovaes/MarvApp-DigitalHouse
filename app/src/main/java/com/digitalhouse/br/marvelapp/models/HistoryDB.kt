package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity

@Entity(tableName = "history")
data class HistoryDB(
    val id:Int,
    var nome: String,
    val extension: String,
    val path: String,
    var tipo: String
    ) {
}