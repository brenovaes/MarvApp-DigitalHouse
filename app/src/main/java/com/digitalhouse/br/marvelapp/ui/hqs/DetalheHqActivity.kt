package com.digitalhouse.br.marvelapp.ui.hqs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detalhe_hq.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.*
import com.digitalhouse.br.marvelapp.ui.criadores.CreatorsAdapter
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.events.EventsAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.CharactersAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.digitalhouse.br.marvelapp.ui.series.SeriesAdapter
import com.digitalhouse.br.marvelapp.ui.stories.StoriesAdapter

class DetalheHqActivity :
    AppCompatActivity(),
        StoriesAdapter.OnStoriesClickListener,
    CreatorsAdapter.OnCreatorsClickListener,
        CharactersAdapter.OnCharactersClickListener,
        SeriesAdapter.OnSeriesClickListener,
        EventsAdapter.OnEventsClickListener {

    var listaStories: ArrayList<Stories> = getStories()
    var listaSeries: ArrayList<Series> = getSeries()
    var listaEventos: ArrayList<Events> = getEvents()
    var listaCharacters: ArrayList<Characters> = getCharacters()
    var listaCreators: ArrayList<Creators> = getCreators()

    private fun getEvents(): ArrayList<Events> {
        var eventos = Events(1, (R.drawable.events),"Acts of Vengeance!","Start: 10/12/1989", "End: 04/01/2008")
        return arrayListOf(eventos, eventos, eventos, eventos, eventos, eventos)
    }
    private fun getStories(): ArrayList<Stories> {
        var storie = Stories(1,(R.drawable.serie),"Superior Spider-Man Vol. 2: Otto-matic (2019)","Texto que representa algo da história")
        return arrayListOf(storie,storie,storie,storie,storie,storie,storie)
    }
    private fun getSeries(): ArrayList<Series> {
        var series = Series(1,(R.drawable.serie),"Superior Spider-Man Vol. 2: Otto-matic (2019)","Start: 10/12/1989")
        return arrayListOf(series,series,series,series)
    }
    private fun getCharacters(): ArrayList<Characters> {
        var chars = Characters(1,(R.drawable.omiranha),"Spider-Man")
        return arrayListOf(chars,chars,chars,chars,chars,chars,chars)
    }
    private fun getCreators(): ArrayList<Creators> {
        var creator = Creators(1,(R.drawable.stanlee),"Stan Lee","Writter")
        return arrayListOf(creator,creator,creator,creator)
    }

    var adapterStories: StoriesAdapter = StoriesAdapter(listaStories, this)
    var adapterSeries: SeriesAdapter = SeriesAdapter(listaSeries, this)
    var adapterEventos: EventsAdapter = EventsAdapter(listaEventos, this)
    var adapterCharacters: CharactersAdapter = CharactersAdapter(listaCharacters, this)
    var adapterCreators: CreatorsAdapter = CreatorsAdapter(listaCreators, this)

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_hq)

        setSupportActionBar(tbDetalheHq)
        tbDetalheHq.setNavigationOnClickListener {
            onBackPressed()
        }

        tvQtdCriadoresHq.text = listaCreators.size.toString()
        tvQtdHistoriasHq.text = listaStories.size.toString()
        tvQtdSeriesHq.text = listaSeries.size.toString()
        tvQtdEventosHq.text = listaEventos.size.toString()
        tvQtdPersonagensHq.text = listaCharacters.size.toString()

        rvHistoriasHq.adapter = adapterStories
        rvHistoriasHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvHistoriasHq.setHasFixedSize(true)

        rvSeriesHq.adapter = adapterSeries
        rvSeriesHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvSeriesHq.setHasFixedSize(true)

        rvEventosHq.adapter = adapterEventos
        rvEventosHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvEventosHq.setHasFixedSize(true)

        rvPersonagensHq.adapter = adapterCharacters
        rvPersonagensHq.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        rvPersonagensHq.setHasFixedSize(true)

        rvCriadoresHq.adapter = adapterCreators
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

    override fun creatorsClick(position: Int) {
        val creator = listaCreators.get(position)
        var imagem = creator.imagemCriador
        var nome = creator.nomeCriador
        var funcao = creator.funcaoCriador
        adapterCreators.notifyItemChanged(position)
        ActivityDetalheCriador(creator)
    }

    private fun ActivityDetalheCriador(creator: Creators) {
        var intent = Intent(this, DetalheCriadorActivity::class.java)
        intent.putExtra("ComicsCh", creator)
        startActivity(intent)
    }

    override fun charactersClick(position: Int) {
        val char = listaCharacters.get(position)
        var imagem = char.imagemCharacter
        var nome = char.nomeCharacter
        adapterCharacters.notifyItemChanged(position)
        ActivityDetalheCharacter(char)
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
}

