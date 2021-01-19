package com.digitalhouse.br.marvelapp.interfac

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.Suggestions


@Dao
interface SuggestionsDao {

    @Query("SELECT * FROM suggestions ORDER BY idS DESC")
    suspend fun getAllSuggestions(): List<Suggestions>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSuggestions(suggestion: Suggestions)

    @Query("DELETE FROM suggestions WHERE idS NOT IN (SELECT idS FROM suggestions ORDER BY idS DESC LIMIT 10)")
    suspend fun updateNewSuggestion()

}