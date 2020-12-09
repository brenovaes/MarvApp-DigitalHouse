package com.digitalhouse.br.marvelapp.ui.quiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.digitalhouse.br.marvelapp.ui.quiz.DesafiosFragment
import com.digitalhouse.br.marvelapp.ui.quiz.RankingFragment
import com.digitalhouse.br.marvelapp.ui.quiz.SelosFragment

class ViewPagerAdapter(fm: FragmentManager, private var tabCout: Int):
                       FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return tabCout
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DesafiosFragment()
            1 -> return RankingFragment()
            2 -> return SelosFragment()
            else -> return DesafiosFragment() //por default sera sempre desafios
        }
    }

}