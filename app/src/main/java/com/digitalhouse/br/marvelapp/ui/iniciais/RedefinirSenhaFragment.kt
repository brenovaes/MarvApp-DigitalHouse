package com.digitalhouse.br.marvelapp.ui.iniciais

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.interfac.ContractRedefinirSenha
import kotlinx.android.synthetic.main.fragment_redefinir_senha.view.*


class RedefinirSenhaFragment : Fragment() {

    private  lateinit  var crs : ContractRedefinirSenha
//    val fragVerificarE = VerificarEmailFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater!!.inflate(R.layout.fragment_redefinir_senha, container, false)
        view.btnEnviar.setOnClickListener(){
            crs.callFragVerificarEmail()
        }
        return view
    }

    override  fun  onAttach ( context : Context) {
        super .onAttach (context)
        if (context is ContractRedefinirSenha) crs = context
    }


    companion object {

        fun newInstance() = RedefinirSenhaFragment()
    }


}