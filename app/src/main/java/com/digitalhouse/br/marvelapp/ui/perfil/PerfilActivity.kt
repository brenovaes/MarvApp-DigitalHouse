package com.digitalhouse.br.marvelapp.ui.perfil

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.digitalhouse.br.marvelapp.MyPreferences
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.favoritos.FavoritoActivity
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.SplashActivity
import com.digitalhouse.br.marvelapp.ui.quiz.PerguntaActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.fragment_redefinir_senha.view.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class PerfilActivity : AppCompatActivity() {
    var msgPasswordR = "If you proceed with this action and have registered through" +
            " any social network, know that your account will be disconnected from" +
            " the social network and the login will have to be done with the email " +
            "and the new password defined. Do you want to proceed?"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        getInfoUser()

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        btnRedefProfile.setOnClickListener {
            alertPassword(msgPasswordR)
//            Toast.makeText(this,"Salved", Toast.LENGTH_SHORT).show()
        }

        //Setar Botão para tela atual
        btnNavigationPerfil.selectedItemId = R.id.menu_perfil

        btnNavigationPerfil.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_busca -> {
                    startActivity(Intent(this, BuscaActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_quiz -> {
                    startActivity(Intent(this, QuizActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                //mudar activity
                R.id.menu_favoritos -> {
                    startActivity(Intent(this, FavoritoActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                //mudar activity
                R.id.menu_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun getInfoUser() {
        var user = FirebaseAuth.getInstance().currentUser!!
        etEmailP.setText(user.email)
        etUsernameP.setText(user.displayName)
        if (user.photoUrl != null) {
            Picasso.get().load(user.photoUrl).fit().into(ivPhotoP)
        }
    }


    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itTema -> {

                    chooseThemeDialog(MyPreferences(this).darkMode)
                    Toast.makeText(this@PerfilActivity, "Changed theme.", Toast.LENGTH_SHORT).show()

                }

                R.id.help -> {
                    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                    var mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
                    mGoogleSignInClient.signOut().addOnCompleteListener {
                        FirebaseAuth.getInstance().signOut()
                    }
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
            true
        })
        popupMenu.show()
    }

    fun chooseThemeDialog(preferenceUser: Int?) {

        when (preferenceUser) {
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                MyPreferences(this).darkMode = 0
                delegate.applyDayNight()


            }
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                MyPreferences(this).darkMode = 1
                delegate.applyDayNight()

            }

        }

    }

    fun alertPassword(msg: String) {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setMessage(msg)
            setPositiveButton("Yes", positiveButtonClick)
            setNegativeButton("No", negativeButtonClick)
            show()
        }
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        var email = FirebaseAuth.getInstance().currentUser!!.email

        if (email != null) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Please check your email inbox.")
                    } else {
                        showToast("Something went wrong, please try again later.")
                    }
                }
        }
    }

    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        showToast("Password reset canceled.")
    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}