package com.digitalhouse.br.marvelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.digitalhouse.br.marvelapp.R

class FavoritoBuscaCriadorFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fav_busca_criadores, container, false)
        return view
    }

    companion object {
        fun newInstance() = FavoritoBuscaCriadorFrag()
    }
}