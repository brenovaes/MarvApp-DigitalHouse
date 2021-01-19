package com.digitalhouse.br.marvelapp.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "history", indices = arrayOf(Index(value = ["type", "id"], unique = true)))
data class HistoryDB(
//    @PrimaryKey(autoGenerate = false)
    val id:Int,
    var nome: String,
    val extension: String,
    val path: String,
    var type: String
    ) {
    @PrimaryKey (autoGenerate = true)
    var idC = 0
}
