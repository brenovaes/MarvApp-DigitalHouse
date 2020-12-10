package com.digitalhouse.br.marvelapp.ui.busca

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.service.serviceB
import kotlinx.android.synthetic.main.fragment_busca_criadores.*



class BuscaCriadoresFragment : Fragment(), BCriadoresAdapter.OnBCriadoresClickListener {
    var listCriadores = arrayListOf<ResultsCr>()
    private lateinit var cf: ContractDetalheCardsFragments
    lateinit var adapterC: BCriadoresAdapter




    val viewModelBusca by viewModels<BuscaViewModel>{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return BuscaViewModel(serviceB) as T
            }
        }
    }

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
        viewModelBusca.retornoAllCreators.observe(viewLifecycleOwner){
            listCriadores.addAll(it.data.results)
            adapterC = BCriadoresAdapter(listCriadores, this)
            rvBuscaCr.adapter = adapterC

        }

        viewModelBusca.getAllCreatorsBusca()
    }


    companion object {
        fun newInstance() = BuscaCriadoresFragment()
    }


    override fun bCriadoresClick(position: Int) {
        adapterC.notifyItemChanged(position)
        viewModelBusca.retornoAllCreators.observe(this){

            cf.callDetalhesCCards(it.data.results[position].id)
        }

    }
}