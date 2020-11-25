package com.digitalhouse.br.marvelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_selos.*

class SelosFragment : Fragment() {
    private var listaSelo = getListaSelo()
    private var adapter = SeloAdapter(listaSelo)

    private fun getListaSelo(): ArrayList<Selo> {
        return arrayListOf<Selo>(
            Selo(R.color.black, "Knowledge Source"),
            Selo(R.color.black, "Marvel Apprentice"),
            Selo(R.color.black, "Almost an Avenger"),
            Selo(R.color.black, "Knowledge Beginner"),
            Selo(R.color.black, "Neighborhood Hero")
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selos, container, false)
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

