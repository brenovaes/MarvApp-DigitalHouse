package com.digitalhouse.br.marvelapp.ui.personagens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.digitalhouse.br.marvelapp.models.Comics
import com.digitalhouse.br.marvelapp.service.serviceCh
import com.digitalhouse.br.marvelapp.ui.events.EventsAdapter
import com.digitalhouse.br.marvelapp.ui.hqs.ComicsAdapter
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.series.SeriesAdapter
import com.squareup.picasso.Picasso



class DetalhePersonagemActivity :
    AppCompatActivity(),
        //ComicsAdapter.OnComicsClickListener,
        CharactersComicsAdapter.OnCharactersComicsClickListener,
        SeriesAdapter.OnSeriesClickListener,
        EventsAdapter.OnEventsClickListener {

    var character = arrayListOf<ResultsCh>()
    lateinit var adapterComics: CharactersComicsAdapter
    lateinit var adapterSeries: SeriesAdapter
    lateinit var adapterEventos: EventsAdapter

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
            var comics = character[0].comics.items
            var series = character[0].series.items
            var events = character[0].events.items

            tvNomePersonagemDetalhe.text = character[0].name
            tvDescricaoPersonagemDetalhe.text = character[0].description

            var img = character[0].thumbnail.path + "." + character[0].thumbnail.extension
            Picasso.get().load(img).resize(360,280).into(ivPersonagemDetalhe)


//            adapterComics = ComicsAdapter(comics, this, null)
//            rvComicsPersonagem.adapter = adapterComics

            adapterSeries = SeriesAdapter(series,this)
            rvSeriesPersonagem.adapter = adapterSeries

            adapterEventos = EventsAdapter(events, this)
            rvEventosPersonagem.adapter = adapterEventos


//            tvQtdComicsPersonagem.text = comics.size.toString()
            tvQtdEventosPersonagem.text = events.size.toString()
            tvQtdSeriesPersonagem.text = series.size.toString()

            viewModelCharacters.retornoCharactersComic.observe(this){
                tvQtdComicsPersonagem.text = it.data.results.size.toString()
                adapterComics = CharactersComicsAdapter(it.data.results, this)
                rvComicsPersonagem.adapter = adapterComics
            }
        }

        viewModelCharacters.getCharacter(idCharacter)
        viewModelCharacters.getCharactersComics(idCharacter)


        rvComicsPersonagem.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvComicsPersonagem.setHasFixedSize(true)


        rvSeriesPersonagem.layoutManager = LinearLayoutManager(this,HORIZONTAL,false)
        rvSeriesPersonagem.setHasFixedSize(true)


        rvEventosPersonagem.layoutManager = LinearLayoutManager(this,HORIZONTAL,false)
        rvEventosPersonagem.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
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
//        val comic = listaComics.get(position)
//        var imagem = comic.imagemComic
//        var nome = comic.nomeComic
//        var data = comic.dataVendaComic
//        var criador = comic.criadorComic
//        adapterComics.notifyItemChanged(position)
//        ActivityDetalheHq(comic)
    }

    override fun seriesClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun ActivityDetalheHq (detalheHq: Comics){
        var intent = Intent(this, DetalheHqActivity::class.java)
        intent.putExtra("ComicsCh", detalheHq)
        startActivity(intent)
    }
}