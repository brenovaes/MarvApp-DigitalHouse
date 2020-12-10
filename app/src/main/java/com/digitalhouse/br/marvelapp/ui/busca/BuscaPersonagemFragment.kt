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
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.service.serviceB
import kotlinx.android.synthetic.main.fragment_busca_personagem.*

class BuscaPersonagemFragment : Fragment(), BPersonagemAdapter.OnBPersonagemClickListener {

    var listPersonagens = arrayListOf<ResultsCh>()
    private lateinit var cf: ContractDetalheCardsFragments
    lateinit var adapterB: BPersonagemAdapter


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
        var view = inflater.inflate(R.layout.fragment_busca_personagem, container, false)


        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheCardsFragments) cf = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelBusca.retornoAllCharacters.observe(viewLifecycleOwner){
            listPersonagens.addAll(it.data.results)
            adapterB = BPersonagemAdapter(listPersonagens, this)
            rvBuscaP.adapter = adapterB
        }

        viewModelBusca.getAllCharactersBusca()
    }

    companion object {

        fun newInstance() =  BuscaPersonagemFragment()
    }


    override fun bPersonagemClick(position: Int) {
        adapterB.notifyItemChanged(position)
        viewModelBusca.retornoAllCharacters.observe(this) {
            cf.callDetalhesPCards(it.data.results[position].id)
        }
    }
}