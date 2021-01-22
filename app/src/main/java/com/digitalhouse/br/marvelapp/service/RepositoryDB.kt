package com.digitalhouse.br.marvelapp.service

import androidx.lifecycle.MutableLiveData
import com.digitalhouse.br.marvelapp.interfac.HeroDayDao
import com.digitalhouse.br.marvelapp.interfac.HistoryDao
import com.digitalhouse.br.marvelapp.interfac.SuggestionsDao
import com.digitalhouse.br.marvelapp.interfac.UserDao
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.models.Suggestions
import com.digitalhouse.br.marvelapp.models.User

interface RepositoryDB {
    suspend fun addUserTask(user: User)
//    suspend fun getAllUserTask() : List<User>
    suspend fun getUserTask(): List<User>
//    suspend fun deleteUserTask(userD: User)
    suspend fun checkEmailTask (email: String): Int?
    suspend fun checkPasswordTask (id: Int): String
}

interface RepositoryHero{
    suspend fun addHeroDay(character: Characters)
    suspend fun getHeroDay():String
    suspend fun getAll():Characters?
    suspend fun deleteHeroDay()
    suspend fun getIdHero():Int
    //    suspend fun updateHero(character: Characters)
}

interface RepositoryHistory {
    suspend fun getAllHistoryTask(): List<HistoryDB>
    suspend fun addHistoryTask(historyDB: HistoryDB)
    suspend fun getCountHistoryTask(): Int
    suspend fun updateNewHistoryTask()
    suspend fun deleteHistoryTask (idValue: Int)
}

interface RepositorySuggestions{
    suspend fun getAllSuggestionsTask(): List<Suggestions>
    suspend fun addSuggestionsTask(suggestions: Suggestions)
    suspend fun updateNewSuggestionTask()
//    suspend fun updateNewHistoryTask()
}

class RepositoryImpl (val userDao: UserDao): RepositoryDB {

    override suspend fun addUserTask(user: User) = userDao.addUser(user)
    override suspend fun getUserTask() = userDao.getUser()
    override suspend fun checkEmailTask(email: String): Int? = userDao.checkEmail(email)
    override suspend fun checkPasswordTask(id: Int): String = userDao.checkPassword(id)

//    override suspend fun getAllUserTask() = userDao.getUser()
//    override suspend fun deleteUserTask(userD: User) = userDao.deleteUser(userD)
}

class RepositoryImplHero(val heroDayDao: HeroDayDao):RepositoryHero{
    override suspend fun addHeroDay(character: Characters) = heroDayDao.addHeroDay(character)
    override suspend fun getHeroDay(): String = heroDayDao.getHeroDay()
    override suspend fun getAll():Characters? = heroDayDao.getAll()
    override suspend fun deleteHeroDay()= heroDayDao.deleteHeroDay()
    override suspend fun getIdHero():Int = heroDayDao.getIdHero()
    //    override suspend fun updateHero(character: Characters) = heroDayDao.updateHero(character)
}

class RepositoryImplHistory (val historyDao: HistoryDao): RepositoryHistory{
    override suspend fun getAllHistoryTask(): List<HistoryDB> = historyDao.getAllHistory()
    override suspend fun addHistoryTask(historyDB: HistoryDB) = historyDao.addHistory(historyDB)
    override suspend fun getCountHistoryTask(): Int = historyDao.getCountHistory()
    override suspend fun updateNewHistoryTask() = historyDao.updateNewHistory()
    override suspend fun deleteHistoryTask(idValue: Int) = historyDao.deleteHistory(idValue)

}

class RepositoryImplSuggestions(val suggestionsDao: SuggestionsDao): RepositorySuggestions{
    override suspend fun getAllSuggestionsTask(): List<Suggestions> = suggestionsDao.getAllSuggestions()
    override suspend fun addSuggestionsTask(suggestions: Suggestions) = suggestionsDao.addSuggestions(suggestions)
    override suspend fun updateNewSuggestionTask() = suggestionsDao.updateNewSuggestion()
//    override suspend fun updateNewHistoryTask() = = suggestionsDao.updateNewHistory()

}






