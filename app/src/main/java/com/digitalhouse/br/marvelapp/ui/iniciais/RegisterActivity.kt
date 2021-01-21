package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.models.User
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryImpl
import kotlinx.android.synthetic.main.activity_cadastro.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

    private lateinit var repository: RepositoryDB
    private lateinit var db: AppDataBase

    val registerViewModel by viewModels<RegisterViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RegisterViewModel(repository) as T
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        initDB()
        repository = RepositoryImpl(db.userDao())

        setSupportActionBar(toolbarC)
        toolbarC.setNavigationOnClickListener(View.OnClickListener() {
            onBackPressed()
        })

        //falta verificar se os campos estão vazios antes de salvar
        btnCadastrar.setOnClickListener() {
            if (registerViewModel.checkPassword(etPasswordC.text.toString(), etRPasswordC.text.toString())) {
                registerViewModel.addNewUser(
                    User(
                        username = etUsuario.text.toString(),
                        password = etPasswordC.text.toString(),
                        email = etEmail.text.toString()
                    )
                )
                sendDataFirebase(etEmail.text.toString(),etPasswordC.text.toString() )


            } else {
                showToast("Senhas diferentes!")
            }
        }
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }





//    fun getInformationUser(): User {
//        val name = tvUsuario.text.toString()
//        val email = tvEmail.text.toString()
//        val senha1 = etPasswordC.text.toString()
//        val senha2 = etRPasswordC.text.toString()
//
//        if (checkPassword(senha1, senha2)){
//            showToast("Usuário cadastrado!")
//            callLogin()
//        }
//        else{
//            showToast("Senhas diferentes!")
//        }
//
//        return User(1, email,name, senha1)
//    }

//    fun checkPassword(s1: String, s2: String): Boolean {
//        return s1 == s2
//    }

    //imlementar passagem de putExtra user
    fun callLogin() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun sendDataFirebase(email:String, password:String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    showToast("usuário cadastrado com sucesso")
//                    val idUser = firebaseUser.uid
//                    val emailUser = firebaseUser.email.toString()
//                    callMain(idUser, emailUser)
                }
            }
    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }


}