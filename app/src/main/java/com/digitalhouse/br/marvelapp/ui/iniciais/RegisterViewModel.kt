package com.digitalhouse.br.marvelapp.ui.iniciais

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.models.User
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import kotlinx.coroutines.launch

class RegisterViewModel (val repositoryDB: RepositoryDB) : ViewModel() {

    val listaUser = MutableLiveData<List<User>>()

    fun addNewUser(user: User) {
        viewModelScope.launch {
            repositoryDB.addUserTask(user)
        }
    }

//    fun deleteUser() {
//        viewModelScope.launch {
//            repositoryDB.deleteByUserIdTask()
//            update()
//        }
//    }

//    fun getAllUser(){
//        viewModelScope.launch {
//            listaUser.value = repositoryDB.getAllUserTask()
//        }
//    }

    fun checkPassword(s1: String, s2: String): Boolean {
        return s1 == s2
    }



}
