package com.digitalhouse.br.marvelapp.ui.quiz

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.digitalhouse.br.marvelapp.MyPreferences
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_pergunta.*
import kotlinx.android.synthetic.main.barra_progresso_pergunta.view.*
import kotlinx.android.synthetic.main.barra_selos_quiz.view.*
import kotlinx.android.synthetic.main.barra_selos_quiz.view.cvBarraSeloConquistado2
import kotlinx.android.synthetic.main.barra_selos_quiz.view.cvBarraSeloConquistado3
import kotlinx.android.synthetic.main.enunciado_quiz.*
import kotlinx.android.synthetic.main.enunciado_quiz.view.*
import kotlinx.android.synthetic.main.fragment_desafios.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class PerguntaActivity : AppCompatActivity(), AlternativaAdapter.OnItemClickListener {
    private var listaAlternativa = getListaAlternativa()
    private var adapter = AlternativaAdapter(listaAlternativa, this)
    private var buttonRG: RadioGroup? = null
    private var msg: String? = null
    private var opcaoEscolhida: Boolean = false


    private fun getListaAlternativa(): ArrayList<Alternativa> {
        return arrayListOf<Alternativa>(
                Alternativa("Timely ComicsCh", R.color.white),
                Alternativa("Atlas ComicsCh",R.color.white),
                Alternativa("Goodman ComicsCh", R.color.white),
                Alternativa("More Fun ComicsCh", R.color.white),
        )
    }

    override fun onItemClick(position: Int) {
        listaAlternativa[position].corBackground = R.color.destaqueVermelho
        Log.i("PerguntaActivity", "MUDA DE COR")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }


//        barraProgressoPergunta.cvProgressoPergunta1.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta2.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta3.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta4.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))

        tvEnunciadoTexto.text = "What was the name of Marvel's predecessor publisher?"
        cvNumeroPergunta.tvNumeroDaPergunta.text = "1/5"

//        rvAlternativa.adapter = adapter
//        rvAlternativa.layoutManager = LinearLayoutManager(this)
//        rvAlternativa.setHasFixedSize(true)

//        btnAnterior.setOnClickListener(callActivity(QuizActivity::class.java))

        btnSalvar.setOnClickListener{
            if(opcaoEscolhida){
                if(msg != null){
                    val intent = Intent(this, AlternativaSelecionadaActivity::class.java)
                    intent.putExtra("opção", false)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(this,"Choose an option", Toast.LENGTH_SHORT).show()
            }
        }

        btnDesistir.setOnClickListener{
            alertDialogDesistir()
        }

    }

    fun onRadioButtOnClicked(v: View?) {
        Log.i("PERGUNTA ACTIVITY", "OPÕES ABERTAS")
        if (v is RadioButton) {
            val checked = v.isChecked
            opcaoEscolhida = true
            when (v.id) {
                R.id.rbAlternativa1 -> if (checked) {
                    if (R.id.rbAlternativa1 == v.id) {
                        msg = "1"
                    }
                }
                R.id.rbAlternativa2 -> if (checked) {
                    if (R.id.rbAlternativa2 == v.id) {
                        msg = "2"
                    }
                }
                R.id.rbAlternativa3 -> if (checked) {
                    if (R.id.rbAlternativa3 == v.id) {
                        msg = "3"
                    }
                }
                R.id.rbAlternativa4 -> if (checked) {
                    if (R.id.rbAlternativa4 == v.id) {
                        msg = "4"
                    }
                }
            }
        }
    }



    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(this,"Thank You", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, QuizActivity::class.java))
    }

    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(this,"Good Lucky", Toast.LENGTH_SHORT).show()
    }

    private fun alertDialogDesistir() {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setMessage("When you give up you will lose your progress on this trail. Are you sure you want to give up?")
            setPositiveButton("Yes", DialogInterface.OnClickListener(function = this@PerguntaActivity.positiveButtonClick))
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
                    Toast.makeText(this@PerguntaActivity,"Changed theme.", Toast.LENGTH_SHORT).show()

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