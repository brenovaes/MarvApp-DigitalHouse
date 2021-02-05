package com.digitalhouse.br.marvelapp.ui.criadores

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
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.crFCr
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.models.FavCreator
import com.digitalhouse.br.marvelapp.models.UserFavCreator
import com.digitalhouse.br.marvelapp.service.*
import com.digitalhouse.br.marvelapp.ui.hqs.DetalheHqActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*


class DetalheCriadorActivity : AppCompatActivity(),
        ComicsCreatorsAdapter.OnComicsCreatorsClickListener,
        SeriesCreatorsAdapter.OnSeriesCreatorsClickListener,
        EventsCreatorsAdapter.OnEventsCreatorsClickListener {

    //lateinit var listaComics: ArrayList<ItemsCo?>

    var creator = arrayListOf<ResultsCr>()
    lateinit var adapterComics: ComicsCreatorsAdapter
    lateinit var adapterSeries: SeriesCreatorsAdapter
    lateinit var adapterEventos: EventsCreatorsAdapter

//    lateinit var db:AppDataBase
    private lateinit var repositoryHistory: RepositoryHistory
    private lateinit var repositorySuggestions: RepositorySuggestions

    var userId = FirebaseAuth.getInstance().currentUser!!.uid


    val viewModelCreators by viewModels<CreatorsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CreatorsViewModel(serviceCr, repositoryHistory, repositorySuggestions, crFCr) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_criador)

        repositoryHistory = RepositoryImplHistory(AppDataBase.invoke(this).historyDao())
        repositorySuggestions= RepositoryImplSuggestions(AppDataBase.invoke(this).suggestionsDao())

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
        viewModelCreators.getCreatorComics(idCreator)
        viewModelCreators.getCreatorEvents(idCreator)
        viewModelCreators.getCreatorSeries(idCreator)


        rvComicsCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvComicsCriador.setHasFixedSize(true)


        rvSeriesCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvSeriesCriador.setHasFixedSize(true)


        rvEventosCriador.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rvEventosCriador.setHasFixedSize(true)

        viewModelCreators.checkCollection()
        viewModelCreators.checkFavoriteF(idCreator, userId)

        ivFavoritoDetalheCriador.setOnClickListener {
            var favC = viewModelCreators.retornoCreator.value!!.data.results[0]

            viewModelCreators.checkC.observe(this){
                if (it == null || it== true){

                    viewModelCreators.addCreatorFav(UserFavCreator(
                        userId, favC.id,
                        FavCreator(
                            favC.fullName,
                            favC.thumbnail.extension,
                            favC.thumbnail.path,
                            "Creator"
                        )
                    )
                    )
                    viewModelCreators.addIconCh()

                }else{
//                    viewModelCreators.checkF.observe(this){
                        if(viewModelCreators.checkF.value == true){
                            viewModelCreators.deleteCreatorFav(userId, idCreator)
                            viewModelCreators.deletIconCh()

                        } else {

                            viewModelCreators.addCreatorFav(UserFavCreator(
                                userId, favC.id,
                                FavCreator(
                                    favC.fullName,
                                    favC.thumbnail.extension,
                                    favC.thumbnail.path,
                                    "Creator"
                                )))
                            viewModelCreators.addIconCh()
                        }
//                    }
                }
            }
            this.recreate()
        }

        checkfavorite()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var url_share = creator[0].urls[0].url
        Log.i("DetalhesCriadorActivityTAG", url_share)

        when (item.itemId) {
            R.id.menu_share -> {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url_share)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

//                Toast.makeText(this, "Share Creator", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.menu_share -> {
//                Toast.makeText(this, "Share Creator", Toast.LENGTH_SHORT).show()
//                return true
//            }
//        }
//        return false
//    }

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

        viewModelCreators.checkIdC.observe(this){
            when (it) {
                1 -> ivFavoritoDetalheCriador.setImageResource(R.drawable.heart_filled)

                0 -> ivFavoritoDetalheCriador.setImageResource(R.drawable.heart)

            }
        }

    }

}