package com.digitalhouse.br.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.fragment_busca_h_q.*
import kotlinx.android.synthetic.main.fragment_busca_personagem.*

class BuscaHQFragment : Fragment(), BPersonagemAdapter.OnBPersonagemClickListener {
    var listHQ:ArrayList<EntesMarvel> = getHQ()

    var adapterB = BPersonagemAdapter(listHQ, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_h_q, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBuscaHQ.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = adapterB
        }
    }

    companion object {
        fun newInstance() = BuscaHQFragment()
    }


    fun getHQ():ArrayList<EntesMarvel>{
        return arrayListOf(
                EntesMarvel("HQ1",R.drawable.teste),
                EntesMarvel("HQ12",R.drawable.teste),
                EntesMarvel("HQ3",R.drawable.teste),
                EntesMarvel("HQ4",R.drawable.teste),
                EntesMarvel("HQ5",R.drawable.teste),
                EntesMarvel("HQ5",R.drawable.teste),
                EntesMarvel("HQ5",R.drawable.teste),
                EntesMarvel("HQ5",R.drawable.teste)
        )
    }

    override fun bPersonagemClick(position: Int) {
        //click no card
    }
}

