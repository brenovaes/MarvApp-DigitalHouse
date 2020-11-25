package com.digitalhouse.br.marvelapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.fragment_busca_personagem.*
import kotlinx.android.synthetic.main.fragment_busca_personagem.view.*

class BuscaPersonagemFragment : Fragment(), BPersonagemAdapter.OnBPersonagemClickListener {
    var listPersonagens:ArrayList<EntesMarvel> = getPersonagens()

    var adapterB = BPersonagemAdapter(listPersonagens, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_personagem, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBuscaP.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = adapterB
        }



    }

    companion object {

        fun newInstance() =  BuscaPersonagemFragment()
    }

    fun getPersonagens():ArrayList<EntesMarvel>{
        return arrayListOf(
            EntesMarvel("Personagem1",R.drawable.teste),
            EntesMarvel("Personagem2",R.drawable.teste),
            EntesMarvel("Personagem3",R.drawable.teste),
            EntesMarvel("Personagem4",R.drawable.teste),
            EntesMarvel("Personagem5",R.drawable.teste),
            EntesMarvel("Personagem5",R.drawable.teste),
            EntesMarvel("Personagem5",R.drawable.teste),
            EntesMarvel("Personagem5",R.drawable.teste),
            EntesMarvel("Personagem6",R.drawable.teste),
            EntesMarvel("Personagem6",R.drawable.teste)
        )
    }

    override fun bPersonagemClick(position: Int) {

    }
}