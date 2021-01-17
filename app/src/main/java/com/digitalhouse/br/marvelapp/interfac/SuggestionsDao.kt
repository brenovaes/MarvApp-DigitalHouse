package com.digitalhouse.br.marvelapp.interfac

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.Suggestions


@Dao
interface SuggestionsDao {

    @Query("SELECT * FROM suggestions")
    suspend fun getAllSuggestions(): List<Suggestions>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSuggestions(suggestion: Suggestions)

    @Query("SELECT COUNT(id) FROM suggestions")
    suspend fun getCountSuggestion(): Int

//    @Query("DELETE FROM history WHERE (SELECT MIN(id) FROM history)")
//    suspend fun updateNewHistory()
}