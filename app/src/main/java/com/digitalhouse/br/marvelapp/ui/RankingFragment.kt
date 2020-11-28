package com.digitalhouse.br.marvelapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_ranking.*
import kotlinx.android.synthetic.main.fragment_selos.*

class RankingFragment : Fragment() {
    private var listaUsers = getListUsers()
    private var adapter = UserAdapter(listaUsers)

    private fun getListUsers(): ArrayList<User> {
        val user0 = User(0, "Zoë Saldaña", "zoe@gmail.com", "abc0")
        val user1 = User(1, "Andrew Garfield", "andrew@gmail.com", "abc1")
        val user2 = User(2, "Tobey Maguire", "tobey@gmail.com", "abc2")
        val user3 = User(3, "Karen Gillan", "karen@gmail.com", "abc3")
        val user4 = User(4, "Robert Downey Jr.", "robert@gmail.com", "abc4")
        val user5 = User(5, "Mark Ruffalo", "mark@gmail.com", "abc5")

        user0.colocao = "1º"
        user0.pontuacao = "1000 pts"
        user1.colocao = "2º"
        user1.pontuacao = "950 pts"
        user2.colocao = "3º"
        user2.pontuacao = "900 pts"
        user3.colocao = "4º"
        user3.pontuacao = "875 pts"
        user4.colocao = "5º"
        user4.pontuacao = "800 pts"
        user5.colocao = "6º"
        user5.pontuacao = "700 pts"

        val listaUsers: ArrayList<User> = arrayListOf()
        listaUsers.add(user0)
        listaUsers.add(user1)
        listaUsers.add(user2)
        listaUsers.add(user3)
        listaUsers.add(user4)
        listaUsers.add(user5)

        return listaUsers
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvRanking.adapter = adapter
        rvRanking.layoutManager = LinearLayoutManager(activity)
        rvRanking.setHasFixedSize(true)
    }

    companion object {
        fun newInstance() = RankingFragment()
    }
}