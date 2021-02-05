package com.digitalhouse.br.marvelapp.ui.favoritos

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.digitalhouse.br.marvelapp.MyPreferences
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.ui.perfil.PerfilActivity
import com.digitalhouse.br.marvelapp.ui.quiz.QuizActivity
import com.digitalhouse.br.marvelapp.ui.busca.ViewPagerBuscaAdapter
import com.digitalhouse.br.marvelapp.ui.busca.BuscaActivity
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.home.HomeActivity
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.SplashActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_favorito.*
import kotlinx.android.synthetic.main.toolbar_principal.*

class FavoritoActivity: AppCompatActivity(), ContractDetalheCardsFragments {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorito)


        btnSetting.setOnClickListener {
            showPopup(btnSetting)
        }

        setUpTabs()

        btnNavigationFavoritoBusca.selectedItemId = R.id.menu_favoritos

        //Transição entre activity's
        btnNavigationFavoritoBusca.setOnNavigationItemSelectedListener {
            when(it.itemId){
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
                R.id.menu_favoritos -> {
                    startActivity(Intent(this, FavoritoActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun setUpTabs(){
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("CHARACTERS"))
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("COMICS"))
        tlFavoritoBusca.addTab(tlFavoritoBusca.newTab().setText("CREATORS"))

        val adapter = ViewPagerBuscaAdapter(supportFragmentManager, tlFavoritoBusca.tabCount)
        vpFavorito.adapter = adapter
        vpFavorito.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlFavoritoBusca))

        tlFavoritoBusca.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vpFavorito.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun callDetalhesPCards(idCharacter:Int) {
        startActivity(Intent(this, DetalhePersonagemActivity::class.java))
    }

    override fun callDetalhesHQCards(idComic:Int) {
        startActivity(Intent(this, DetalheHqActivity::class.java))
    }

    override fun callDetalhesCCards(idCreator:Int) {
        startActivity(Intent(this, DetalheCriadorActivity::class.java))
    }

    private fun showPopup(view: View) {
        val popupMenu: PopupMenu = PopupMenu(this, toolbarPrincipal, Gravity.RIGHT)
        popupMenu.menuInflater.inflate(R.menu.menu_setting,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.itTema ->{

                    chooseThemeDialog(MyPreferences(this).darkMode )
                    Toast.makeText(this@FavoritoActivity, "Changed theme.", Toast.LENGTH_SHORT).show()

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

//    private fun checkTheme(): Int {
//        when (MyPreferences(this).darkMode) {
//            0 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                delegate.applyDayNight()
//
//            }
//            1 -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                delegate.applyDayNight()
//
//
//            }
//
//        }
//        return MyPreferences(this).darkMode
//    }

}