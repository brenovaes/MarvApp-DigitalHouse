package com.digitalhouse.br.marvelapp.ui.busca

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.service.serviceB
import kotlinx.android.synthetic.main.fragment_busca_h_q.*

class BuscaComicsFragment : Fragment(), BHQAdapter.OnBHQClickListener {
    var listHQ = arrayListOf<ResultsCo>()
    private lateinit var cf: ContractDetalheCardsFragments
    lateinit var adapterCo: BHQAdapter
    var busca = 0

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
        var view = inflater.inflate(R.layout.fragment_busca_h_q, container, false)
        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheCardsFragments) cf = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelBusca.retornoAllComics.observe(viewLifecycleOwner){
            listHQ.addAll(it.data.results)
            adapterCo= BHQAdapter(listHQ, this)
            rvBuscaHQ.adapter = adapterCo
        }


        viewModelBusca.getAllComics()

        ivBBuscaHQ.setOnClickListener {
            viewModelBusca.retornoAllComicsBusca.observe(viewLifecycleOwner) {
                listHQ.clear()
                listHQ.addAll(it.data.results)
                rvBuscaHQ.adapter =  BHQAdapter(listHQ, this)
                busca = 1
            }

            viewModelBusca.getAllComicsBusca(etBBuscaHQ.text.toString())

//kmzksm

        }

    }

    companion object {
        fun newInstance() = BuscaComicsFragment()
    }



    override fun bHQClick(position: Int) {
        adapterCo.notifyItemChanged(position)
        if (busca != 0){
            viewModelBusca.retornoAllComicsBusca.observe(this){
                cf.callDetalhesHQCards(it.data.results[position].id)
            }
        }else{
            viewModelBusca.retornoAllComics.observe(this){
                cf.callDetalhesHQCards(it.data.results[position].id)
            }
        }

    }



}