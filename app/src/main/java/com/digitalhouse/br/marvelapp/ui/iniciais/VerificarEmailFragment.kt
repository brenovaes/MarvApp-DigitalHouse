package com.digitalhouse.br.marvelapp.ui.iniciais

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.digitalhouse.br.marvelapp.R


class VerificarEmailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_verificar_email, container, false)
    }


    companion object {

        fun newInstance() = VerificarEmailFragment()
    }
}


