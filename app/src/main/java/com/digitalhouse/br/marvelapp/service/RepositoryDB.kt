package com.digitalhouse.br.marvelapp.service

import com.digitalhouse.br.marvelapp.interfac.HeroDayDao
import com.digitalhouse.br.marvelapp.interfac.UserDao
import com.digitalhouse.br.marvelapp.models.Characters
import com.digitalhouse.br.marvelapp.models.User
import java.time.LocalDate

interface RepositoryDB {
    suspend fun addUserTask(user: User)
    suspend fun getAllUserTask() : List<User>
    suspend fun deleteByUserIdTask()
    suspend fun updateTask()

}

interface RepositoryHero{
    suspend fun addHeroDay(character: Characters)
    suspend fun getHeroDay():String
    suspend fun getAll():Characters?
//    suspend fun updateHero(character: Characters)
    suspend fun deleteHeroDay()
    suspend fun getIdHero():Int
}

class RepositoryImpl (val userDao: UserDao): RepositoryDB {

    override suspend fun addUserTask(user: User) = userDao.addUser(user)

    override suspend fun getAllUserTask() = userDao.getUser()

    override suspend fun deleteByUserIdTask() = userDao.deleteByUserId()

    override suspend fun updateTask() = userDao.update()

}

class RepositoryImplHero(val heroDayDao: HeroDayDao):RepositoryHero{
    override suspend fun addHeroDay(character: Characters) = heroDayDao.addHeroDay(character)
    override suspend fun getHeroDay(): String = heroDayDao.getHeroDay()
    override suspend fun getAll():Characters? = heroDayDao.getAll()
//    override suspend fun updateHero(character: Characters) = heroDayDao.updateHero(character)
    override suspend fun deleteHeroDay()= heroDayDao.deleteHeroDay()
    override suspend fun getIdHero():Int = heroDayDao.getIdHero()
}






