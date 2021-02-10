package com.digitalhouse.br.marvelapp.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.digitalhouse.br.marvelapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_selos.*

class SelosFragment : Fragment() {
    private var listaSelo = getListaSelo()
    private var adapter = SeloAdapter(listaSelo)

    private fun getListaSelo(): ArrayList<Selo> {
        return arrayListOf<Selo>(
            Selo(R.drawable.stamp_vision_grey, "Knowledge Source"),
            Selo(R.drawable.stamp_iron_heart_grey, "Marvel Apprentice"),
            Selo(R.drawable.stamp_avengers_grey, "Almost an Avenger"),
            Selo(R.drawable.stamp_x_school_grey, "Knowledge Learner"),
            Selo(R.drawable.stamp_spider_grey, "Neighborhood Hero"),
            Selo(R.drawable.stamp_angel_grey, "Horseman of Apocalypse")
        )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_selos, container, false)
        
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rvSelos.adapter = adapter
        rvSelos.layoutManager = GridLayoutManager(activity,2)
        rvSelos.setHasFixedSize(true)
    }

    companion object {
        fun newInstance() = SelosFragment()
    }


}

