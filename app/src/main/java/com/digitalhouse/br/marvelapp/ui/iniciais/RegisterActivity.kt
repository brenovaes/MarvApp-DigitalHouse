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
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_redefinir_senha.*

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
            if (etUsuario.text.toString().isNotBlank() &&
                    etEmail.text.toString().isNotBlank() &&
                    etPasswordC.text.toString().isNotBlank() &&
                    etRPasswordC.text.toString().isNotBlank()){

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
                    showToast("Different passwords, please make them the same.")
                }

            }else{
                showToast("Fill in all required information.")
            }
        }
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }



    fun callHome() {
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun sendDataFirebase(email:String, password:String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    showToast("User successfully registered.")
                    callHome()
                }

                else{
                    showToast("Failed to register the user, please try again.")
                }
            }
    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }


}