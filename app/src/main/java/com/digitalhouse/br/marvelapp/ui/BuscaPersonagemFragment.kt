package com.digitalhouse.br.marvelapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import kotlinx.android.synthetic.main.fragment_busca_personagem.*
import kotlinx.android.synthetic.main.fragment_busca_personagem.view.*

class BuscaPersonagemFragment : Fragment(), BPersonagemAdapter.OnBPersonagemClickListener {
    var listPersonagens:ArrayList<Characters> = getPersonagens()
    private lateinit var cf: ContractDetalheCardsFragments
    var adapterB = BPersonagemAdapter(listPersonagens, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_personagem, container, false)


        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheCardsFragments) cf = context
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

    fun getPersonagens():ArrayList<Characters>{
        return arrayListOf(
                Characters(1,R.drawable.omiranha,"Personagem1",),
                Characters(2,R.drawable.omiranha,"Personagem1",),
                Characters(3,R.drawable.omiranha,"Personagem1",),
                Characters(4,R.drawable.omiranha,"Personagem1",),
                Characters(5,R.drawable.omiranha,"Personagem1",),
                Characters(6,R.drawable.omiranha,"Personagem1",),
                Characters(7,R.drawable.omiranha,"Personagem1",)

        )
    }

    override fun bPersonagemClick(position: Int) {
        adapterB.notifyItemChanged(position)
        cf.callDetalhesPCards()
    }
}