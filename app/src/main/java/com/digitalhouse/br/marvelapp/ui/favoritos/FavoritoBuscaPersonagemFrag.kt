package com.digitalhouse.br.marvelapp.ui.favoritos

import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.crFCh
import com.digitalhouse.br.marvelapp.crFCo
import com.digitalhouse.br.marvelapp.crFCr
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheCardsFragments
import com.digitalhouse.br.marvelapp.interfac.ContractDetalheFav
import com.digitalhouse.br.marvelapp.ui.busca.BHQAdapter
import com.digitalhouse.br.marvelapp.ui.personagens.DetalhePersonagemActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_fav_busca_personagem.*

class FavoritoBuscaPersonagemFrag : Fragment(), FavoritoAdapter.OnFavoritoPersonagemClickListener {

    val viewModelFavorito by viewModels<FavoritoViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FavoritoViewModel(
                        crFCo,
                        crFCh,
                        crFCr
                ) as T
            }
        }
    }

    private lateinit var cf: ContractDetalheFav
    lateinit var adapterF: FavoritoAdapter

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


        viewModelFavorito.getFavCh(FirebaseAuth.getInstance().currentUser!!.uid)
            viewModelFavorito.resListFavCh.observe(viewLifecycleOwner){
                adapterF = FavoritoAdapter(it, this@FavoritoBuscaPersonagemFrag)
                rvFavoritoPers.adapter = adapterF
                qtdFavoritosPersonagem.text = it.size.toString()
            }

    }
    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractDetalheFav) cf = context
    }


    companion object {

        fun newInstance() =  FavoritoBuscaPersonagemFrag()
    }


    override fun fPersonagemClick(position: Int, id:Int) {
       adapterF.notifyItemChanged(position)
        cf.callDetalhesPCardsFav(id)
    }
}