package com.digitalhouse.br.marvelapp.ui.hqs

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
import kotlinx.android.synthetic.main.activity_detalhe_hq.*
import androidx.recyclerview.widget.OrientationHelper.HORIZONTAL
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.crFCo
import com.digitalhouse.br.marvelapp.database.AppDataBase
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.models.*
import com.digitalhouse.br.marvelapp.service.*
import com.digitalhouse.br.marvelapp.ui.criadores.DetalheCriadorActivity
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_criador.*
import kotlinx.android.synthetic.main.activity_detalhe_personagem.*


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

    private lateinit var repositoryHistory: RepositoryHistory
    private lateinit var repositorySuggestions:RepositorySuggestions

    var userId = FirebaseAuth.getInstance().currentUser!!.uid


    val viewModelComics by viewModels<ComicsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicsViewModel(serviceCo, repositoryHistory,repositorySuggestions, crFCo) as T
            }
        }
    }




    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_hq)

        repositoryHistory = RepositoryImplHistory(AppDataBase.invoke(this).historyDao())
        repositorySuggestions = RepositoryImplSuggestions(AppDataBase.invoke(this).suggestionsDao())

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
                tvQtdCriadoresHq.text = it.data.total.toString()
                adapterCreators =  CreatorsComicsAdapter(it.data.results,this, creators)
                rvCriadoresHq.adapter = adapterCreators

            }

            viewModelComics.retornoComicsCharacters.observe(this){
                tvQtdPersonagensHq.text = it.data.total.toString()
                adapterCharacters = CharactersComicsAdapter(it.data.results, this)
                rvPersonagensHq.adapter = adapterCharacters

            }

            //sem valor pois a função n está sendo chamada
            viewModelComics.retornoComicsSeries.observe(this) {
                tvQtdSeriesHq.text = it.data.total.toString()
                adapterSeries = SeriesComicsAdapter(it.data.results, this)
                rvSeriesHq.adapter = adapterSeries

            }

            viewModelComics.retornoComicsEvents.observe(this) {
                tvQtdEventosHq.text = it.data.total.toString()
                adapterEventos = EventsComicsAdapter(it.data.results, this)
                rvEventosHq.adapter = adapterEventos

            }

            viewModelComics.retornoComicsStories.observe(this) {
                tvQtdHistoriasHq.text = it.data.total.toString()
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

        viewModelComics.checkCollection()
        viewModelComics.checkFavoriteF(idComic, userId)

        ivFavoritoDetalheHq.setOnClickListener {
            var favC = viewModelComics.retornoComic.value!!.data.results[0]

            viewModelComics.checkC.observe(this){
                if (it == null || it== true){

                    viewModelComics.addCreatorFav(UserFavComic(
                        userId, favC.id,
                        FavComic(
                            favC.id,
                            favC.title,
                            favC.thumbnail.extension,
                            favC.thumbnail.path,
                            "Comic"
                        )
                    )
                    )
                    viewModelComics.addIconCh()

                }else{
                    viewModelComics.checkF.observe(this){
                        if(it){
                            viewModelComics.deleteCreatorFav(userId, idComic)
                            viewModelComics.deletIconCh()

                        } else {

                            viewModelComics.addCreatorFav(
                                UserFavComic(
                                userId, favC.id,
                                FavComic(
                                    favC.id,
                                    favC.title,
                                    favC.thumbnail.extension,
                                    favC.thumbnail.path,
                                    "Comic"
                                )
                                )
                            )
                            viewModelComics.addIconCh()
                        }
                    }
                }
            }
            this.recreate()
        }

        checkfavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var url_share = comics[0].urls[0].url
        Log.i("DetalhesHqActivityTAG", url_share)

        when (item.itemId) {
            R.id.menu_share -> {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url_share)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
//                Toast.makeText(this, "Compartilhar Quadrinhos", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return false
    }



    private fun activityDetalheCriador(id: Int) {
        var intent = Intent(this, DetalheCriadorActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }


    private fun activityDetalheCharacter(id: Int) {
        var intent = Intent(this, DetalhePersonagemActivity::class.java)
        intent.putExtra("idCh", id)
        startActivity(intent)
    }


    override fun creatorsComicsClick(position: Int) {
        viewModelComics.retornoComicsCreator.observe(this) {
            activityDetalheCriador(it.data.results[position].id)
        }
    }

    override fun charactersComicsClick(position: Int) {
        viewModelComics.retornoComicsCharacters.observe(this) {
            Log.i("haractersComicsClick",it.data.results[0].id.toString() )
            activityDetalheCharacter(it.data.results[position].id)
        }
    }

    override fun seriesComicsClick(position: Int) {
        var url: String
        viewModelComics.retornoComicsSeries.observe(this){
            url = it.data.results[position].urls[0].url
            Log.i("seriesComicsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun eventsComicsClick(position: Int) {
        var url: String
        viewModelComics.retornoComicsEvents.observe(this){
            url = it.data.results[position].urls[0].url
            Log.i("eventsComicsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun storiesComicsClick(position: Int) {
        var url: String
        viewModelComics.retornoComic.observe(this){
            url = it.data.results[0].urls[0].url
            Log.i("storiesComicsClick", url)
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }

    fun checkfavorite() {

        viewModelComics.checkIdC.observe(this){
            when (it) {
                1 -> ivFavoritoDetalheCriador.setImageResource(R.drawable.heart_filled)

                0 -> ivFavoritoDetalheCriador.setImageResource(R.drawable.heart)

            }
        }

    }
}

