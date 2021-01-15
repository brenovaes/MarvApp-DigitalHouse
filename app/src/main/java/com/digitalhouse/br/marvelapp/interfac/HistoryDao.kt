package com.digitalhouse.br.marvelapp.interfac

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.HistoryDB

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    suspend fun getAllHistory(): List<HistoryDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(historyDB: HistoryDB)

    @Query ("SELECT COUNT(id) FROM history")
    suspend fun getCountHistory(): Int

//    @Query("SELECT MIN(id) FROM history WHERE condition")
//    suspend fun updateNewHistory(): List<HistoryDB>

}