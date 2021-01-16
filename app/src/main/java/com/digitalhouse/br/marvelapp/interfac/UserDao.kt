package com.digitalhouse.br.marvelapp.interfac

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

//    @Query("DELETE FROM user WHERE username = :userD")
//    suspend fun deleteUser(userD: User)
}