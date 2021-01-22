package com.digitalhouse.br.marvelapp.interfac

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.User

@Dao
interface UserDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

    @Query ("SELECT id FROM user WHERE email = :email")
    suspend fun checkEmail (email: String): Int?

    @Query ("SELECT password FROM user WHERE id = :id")
    suspend fun checkPassword(id: Int): String

//    @Query("DELETE FROM user WHERE username = :userD")
//    suspend fun deleteUser(userD: User)
}