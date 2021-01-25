package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.service.RepositoryDB
import com.digitalhouse.br.marvelapp.service.RepositoryImpl
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var repository: RepositoryDB
    private lateinit var db: AppDataBase

    private lateinit var auth: FirebaseAuth
    var RC_SIGN_IN = 120
    private lateinit var googleSignInClient: GoogleSignInClient

    val loginViewModel by viewModels<LoginViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            callHome()
        }

        initDB()
        repository = RepositoryImpl(db.userDao())

        btnLogin.setOnClickListener {
            getDataFields()
        }

        btnGoogle.setOnClickListener{
            signIn()
        }

        tvCadastre_se.setOnClickListener {
            callCadastro()
        }

        tvEsqueceuSenha.setOnClickListener() {
            callEsqueceuSenha()
        }

        //somente para ver se a tela de redefinição de senha está ok
        ivLogo.setOnClickListener() {
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }
    }

    fun callHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    //falta imlementar passagem de parâmetros do user
    fun callCadastro() {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun callEsqueceuSenha() {
        var intent = Intent(this, EsqueceuSenhaActivity::class.java)
        startActivity(intent)
    }

    fun initDB() {
        db = AppDataBase.invoke(this)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun getDataFields(){
        var email = etEmailL.text.toString()
        var password = etPasswordL.text.toString()
        var emailEmpty = email.isNotBlank()
        var passwordEmpty = password.isNotBlank()
        //mudar para notblank
        if (emailEmpty && passwordEmpty){
            //envia dados para o firebase
            sendDataFirebase(email,password)

        }else{
            showToast("Fill in all required information")
        }

    }

    fun sendDataFirebase(email:String, password:String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        val firebaseUser: FirebaseUser = task.result?.user!!
//                        val idUser = firebaseUser.uid
//                        val emailUser = firebaseUser.email.toString()
                        callHome()
                    }
                    else{
                        showToast("Incorrect email or password")
                    }
                }
    }


    fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("ActivityResult", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("ActivityResult", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("firebaseAuthWithGoogle", "signInWithCredential:success")
                    val user = auth.currentUser
                    callHome()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebaseAuthWithGoogle", "signInWithCredential:failure", task.exception)
                    // ...
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }



}