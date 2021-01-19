package com.digitalhouse.br.marvelapp.interfac

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.HistoryDB

@Dao
interface HistoryDao {

    @Query ("SELECT * FROM history ORDER BY idC DESC")
    suspend fun getAllHistory(): List<HistoryDB>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(historyDB: HistoryDB)

    @Query ("SELECT COUNT(id) FROM history")
    suspend fun getCountHistory(): Int

    @Query ("DELETE FROM history WHERE idC NOT IN (SELECT idC FROM history ORDER BY idC DESC LIMIT 10)")
    suspend fun updateNewHistory()

    @Query ("DELETE FROM history WHERE id = :idValue")
    suspend fun deleteHistory (idValue: Int)
}