package com.digitalhouse.br.marvelapp.service

import com.digitalhouse.br.marvelapp.interfac.UserDao
import com.digitalhouse.br.marvelapp.models.User

interface RepositoryDB {
    suspend fun addUserTask(user: User)
    suspend fun getAllUserTask() : List<User>
}

class RepositoryImpl (val userDao: UserDao): RepositoryDB {

    override suspend fun addUserTask(user: User) = userDao.addUser(user)

    override suspend fun getAllUserTask() = userDao.getUser()
}