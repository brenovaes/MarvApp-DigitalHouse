package com.digitalhouse.br.marvelapp.ui.criadores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.models.Comics
import com.digitalhouse.br.marvelapp.service.serviceCr
import com.squareup.picasso.Picasso


class DetalheCriadorActivity : AppCompatActivity(),
        ComicsCreatorsAdapter.OnComicsCreatorsClickListener,
        SeriesCreatorsAdapter.OnSeriesCreatorsClickListener,
        EventsCreatorsAdapter.OnEventsCreatorsClickListener {

    //lateinit var listaComics: ArrayList<ItemsCo?>

    var creator = arrayListOf<ResultsCr>()
    lateinit var adapterComics: ComicsCreatorsAdapter
    lateinit var adapterSeries: SeriesCreatorsAdapter
    lateinit var adapterEventos: EventsCreatorsAdapter

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

            viewModelCreators.retornoCreatorComics.observe(this){
                tvQtdComicsCriador.text = it.data.results.size.toString()
                adapterComics = ComicsCreatorsAdapter(it.data.results, this)
                rvComicsCriador.adapter = adapterComics
            }

            viewModelCreators.retornoCreatorEvents.observe(this) {
                tvQtdEventosCriador.text = it.data.results.size.toString()
                adapterEventos = EventsCreatorsAdapter(it.data.results, this)
                rvEventosCriador.adapter = adapterEventos
            }

            viewModelCreators.retornoCreatorSeries.observe(this){
                tvQtdSeriesCriador.text = it.data.results.size.toString()
                adapterSeries = SeriesCreatorsAdapter(it.data.results, this)
                rvSeriesCriador.adapter = adapterSeries
            }
        }

        viewModelCreators.getCreator(idCreator)
        viewModelCreators.getCreatorComics(idCreator)
        viewModelCreators.getCreatorEvents(idCreator)
        viewModelCreators.getCreatorSeries(idCreator)


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

    override fun comicsCreatorsClick(position: Int) {
//        val comic = listaComics.get(position)
//        adapterComics.notifyItemChanged(position)
//        ActivityDetalheHq(comic)
    }

    override fun seriesCreatorsClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsCreatorsClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun ActivityDetalheHq(detalheHq: Comics) {
//        var intent = Intent(this, DetalheHqActivity::class.java)
//        intent.putExtra("ComicsCh", detalheHq)
//        startActivity(intent)
    }

}