package com.digitalhouse.br.marvelapp.ui.personagens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.digitalhouse.br.marvelapp.service.serviceCh
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.hqs.EventsComicsAdapter
import com.digitalhouse.br.marvelapp.ui.hqs.SeriesComicsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*


class DetalhePersonagemActivity :
    AppCompatActivity(),
        CharactersComicsAdapter.OnCharactersComicsClickListener,
        SeriesComicsAdapter.OnSeriesComicsClickListener,
        EventsComicsAdapter.OnEventsComicsClickListener{

    var character = arrayListOf<ResultsCh>()
    lateinit var adapterComics: CharactersComicsAdapter
    lateinit var adapterSeries: SeriesComicsAdapter
    lateinit var adapterEventos: EventsComicsAdapter


    val viewModelCharacters by viewModels<CharactersViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CharactersViewModel(serviceCh) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_personagem)

        var idCharacter = intent.getIntExtra("idCh", 0)

        setSupportActionBar(tbDetalhePersonagem)
        tbDetalhePersonagem.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModelCharacters.retornoCharacter.observe(this){
            character.addAll(it.data.results)
//            var comics = character[0].comics.items
//            var series = character[0].series.items
//            var events = character[0].events.items

            tvNomePersonagemDetalhe.text = character[0].name
            tvDescricaoPersonagemDetalhe.text = character[0].description

            var img = character[0].thumbnail.path + "." + character[0].thumbnail.extension
            Picasso.get().load(img).resize(360, 280).into(ivPersonagemDetalhe)

            viewModelCharacters.retornoCharactersComic.observe(this){
                tvQtdComicsPersonagem.text = it.data.total.toString()
                adapterComics = CharactersComicsAdapter(it.data.results, this)
                rvComicsPersonagem.adapter = adapterComics
            }

            viewModelCharacters.retornoCharactersEvents.observe(this) {
                tvQtdEventosPersonagem.text = it.data.total.toString()
                adapterEventos = EventsComicsAdapter(it.data.results, this)
                rvEventosPersonagem.adapter = adapterEventos
            }

            viewModelCharacters.retornoCharactesSeries.observe(this){
                tvQtdSeriesPersonagem.text = it.data.total.toString()
                adapterSeries = SeriesComicsAdapter(it.data.results, this)
                rvSeriesPersonagem.adapter = adapterSeries
            }
        }

        viewModelCharacters.getCharacter(idCharacter)
        viewModelCharacters.getCharactersComics(idCharacter)
        viewModelCharacters.getCharactersEvents(idCharacter)
        viewModelCharacters.getCharacterSeries(idCharacter)


        rvComicsPersonagem.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvComicsPersonagem.setHasFixedSize(true)


        rvSeriesPersonagem.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvSeriesPersonagem.setHasFixedSize(true)


        rvEventosPersonagem.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvEventosPersonagem.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_share -> {
                Toast.makeText(this, "Compartilhar Personagem", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }

    override fun charactersComicsClick(position: Int) {
        viewModelCharacters.retornoCharactersComic.observe(this) {
            activityDetalheHq(it.data.results[position].id)
        }

    }

    override fun seriesComicsClick(position: Int) {
        var url: String
        viewModelCharacters.retornoCharactesSeries.observe(this) {
            url = it.data.results[position].urls[0].url
            Log.i("seriesComicsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun eventsComicsClick(position: Int) {
        var url: String
        viewModelCharacters.retornoCharactersEvents.observe(this){
            url = it.data.results[position].urls[0].url
            Log.i("eventsComicsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    fun activityDetalheHq(id: Int){
        var intent = Intent(this, DetalheHqActivity::class.java)
        intent.putExtra("idCo", id)
        startActivity(intent)
    }
}