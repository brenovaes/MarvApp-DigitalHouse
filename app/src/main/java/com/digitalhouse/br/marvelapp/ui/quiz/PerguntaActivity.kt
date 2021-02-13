package com.digitalhouse.br.marvelapp.ui.quiz

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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.*
import com.digitalhouse.br.marvelapp.models.Pontuacao
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_pergunta.*
import kotlinx.android.synthetic.main.enunciado_quiz.*
import kotlinx.android.synthetic.main.enunciado_quiz.view.*
import kotlinx.android.synthetic.main.toolbar_principal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class PerguntaActivity : AppCompatActivity() {
    //    private var listaAlternativa = getListaAlternativa()
//    private var adapter = AlternativaAdapter(listaAlternativa, this)
    private var buttonRG: RadioGroup? = null
    private var msg: String? = null
    private var opcaoEscolhida: Boolean = false
    var pergunta = 1
    var pontos = 0
    var trilha = 0

    var email = FirebaseAuth.getInstance().currentUser!!.email!!

    val viewModelQuiz by viewModels<QuizViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return QuizViewModel(
                        crTri1,
                        crTri2,
                        crTri3,
                        crTri4,
                        crPontosTr,
                        crHank
                ) as T
            }
        }
    }


    override fun onResume() {
        super.onResume()

        viewModelQuiz.checkHancking(email!!)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pergunta)

        trilha = intent.getIntExtra("trilha", 1)

        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        var name = FirebaseAuth.getInstance().currentUser!!.displayName!!

//        var r = viewModelQuiz.checkHancking(email)
//        Log.i("RETURN", r.toString())

        Log.i("LOG FORA", viewModelQuiz.checkH.value.toString())

        pergunta = intent.getIntExtra("pergunta", 1)
        pontos = intent.getIntExtra("pontos", 0)
//        var trilha = intent.getIntExtra("trilha", 1)

        Log.i("TRILHA:", trilha.toString())
        viewModelQuiz.somaPontos()
        when(trilha){

            1 -> {

                //n ta ok
                if (pergunta > 5) {
                    Log.i("TRILHA 1:", trilha.toString())
                    viewModelQuiz.checkH.observe(this){
                        Log.i("Check trilha:", trilha.toString())
                        if (viewModelQuiz.checkH.value == true) {
                            Log.i("UPDATE:", trilha.toString())

                            viewModelQuiz.update(email, pontos, trilha)

                        } else if (viewModelQuiz.checkH.value == null && viewModelQuiz.checkH.value != true) {
                            Log.i("ADD:", trilha.toString())

                            viewModelQuiz.addPontos(email, name, trilha, pontos)
                            viewModelQuiz.checkH.value = true
                        }
                    }

                    startActivity(Intent(this, QuizActivity::class.java))
                }

                viewModelQuiz.getPergTrilha01(pergunta.toString())
                viewModelQuiz.retornoTrilha01.observe(this){
                    tvEnunciadoTexto.text = it.pergunta
                    cvNumeroPergunta.tvNumeroDaPergunta.text = "$pergunta"
                    rbAlternativa1.text = it.errada1
                    rbAlternativa2.text = it.errada2
                    rbAlternativa3.text = it.errada3
                    rbAlternativa4.text = it.correta

                }
            }

            2 -> {
                if (pergunta > 3) {

                    //ta ok
                    viewModelQuiz.checkH.observe(this){
                        if (viewModelQuiz.checkH.value == true) {
                            viewModelQuiz.update(email, pontos, trilha)

                        } else if (viewModelQuiz.checkH.value == null && viewModelQuiz.checkH.value != true) {
                            viewModelQuiz.addPontos(email, name, trilha, pontos)
                            viewModelQuiz.checkH.value = true
                        }
                    }

                    startActivity(Intent(this, QuizActivity::class.java))
                }

                viewModelQuiz.getPergTrilha02(pergunta.toString())
                viewModelQuiz.retornoTrilha02.observe(this){
                    tvEnunciadoTexto.text = it.pergunta
                    cvNumeroPergunta.tvNumeroDaPergunta.text = "$pergunta/5"
                    rbAlternativa1.text = it.errada1
                    rbAlternativa2.text = it.errada2
                    rbAlternativa3.text = it.errada3
                    rbAlternativa4.text = it.correta

                }
            }

            3->{
                if (pergunta > 8) {

                    //ta ok
                    viewModelQuiz.checkH.observe(this){
                        if (viewModelQuiz.checkH.value == true) {
                            viewModelQuiz.update(email, pontos, trilha)
                        } else if (viewModelQuiz.checkH.value == null && viewModelQuiz.checkH.value != true) {
                            viewModelQuiz.addPontos(email, name, trilha, pontos)
                            viewModelQuiz.checkH.value = true
                        }
                    }

                    startActivity(Intent(this, QuizActivity::class.java))
                }


                viewModelQuiz.getPergTrilha03(pergunta.toString())
                viewModelQuiz.retornoTrilha03.observe(this){
                    tvEnunciadoTexto.text = it.pergunta
                    cvNumeroPergunta.tvNumeroDaPergunta.text = "$pergunta/5"
                    rbAlternativa1.text = it.errada1
                    rbAlternativa2.text = it.errada2
                    rbAlternativa3.text = it.errada3
                    rbAlternativa4.text = it.correta

                }
            }


            4->{
                if (pergunta > 7) {

                    //ta ok
                    viewModelQuiz.checkH.observe(this){
                        if (it == true) {
                            viewModelQuiz.update(email, pontos, trilha)
                        } else if (viewModelQuiz.checkH.value == null && viewModelQuiz.checkH.value != true) {
                            viewModelQuiz.addPontos(email, name, trilha, pontos)
                            viewModelQuiz.checkH.value = true
                        }
                    }

                    startActivity(Intent(this, QuizActivity::class.java))
                }


                viewModelQuiz.getPergTrilha04(pergunta.toString())
                viewModelQuiz.retornoTrilha04.observe(this){
                    tvEnunciadoTexto.text = it.pergunta
                    cvNumeroPergunta.tvNumeroDaPergunta.text = "$pergunta/5"
                    rbAlternativa1.text = it.errada1
                    rbAlternativa2.text = it.errada2
                    rbAlternativa3.text = it.errada3
                    rbAlternativa4.text = it.correta

                }
            }


        }



//        barraProgressoPergunta.cvProgressoPergunta1.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta2.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta3.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))
//        barraProgressoPergunta.cvProgressoPergunta4.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.cinzaClaro))

//        viewModelQuiz.getPergTrilha01(pergunta.toString())
//        viewModelQuiz.retornoTrilha01.observe(this){
//            tvEnunciadoTexto.text = it.pergunta
//            cvNumeroPergunta.tvNumeroDaPergunta.text = "$pergunta/5"
//            rbAlternativa1.text = it.errada1
//            rbAlternativa2.text = it.errada2
//            rbAlternativa3.text = it.errada3
//            rbAlternativa4.text = it.correta
//
//        }


//        rvAlternativa.adapter = adapter
//        rvAlternativa.layoutManager = LinearLayoutManager(this)
//        rvAlternativa.setHasFixedSize(true)

//        btnAnterior.setOnClickListener(callActivity(QuizActivity::class.java))

//        btnSalvar.setOnClickListener{
////            if(opcaoEscolhida){
////                if(msg != null){
////                    val intent = Intent(this, AlternativaSelecionadaActivity::class.java)
////                    intent.putExtra("opção", false)
////                    startActivity(intent)
////                }
////            }else{
////                Toast.makeText(this,"Choose an option", Toast.LENGTH_SHORT).show()
////            }
//        }

        btnDesistir.setOnClickListener{
            alertDialogDesistir()
        }

    }

    fun onRadioButtOnClicked(v: View?) {
        val intent = Intent(this, AlternativaSelecionadaActivity::class.java)
        Log.i("PERGUNTA ACTIVITY", "OPÕES ABERTAS")
        Log.i("PERGUNTA ACTIVITY PONTOS", pontos.toString())

        if (v is RadioButton) {
            val checked = v.isChecked
            opcaoEscolhida = true
            when (v.id) {
                R.id.rbAlternativa1 -> {
                    //llPergunta.setBackgroundColor(getColor(R.color.red))
                    intent.putExtra("opção", false)
                    intent.putExtra("pergunta", pergunta)
                    intent.putExtra("pontos", pontos)
                    intent.putExtra("trilha", trilha)
                    startActivity(intent)
                }

                R.id.rbAlternativa2 -> {
                    //llPergunta.setBackgroundColor(getColor(R.color.red))
                    intent.putExtra("opção", false)
                    intent.putExtra("pergunta", pergunta)
                    intent.putExtra("pontos", pontos)
                    intent.putExtra("trilha", trilha)
                    startActivity(intent)
                }
                R.id.rbAlternativa3 -> {
                    //llPergunta.setBackgroundColor(getColor(R.color.red))
                    intent.putExtra("opção", false)
                    intent.putExtra("pergunta", pergunta)
                    intent.putExtra("pontos", pontos)
                    intent.putExtra("trilha", trilha)
                    startActivity(intent)
                }
                R.id.rbAlternativa4 -> {
                    //llPergunta.setBackgroundColor(getColor(R.color.green))
                    intent.putExtra("opção", true)
                    intent.putExtra("pergunta", pergunta)
                    intent.putExtra("pontos", pontos)
                    intent.putExtra("trilha", trilha)
                    startActivity(intent)
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