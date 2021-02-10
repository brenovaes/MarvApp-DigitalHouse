package com.digitalhouse.br.marvelapp.ui.quiz

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.MyPreferences
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.crH
import com.digitalhouse.br.marvelapp.crTri1
import com.digitalhouse.br.marvelapp.service.serviceCh
import com.digitalhouse.br.marvelapp.service.serviceS
import com.digitalhouse.br.marvelapp.ui.home.HomeViewModel
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_alternativa_selecionada.*
import kotlinx.android.synthetic.main.activity_pergunta.*
import kotlinx.android.synthetic.main.toolbar_principal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlternativaSelecionadaActivity : AppCompatActivity() {
    var scope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alternativa_selecionada)

        val imgRespota: ImageView = ivRetornoResposta
        val retorno = intent.getBooleanExtra("opção", false)
        var pergunta = intent.getIntExtra("pergunta",1)
        var pontos = intent.getIntExtra("pontos",0)
        var intent = Intent(this@AlternativaSelecionadaActivity, PerguntaActivity::class.java)




        if(retorno){
            tvRetornoRespostaTitulo.text = "CORRETO"
            tvRetornoRespostaTexto.text = "You are getting closer and closer to earning a Stamp"
            ivRetornoResposta.setImageResource(R.drawable.ic_correto)
            intent.putExtra("pontos", pontos+20)
        }else{
            tvRetornoRespostaTitulo.text = "INCORRETO"
            tvRetornoRespostaTexto.text = "Maybe you need to read more about the Marvel universe"
            ivRetornoResposta.setImageResource(R.drawable.ic_errado)
            intent.putExtra("pontos", pontos)
        }


        scope.launch {
            delay(2000)
//            var intent = Intent(this@AlternativaSelecionadaActivity, PerguntaActivity::class.java)
            intent.putExtra("pergunta", pergunta+1)
            startActivity(intent)
            finish()
        }

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

//        btnDesistir.setOnClickListener{
//            alertDialogDesistir()
//        }
    }


    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(this,"Thank You", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DesafiosFragment::class.java))
    }

    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(this,"Good Lucky", Toast.LENGTH_SHORT).show()
    }

    private fun alertDialogDesistir() {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setMessage("When you give up you will lose your progress on this trail. Are you sure you want to give up?")
            setPositiveButton("Yes", DialogInterface.OnClickListener(function = this@AlternativaSelecionadaActivity.positiveButtonClick))
            setNegativeButton("No", negativeButtonClick)
            show()
        }
    }

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->{

                    chooseThemeDialog(MyPreferences(this).darkMode )
                    Toast.makeText(this@AlternativaSelecionadaActivity,"Changed theme.", Toast.LENGTH_SHORT).show()

                }

                R.id.help ->{
                    var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                    var mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
                    mGoogleSignInClient.signOut().addOnCompleteListener{
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

}