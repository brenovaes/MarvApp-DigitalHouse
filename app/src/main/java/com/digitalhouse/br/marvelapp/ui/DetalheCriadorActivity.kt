package com.digitalhouse.br.marvelapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R

class DetalheCriadorActivity :
    AppCompatActivity(),
    ComicsAdapter.OnComicsClickListener,
    SeriesAdapter.OnSeriesClickListener,
    EventsAdapter.OnEventsClickListener {

    var listaComics: ArrayList<Comics> = getComics()
    var listaSeries: ArrayList<Series> = getSeries()
    var listaEventos: ArrayList<Events> = getEvents()

    private fun getEvents(): ArrayList<Events> {
        var eventos = Events(1, (R.drawable.events),"Acts of Vengeance!","Start: 10/12/1989", "End: 04/01/2008")
        return arrayListOf(eventos, eventos, eventos, eventos, eventos, eventos)
    }
    private fun getComics(): ArrayList<Comics> {
        var comics = Comics(1,(R.drawable.comic),"Spider-Man: 101 Ways to End the Clone Saga (1997) #1","Sale date: 01/01/1997","Creator: Mark Bernardo")
        return arrayListOf(comics,comics,comics,comics,comics,comics,comics)
    }
    private fun getSeries(): ArrayList<Series> {
        var series = Series(1,(R.drawable.serie),"Superior Spider-Man Vol. 2: Otto-matic (2019)","Sale date: 01/01/1997")
        return arrayListOf(series,series,series,series)
    }

    var adapterComics: ComicsAdapter = ComicsAdapter(listaComics, this)
    var adapterSeries: SeriesAdapter = SeriesAdapter(listaSeries, this)
    var adapterEventos: EventsAdapter = EventsAdapter(listaEventos, this)

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_criador)


        setSupportActionBar(tbDetalheCriador)
        tbDetalheCriador.setNavigationOnClickListener{
            onBackPressed()
        }

        tvQtdComicsCriador.text = listaComics.size.toString()
        tvQtdSeriesCriador.text = listaSeries.size.toString()
        tvQtdEventosCriador.text = listaEventos.size.toString()

        rvComicsCriador.adapter = adapterComics
        rvComicsCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvComicsCriador.setHasFixedSize(true)

        rvSeriesCriador.adapter = adapterSeries
        rvSeriesCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvSeriesCriador.setHasFixedSize(true)

        rvEventosCriador.adapter = adapterEventos
        rvEventosCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvEventosCriador.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_share -> {
                Toast.makeText(this, "Compartilhar Criador", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }

    override fun comicsClick(position: Int) {
        val comic = listaComics.get(position)
        var imagem = comic.imagemComic
        var nome = comic.nomeComic
        var data = comic.dataVendaComic
        var criador = comic.criadorComic
        adapterComics.notifyItemChanged(position)
        ActivityDetalheHq(comic)
    }

    override fun seriesClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun eventsClick(position: Int) {
        TODO("Not yet implemented")
    }

    fun ActivityDetalheHq (detalheHq: Comics){
        var intent = Intent(this, DetalheHqActivity::class.java)
        intent.putExtra("Comics", detalheHq)
        startActivity(intent)
    }

}