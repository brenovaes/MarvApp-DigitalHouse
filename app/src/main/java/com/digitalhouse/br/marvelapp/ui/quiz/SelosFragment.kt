package com.digitalhouse.br.marvelapp.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.digitalhouse.br.marvelapp.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_selos.*

class SelosFragment : Fragment(), SeloAdapter.OnSeloClickListener {
    var listSelo =  arrayListOf<Selo>()
    lateinit var adapter: SeloAdapter

    val viewModelQuiz by viewModels<QuizViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return QuizViewModel(
                        crTri1,
                        crTri2,
                        crTri3,
                        crTri4,
                        crPontosTr,
                        crHank
                ) as T
            }
        }
    }

    private fun getListaSelo(): ArrayList<Selo> {
        viewModelQuiz.getPontosTrilhas()

        var listaSeloCinza = arrayListOf<Selo>(
                Selo(R.drawable.stamp_vision_grey, "Knowledge Source"),
                Selo(R.drawable.stamp_iron_heart_grey, "Marvel Apprentice"),
                Selo(R.drawable.stamp_avengers_grey, "Almost an Avenger"),
                Selo(R.drawable.stamp_x_school_grey, "Knowledge Learner"),
                Selo(R.drawable.stamp_spider_grey, "Neighborhood Hero"),
                Selo(R.drawable.stamp_angel_grey, "Horseman of Apocalypse"))

//
//        if (viewModelQuiz.checkH.value == true) {
//            listaSeloCinza.forEach {
//                if (it.nomeSelo == "Knowledge Learner") {
//                    it.imageResource = R.drawable.stamp_x_school_color
//                }
//            }
//        }





//        viewModelQuiz.pontosTrilha01.observe(viewLifecycleOwner){
//            if(it)
//
//        }


        return listaSeloCinza
//        arrayListOf<Selo>(
//            Selo(R.drawable.stamp_vision_grey, "Knowledge Source"),
//            Selo(R.drawable.stamp_iron_heart_grey, "Marvel Apprentice"),
//            Selo(R.drawable.stamp_avengers_grey, "Almost an Avenger"),
//
//            Selo(R.drawable.stamp_x_school_grey, "Knowledge Learner"),
//            Selo(R.drawable.stamp_spider_grey, "Neighborhood Hero"),
//            Selo(R.drawable.stamp_angel_grey, "Horseman of Apocalypse")
//        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_selos, container, false)
        viewModelQuiz.checkHancking(FirebaseAuth.getInstance().currentUser!!.email!!)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         listSelo = getListaSelo()


        viewModelQuiz.updatePontuacao()
        Log.i("PONTOS TOTAL USER SELO", viewModelQuiz.pontosTotalUser.value.toString())
        if (viewModelQuiz.pontosTotalUser.value == 460) {
            listSelo.forEach { selo ->
                if (selo.nomeSelo == "Almost an Avenger") {
                    selo.imageResource = R.drawable.stamp_avengers_color
                }
            }
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = SeloAdapter(listSelo, this)
        rvSelos.adapter = adapter
        rvSelos.layoutManager = GridLayoutManager(activity, 2)
        rvSelos.setHasFixedSize(true)

    }

    companion object {
        fun newInstance() = SelosFragment()
    }

    override fun seloClick(position: Int) {
    }


}

