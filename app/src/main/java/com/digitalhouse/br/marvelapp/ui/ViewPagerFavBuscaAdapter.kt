package com.digitalhouse.br.marvelapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerFavBuscaAdapter(fm: FragmentManager, private var tabCout: Int):
                       FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return tabCout
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FavoritoBuscaPersonagemFrag()
            1 -> return FavoritoBuscaHqFrag()
            2 -> return FavoritoBuscaCriadorFrag()
            else -> return FavoritoBuscaPersonagemFrag()
        }
    }

}