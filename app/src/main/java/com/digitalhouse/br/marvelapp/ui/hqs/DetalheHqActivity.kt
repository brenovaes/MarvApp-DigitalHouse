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
import com.digitalhouse.br.marvelapp.ui.criadores.CreatorsAdapter
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.events.EventsAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.CharactersAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.digitalhouse.br.marvelapp.ui.series.SeriesAdapter
import com.digitalhouse.br.marvelapp.ui.stories.StoriesAdapter
import com.squareup.picasso.Picasso

class DetalheHqActivity :
    AppCompatActivity(),
        StoriesAdapter.OnStoriesClickListener,
//    CreatorsAdapter.OnCreatorsClickListener,
        CreatorsComicsAdapter.OnCreatorsComicsClickListener,
        CharactersAdapter.OnCharactersClickListener,
        SeriesAdapter.OnSeriesClickListener,
        EventsAdapter.OnEventsClickListener {


    var comics = arrayListOf<ResultsCo>()
    lateinit var adapterStories: StoriesAdapter
    lateinit var adapterSeries: SeriesAdapter
    lateinit var adapterEventos: EventsAdapter
    lateinit var adapterCharacters: CharactersAdapter
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
            var characters = comics[0].characters.items
            var series = comics[0].series.items
            var events = comics[0].events.items
            var stories = comics[0].stories.items

            tvNomeHqDetalhe.text = comics[0].title
            tvDescricaoHqDetalhe.text = comics[0].description

            var img = comics[0].thumbnail.path + "." + comics[0].thumbnail.extension
            Picasso.get().load(img).resize(360,280).into(ivHqDetalhe)


            adapterStories= StoriesAdapter(stories, this)
            rvHistoriasHq.adapter = adapterStories


//            adapterSeries = SeriesAdapter(series,this)
//            rvSeriesHq.adapter = adapterSeries


            adapterEventos = EventsAdapter(events, this)
            rvEventosHq.adapter = adapterEventos

            adapterCharacters= CharactersAdapter(characters, this)
            rvPersonagensHq.adapter = adapterCharacters




            tvQtdHistoriasHq.text = stories.size.toString()
            tvQtdSeriesHq.text =  1.toString()
            tvQtdEventosHq.text = events.size.toString()
            tvQtdPersonagensHq.text = characters.size.toString()

            viewModelComics.retornoComicsCreator.observe(this){
                tvQtdCriadoresHq.text = it.data.results.size.toString()
                adapterCreators =  CreatorsComicsAdapter(it.data.results,this, creators)
                rvCriadoresHq.adapter = adapterCreators
            }

        }



        viewModelComics.getCreatorsComic(idComic)

        viewModelComics.getComic(idComic)


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

    override fun storiesClick(position: Int) {
        TODO("Not yet implemented")
    }

//    override fun creatorsClick(position: Int) {
////        val creator = listaCreators.get(position)
////        var imagem = creator.imagemCriador
////        var nome = creator.nomeCriador
////        var funcao = creator.funcaoCriador
////        adapterCreators.notifyItemChanged(position)
////        ActivityDetalheCriador(creator)
//    }

    private fun ActivityDetalheCriador(creator: Creators) {
        var intent = Intent(this, DetalheCriadorActivity::class.java)
        intent.putExtra("ComicsCh", creator)
        startActivity(intent)
    }

    override fun charactersClick(position: Int) {
//        val char = listaCharacters.get(position)
//        var imagem = char.imagemCharacter
//        var nome = char.nomeCharacter
//        adapterCharacters.notifyItemChanged(position)
//        ActivityDetalheCharacter(char)
    }

    private fun ActivityDetalheCharacter(char: Characters) {
        var intent = Intent(this, DetalhePersonagemActivity::class.java)
        intent.putExtra("ComicsCh", char)
        startActivity(intent)
    }

    override fun seriesClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun creatorsComicsClick(position: Int) {
        TODO("Not yet implemented")
    }
}

