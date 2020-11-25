package com.digitalhouse.br.marvelapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_perfil_ranking.view.*
import kotlinx.android.synthetic.main.card_selo.view.*

class UserAdapter (private val listaUsers: ArrayList<User>):
                   RecyclerView.Adapter<UserAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate((R.layout.card_perfil_ranking), parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = listaUsers[position]

        holder.tvNomePerfil.text = currentItem.username
        holder.tvColocacao.text = currentItem.colocao
        holder.tvPontuacaoPerfil.text = currentItem.pontuacao

        if(position == 5){
            holder.ivComartilhar.setImageResource(R.drawable.ic_share_vermelho)
        }
    }

    override fun getItemCount() = listaUsers.size


    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvColocacao: TextView = itemView.tvColocacao
        val tvNomePerfil: TextView = itemView.tvNomePerfil
        val tvPontuacaoPerfil: TextView = itemView.tvPontuacaoPerfil
        val ivComartilhar: ImageView = itemView.ivCompartilhar
    }
}