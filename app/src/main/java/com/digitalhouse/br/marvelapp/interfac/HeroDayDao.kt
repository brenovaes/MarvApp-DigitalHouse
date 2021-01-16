package com.digitalhouse.br.marvelapp.interfac

import androidx.room.*
import com.digitalhouse.br.marvelapp.models.Characters
import java.time.LocalDate

@Dao
interface HeroDayDao {

    @Insert
    suspend fun addHeroDay(character: Characters)

    @Query("SELECT dateT FROM characters WHERE posit = 0")
    suspend fun getHeroDay():String

    @Query("SELECT idCharacter FROM characters WHERE posit = 0")
    suspend fun getIdHero():Int

    @Query("SELECT * FROM characters")
    suspend fun getAll():Characters?

    @Query("DELETE FROM characters WHERE posit = 0")
    suspend fun deleteHeroDay()

//    @Update(entity = Characters::class )
//    suspend fun updateHero(character: Characters)

}

