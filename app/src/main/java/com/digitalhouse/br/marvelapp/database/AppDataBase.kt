package com.digitalhouse.br.marvelapp.database

import android.content.Context
import androidx.room.*
import com.digitalhouse.br.marvelapp.Converters
import com.digitalhouse.br.marvelapp.interfac.HeroDayDao
import com.digitalhouse.br.marvelapp.interfac.HistoryDao
import com.digitalhouse.br.marvelapp.interfac.SuggestionsDao
import com.digitalhouse.br.marvelapp.interfac.UserDao
import com.digitalhouse.br.marvelapp.models.*


@Database(entities = [User::class, Characters::class, HistoryDB::class, Suggestions::class, Trilha::class], version = 1)

abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun heroDayDao(): HeroDayDao
    abstract fun historyDao(): HistoryDao
    abstract fun suggestionsDao(): SuggestionsDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, "marvel_db.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}