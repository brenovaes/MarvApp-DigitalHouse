package com.digitalhouse.br.marvelapp.ui.criadores

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ItemsCo
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.models.Comics
import com.digitalhouse.br.marvelapp.models.Events
import com.digitalhouse.br.marvelapp.models.Series
import com.digitalhouse.br.marvelapp.service.serviceCr
import com.digitalhouse.br.marvelapp.ui.events.EventsAdapter
import com.digitalhouse.br.marvelapp.ui.hqs.ComicsAdapter
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.series.SeriesAdapter
import com.squareup.picasso.Picasso


class DetalheCriadorActivity : AppCompatActivity(),
        ComicsAdapter.OnComicsClickListener,
        SeriesAdapter.OnSeriesClickListener,
        EventsAdapter.OnEventsClickListener {

    lateinit var listaComics: ArrayList<ItemsCo?>



    var creator = arrayListOf<ResultsCr>()
//    var nome = context.tvNomeCriadorDetalhe)

    lateinit var adapterComics: ComicsAdapter
    lateinit var adapterSeries: SeriesAdapter
    lateinit var adapterEventos: EventsAdapter

    val viewModelCreators by viewModels<CreatorsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CreatorsViewModel(serviceCr) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_criador)

        var idCreator = intent.getIntExtra("id", 0)


        setSupportActionBar(tbDetalheCriador)
        tbDetalheCriador.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModelCreators.retornoCreator.observe(this) {
            creator.addAll(it.data.results)
            var comics = creator[0].comics.items
            var series = creator[0].series.items
            var events = creator[0].events.items

            var name = creator[0].fullName
            var img = creator[0].thumbnail.path + "." + creator[0].thumbnail.extension

            Picasso.get().load(img).resize(360,280).into(ivCriadorDetalhe)
            tvNomeCriadorDetalhe.text = name


            adapterComics = ComicsAdapter(comics, this, name)
            rvComicsCriador.adapter = adapterComics

            adapterSeries = SeriesAdapter(series,this)
            rvSeriesCriador.adapter = adapterSeries

            adapterEventos = EventsAdapter(events, this)
            rvEventosCriador.adapter = adapterEventos


            tvQtdComicsCriador.text = comics.size.toString()
            tvQtdSeriesCriador.text = series.size.toString()
            tvQtdEventosCriador.text = events.size.toString()


        }
        viewModelCreators.getCreator(idCreator)

//        var img = creator[0].thumbnail.path + "." + creator[0].thumbnail.extension
//

//        this.tvNomeCriadorDetalhe.text = creator[0].fullName
//        nome = creator[0].fullName


//
//        viewModelCreators.retornoCreatorComics.observe(this) {
//            listaComics = arrayListOf(it.data.results.first().comics.items)
//            Log.i("ID", idCreator.toString())
//            adapterComics = ComicsAdapter(listaComics, this)
//            rvComicsCriador.adapter = adapterComics
//        }


//        viewModelCreators.getCreatorComics(idCreator)






        rvComicsCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvComicsCriador.setHasFixedSize(true)


        rvSeriesCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvSeriesCriador.setHasFixedSize(true)


        rvEventosCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvEventosCriador.setHasFixedSize(true)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_share -> {
                Toast.makeText(this, "Share Creator", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }

    override fun comicsClick(position: Int) {
        val comic = listaComics.get(position)
//        var imagem = comic.imagemComic
//        var nome = comic.nomeComic
//        var data = comic.dataVendaComic
//        var criador = comic.criadorComic
        adapterComics.notifyItemChanged(position)
//        ActivityDetalheHq(comic)
    }

    override fun seriesClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun ActivityDetalheHq(detalheHq: Comics) {
        var intent = Intent(this, DetalheHqActivity::class.java)
        intent.putExtra("ComicsCh", detalheHq)
        startActivity(intent)
    }

}