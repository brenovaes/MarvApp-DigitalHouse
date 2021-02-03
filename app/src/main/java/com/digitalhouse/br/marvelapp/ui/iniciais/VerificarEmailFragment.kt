package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractRedefinirSenha
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VerificarEmailFragment : Fragment() {

    private  lateinit  var crs : ContractRedefinirSenha

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_verificar_email, container, false)

        crs.callLoginAct()
        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractRedefinirSenha) crs = context
    }


    companion object {

        fun newInstance() = VerificarEmailFragment()
    }
}


