package com.digitalhouse.br.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.fragment_busca_criadores.*
import kotlinx.android.synthetic.main.fragment_busca_h_q.*


class BuscaCriadoresFragment : Fragment(), BPersonagemAdapter.OnBPersonagemClickListener {
    var listCriadores:ArrayList<EntesMarvel> = getCriadores()

    var adapterB = BPersonagemAdapter(listCriadores, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_criadores, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBuscaCr.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = adapterB
        }
    }


    companion object {
        fun newInstance() = BuscaCriadoresFragment()
    }

    fun getCriadores():ArrayList<EntesMarvel>{
        return arrayListOf(
                EntesMarvel("Criador1",R.drawable.teste),
                EntesMarvel("Criador2",R.drawable.teste),
                EntesMarvel("Criador3",R.drawable.teste),
                EntesMarvel("Criador4",R.drawable.teste),
                EntesMarvel("Criador5",R.drawable.teste),
                EntesMarvel("Criador5",R.drawable.teste),
                EntesMarvel("Criador5",R.drawable.teste),
                EntesMarvel("Criador5",R.drawable.teste)
        )
    }

    override fun bPersonagemClick(position: Int) {
        //click no card
    }
}