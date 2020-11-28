package com.digitalhouse.br.marvelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.fragment_fav_busca_personagem.*

class FavoritoBuscaPersonagemFrag : Fragment(), FavoritoAdapter.OnFavoritoPersonagemClickListener {

    var listPersonagens:ArrayList<Characters> = getPersonagens()
    var adapterF = FavoritoAdapter(listPersonagens, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fav_busca_personagem, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFavoritoBusca.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = adapterF
        }



    }

    companion object {

        fun newInstance() =  FavoritoBuscaPersonagemFrag()
    }

    fun getPersonagens():ArrayList<Characters>{
        return arrayListOf(
            Characters( 1, R.drawable.omiranha,"Nome1"),
            Characters(2, R.drawable.omiranha,"Nome1"),
            Characters(3, R.drawable.omiranha,"Nome2"),
            Characters(4, R.drawable.omiranha,"Nome3"),
            Characters(5, R.drawable.omiranha,"Nome4"),
            Characters(6, R.drawable.omiranha,"Nome5"),
            Characters(7, R.drawable.omiranha,"Nome6"),
            Characters(8, R.drawable.omiranha,"Nome7"),
            Characters(9, R.drawable.omiranha,"Nome8"),
            Characters(10, R.drawable.omiranha,"Nome9")
        )
    }

    override fun fPersonagemClick(position: Int) {
    }
}