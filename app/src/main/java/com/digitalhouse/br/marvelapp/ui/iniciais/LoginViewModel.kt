package com.digitalhouse.br.marvelapp.ui.iniciais

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalhouse.br.marvelapp.models.User
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import kotlinx.coroutines.launch

class LoginViewModel(val repositoryDB: RepositoryDB) : ViewModel() {

    var result = MutableLiveData<Int?>()
    var password = MutableLiveData<String>()
    var resSend = MutableLiveData<Boolean>()

    fun checkUserEmail(email: String) {
        viewModelScope.launch {
            result.value = repositoryDB.checkEmailTask(email)
        }
    }

    fun checkUserPassword(id: Int) {

        viewModelScope.launch {
            password.value = repositoryDB.checkPasswordTask(id)
        }
    }
//
//    fun sendUser(user: User) {
//        crUsers.document().set(user).addOnSuccessListener {
//            resSend.value = true
//        }.addOnCanceledListener {
//            resSend.value = false
//        }
//
//    }


}