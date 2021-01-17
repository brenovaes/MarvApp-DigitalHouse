package com.digitalhouse.br.marvelapp.service

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
    suspend fun getAllUserTask() : List<User>
//    suspend fun deleteUserTask(userD: User)
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
}

interface RepositorySuggestions{
    suspend fun getAllSuggestionsTask(): List<Suggestions>
    suspend fun addSuggestionsTask(suggestions: Suggestions)
    suspend fun getCountSuggestionsTask(): Int
//    suspend fun updateNewHistoryTask()
}

class RepositoryImpl (val userDao: UserDao): RepositoryDB {

    override suspend fun addUserTask(user: User) = userDao.addUser(user)
    override suspend fun getAllUserTask() = userDao.getUser()
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

}

class RepositoryImplSuggestions(val suggestionsDao: SuggestionsDao): RepositorySuggestions{
    override suspend fun getAllSuggestionsTask(): List<Suggestions> = suggestionsDao.getAllSuggestions()
    override suspend fun addSuggestionsTask(suggestions: Suggestions) = suggestionsDao.addSuggestions(suggestions)
    override suspend fun getCountSuggestionsTask(): Int = suggestionsDao.getCountSuggestion()
//    override suspend fun updateNewHistoryTask() = = suggestionsDao.updateNewHistory()

}






