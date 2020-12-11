package com.digitalhouse.br.marvelapp.ui.hqs

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
import kotlinx.android.synthetic.main.activity_detalhe_hq.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.models.*
import com.digitalhouse.br.marvelapp.service.serviceCo
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.squareup.picasso.Picasso


class DetalheHqActivity :
    AppCompatActivity(),
        StoriesComicsAdapter.OnStoriesComicsClickListener,
        CreatorsComicsAdapter.OnCreatorsComicsClickListener,
       CharactersComicsAdapter.OnCharactersComicsClickListener,
        SeriesComicsAdapter.OnSeriesComicsClickListener,
       EventsComicsAdapter.OnEventsComicsClickListener {


    var comics = arrayListOf<ResultsCo>()
    lateinit var adapterStories: StoriesComicsAdapter
    lateinit var adapterSeries: SeriesComicsAdapter
    lateinit var adapterEventos: EventsComicsAdapter
    lateinit var adapterCharacters: CharactersComicsAdapter
    lateinit var adapterCreators: CreatorsComicsAdapter


    val viewModelComics by viewModels<ComicsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicsViewModel(serviceCo) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_hq)
        var idComic = intent.getIntExtra("idCo", 0)

        setSupportActionBar(tbDetalheHq)
        tbDetalheHq.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModelComics.retornoComic.observe(this) {
            comics.addAll(it.data.results)
            var creators = comics[0].creators.items

            tvNomeHqDetalhe.text = comics[0].title
            tvDescricaoHqDetalhe.text = comics[0].description

            var img = comics[0].thumbnail.path + "." + comics[0].thumbnail.extension
            Picasso.get().load(img).resize(360,280).into(ivHqDetalhe)


            viewModelComics.retornoComicsCreator.observe(this){
                tvQtdCriadoresHq.text = it.data.results.size.toString()
                adapterCreators =  CreatorsComicsAdapter(it.data.results,this, creators)
                rvCriadoresHq.adapter = adapterCreators

            }

            viewModelComics.retornoComicsCharacters.observe(this){
                tvQtdPersonagensHq.text = it.data.results.size.toString()
                adapterCharacters = CharactersComicsAdapter(it.data.results, this)
                rvPersonagensHq.adapter = adapterCharacters

            }

            //sem valor pois a função n está sendo chamada
            viewModelComics.retornoComicsSeries.observe(this) {
                tvQtdSeriesHq.text = it.data.results.size.toString()
                adapterSeries = SeriesComicsAdapter(it.data.results, this)
                rvSeriesHq.adapter = adapterSeries

            }

            viewModelComics.retornoComicsEvents.observe(this) {
                tvQtdEventosHq.text = it.data.results.size.toString()
                adapterEventos = EventsComicsAdapter(it.data.results, this)
                rvEventosHq.adapter = adapterEventos

            }

            viewModelComics.retornoComicsStories.observe(this) {
                tvQtdHistoriasHq.text = it.data.results.size.toString()
                adapterStories = StoriesComicsAdapter(it.data.results, this)
                rvHistoriasHq.adapter = adapterStories

            }

        }


        viewModelComics.getComic(idComic)
        viewModelComics.getCreatorsComic(idComic)
        viewModelComics.getCharactersComic(idComic)
        //series ta em espera
//        viewModelComics.getSeriesComic(id)
        viewModelComics.getEventsComic(idComic)
        viewModelComics.getStoriesComic(idComic)


        rvHistoriasHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvHistoriasHq.setHasFixedSize(true)


        rvSeriesHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvSeriesHq.setHasFixedSize(true)


        rvEventosHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvEventosHq.setHasFixedSize(true)


        rvPersonagensHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvPersonagensHq.setHasFixedSize(true)


        rvCriadoresHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvCriadoresHq.setHasFixedSize(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_share -> {
                Toast.makeText(this, "Compartilhar Quadrinhos", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }



    private fun ActivityDetalheCriador(creator: Creators) {
        var intent = Intent(this, DetalheCriadorActivity::class.java)
        intent.putExtra("ComicsCh", creator)
        startActivity(intent)
    }


    private fun ActivityDetalheCharacter(char: Characters) {
        var intent = Intent(this, DetalhePersonagemActivity::class.java)
        intent.putExtra("ComicsCh", char)
        startActivity(intent)
    }


    override fun creatorsComicsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun charactersComicsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun seriesComicsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsComicsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun storiesComicsClick(position: Int) {
        TODO("Not yet implemented")
    }
}

