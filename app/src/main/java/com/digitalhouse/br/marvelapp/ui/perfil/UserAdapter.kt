package com.digitalhouse.br.marvelapp.ui.perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.Pontuacao
import kotlinx.android.synthetic.main.card_perfil_ranking.view.*

class UserAdapter (private val listaUsers: ArrayList<Pontuacao>, val listener:OnUserClickListener):
                   RecyclerView.Adapter<UserAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate((R.layout.card_perfil_ranking), parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = listaUsers[position]

        holder.tvNomePerfil.text = currentItem.username
        holder.tvColocacao.text = (position+1).toString()
        holder.tvPontuacaoPerfil.text = currentItem.pontos.toString()

//        if(position == 5){
//            holder.ivComartilhar.setImageResource(R.drawable.ic_share_red)
//        }
    }

    override fun getItemCount() = listaUsers.size


    interface OnUserClickListener{
        fun userClick(position: Int)
    }



    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvColocacao: TextView = itemView.tvColocacao
        val tvNomePerfil: TextView = itemView.tvNomePerfil
        val tvPontuacaoPerfil: TextView = itemView.tvPontuacaoPerfil
        val ivComartilhar: ImageView = itemView.ivCompartilhar



        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.userClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)
        }
    }

}