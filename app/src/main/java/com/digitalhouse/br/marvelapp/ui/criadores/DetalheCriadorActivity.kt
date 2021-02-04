package com.digitalhouse.br.marvelapp.ui.criadores

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.nfc.tech.MifareUltralight.PAGE_SIZE
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
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.models.Comics
import com.digitalhouse.br.marvelapp.models.HistoryDB
import com.digitalhouse.br.marvelapp.service.*
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.digitalhouse.br.marvelapp.ui.iniciais.LoginActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class DetalheCriadorActivity : AppCompatActivity(),
        ComicsCreatorsAdapter.OnComicsCreatorsClickListener,
        SeriesCreatorsAdapter.OnSeriesCreatorsClickListener,
        EventsCreatorsAdapter.OnEventsCreatorsClickListener {

    //lateinit var listaComics: ArrayList<ItemsCo?>

    var creator = arrayListOf<ResultsCr>()
    lateinit var adapterComics: ComicsCreatorsAdapter
    lateinit var adapterSeries: SeriesCreatorsAdapter
    lateinit var adapterEventos: EventsCreatorsAdapter
    lateinit var lManager: LinearLayoutManager

//    lateinit var db:AppDataBase
    private lateinit var repositoryHistory: RepositoryHistory
    private lateinit var repositorySuggestions: RepositorySuggestions
    private var idCreator by Delegates.notNull<Int>()
    var pageCreator = 0
    var scope = CoroutineScope(Dispatchers.Main)

    var fav = 0

    val viewModelCreators by viewModels<CreatorsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CreatorsViewModel(serviceCr, repositoryHistory, repositorySuggestions) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_criador)

        repositoryHistory = RepositoryImplHistory(AppDataBase.invoke(this).historyDao())
        repositorySuggestions= RepositoryImplSuggestions(AppDataBase.invoke(this).suggestionsDao())

        idCreator = intent.getIntExtra("id", 0)


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
                tvQtdComicsCriador.text = it.data.total.toString()
                adapterComics = ComicsCreatorsAdapter(it.data.results, this)
                rvComicsCriador.adapter = adapterComics
            }

            viewModelCreators.retornoCreatorEvents.observe(this) {
                tvQtdEventosCriador.text = it.data.total.toString()
                adapterEventos = EventsCreatorsAdapter(it.data.results, this)
                rvEventosCriador.adapter = adapterEventos
            }

            viewModelCreators.retornoCreatorSeries.observe(this){
                tvQtdSeriesCriador.text = it.data.total.toString()
                adapterSeries = SeriesCreatorsAdapter(it.data.results, this)
                rvSeriesCriador.adapter = adapterSeries
            }
        }

        viewModelCreators.getCreator(idCreator)
        viewModelCreators.getCreatorComics(idCreator, pageCreator)
        viewModelCreators.getCreatorEvents(idCreator)
        viewModelCreators.getCreatorSeries(idCreator)

        lManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvComicsCriador.layoutManager = lManager
        rvComicsCriador.setHasFixedSize(true)


        rvSeriesCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvSeriesCriador.setHasFixedSize(true)


        rvEventosCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvEventosCriador.setHasFixedSize(true)

        ivFavoritoDetalheCriador.setOnClickListener {
            checkfavorite()
        }
        scope.launch {
            delay(1000)
            setScrollView ()
        }
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
        viewModelCreators.retornoCreatorComics.observe(this) {
            activityDetalheHq(it.data.results[position].id)
        }
    }

    override fun eventsCreatorsClick(position: Int) {
        var url: String
        viewModelCreators.retornoCreatorEvents.observe(this){
            url = it.data.results[position].urls[0].url
            Log.i("eventsCreatorsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun seriesCreatorsClick(position: Int) {
        var url: String
        viewModelCreators.retornoCreatorSeries.observe(this) {
            url = it.data.results[position].urls[0].url
            Log.i("seriesCreatorsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    fun activityDetalheHq(id: Int){
        var intent = Intent(this, DetalheHqActivity::class.java)
        intent.putExtra("idCo", id)
        startActivity(intent)
    }

    fun checkfavorite() {
        when(fav){
            0->  {
                ivFavoritoDetalheCriador.setImageResource(R.drawable.heart_filled)
                fav = 1
            }

            1-> {
                ivFavoritoDetalheCriador.setImageResource(R.drawable.heart)
                fav = 0
            }
        }

    }

    fun setScrollView (){


        rvComicsCriador.run {
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val itemVisible = lManager?.childCount
                    val pastItem = lManager.findFirstVisibleItemPosition()
                    val total = adapterComics.itemCount
                    Log.i("LOADING", viewModelCreators.loading.toString())

                    if ((itemVisible + pastItem) >= total && !viewModelCreators.loading){
                        viewModelCreators.loading = true
                        viewModelCreators.getCreatorComics(idCreator, pageCreator)
                        pageCreator += 10


                        Log.i("AAAAA", pageCreator.toString())
                    }
                }
            })
        }
    }

}