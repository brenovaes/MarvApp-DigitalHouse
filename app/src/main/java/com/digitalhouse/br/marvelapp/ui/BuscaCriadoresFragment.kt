package com.digitalhouse.br.marvelapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import kotlinx.android.synthetic.main.fragment_busca_criadores.*


class BuscaCriadoresFragment : Fragment(), BCriadoresAdapter.OnBCriadoresClickListener {
    var listCriadores:ArrayList<Creators> = getCriadores()
    private lateinit var cf: ContractDetalheCardsFragments
    var adapterC = BCriadoresAdapter(listCriadores, this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_busca_criadores, container, false)
        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheCardsFragments) cf = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBuscaCr.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            // set the custom adapter to the RecyclerView
            adapter = adapterC
        }
    }


    companion object {
        fun newInstance() = BuscaCriadoresFragment()
    }

    fun getCriadores():ArrayList<Creators>{
        return arrayListOf(
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer"),
                Creators(1,R.drawable.stanlee, "Stan Lee", "Writer")
        )
    }

    override fun bCriadoresClick(position: Int) {
        adapterC.notifyItemChanged(position)
        cf.callDetalhesCCards()
    }
}