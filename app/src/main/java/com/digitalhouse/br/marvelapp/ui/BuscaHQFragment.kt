package com.digitalhouse.br.marvelapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.interfac.ContractRedefinirSenha
import kotlinx.android.synthetic.main.fragment_busca_h_q.*

class BuscaHQFragment : Fragment(), BHQAdapter.OnBHQClickListener {
    var listHQ:ArrayList<Comics> = getHQ()
    private lateinit var cf: ContractDetalheCardsFragments


    var adapterB =BHQAdapter(listHQ, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_h_q, container, false)
        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheCardsFragments) cf = context
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


    fun getHQ():ArrayList<Comics>{
        return arrayListOf(
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee"),
                Comics(1,R.drawable.comic, "Spider-Man: 101 Ways to End the Clone Saga (1997) #1", "22/03/2020","Stan Lee")
        )
    }

    override fun bHQClick(position: Int) {
        adapterB.notifyItemChanged(position)
        cf.callDetalhesHQCards()
    }

}