package com.digitalhouse.br.marvelapp.ui.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.Characters

class FavoritoAdapter (var listPersonagens: ArrayList<Characters>, val listener: OnFavoritoPersonagemClickListener): RecyclerView.Adapter<FavoritoAdapter.FPersonagemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FPersonagemViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return FPersonagemViewHolder(itemView)
    }

    override fun getItemCount() = listPersonagens.size

    override fun onBindViewHolder(holder: FPersonagemViewHolder, position: Int) {
        var personagens = listPersonagens.get(position)
//        holder.imgCard.setImageResource(personagens.imagemCharacter)
//        holder.nomeCard.text = personagens.nomeCharacter
    }

    interface OnFavoritoPersonagemClickListener{
        fun fPersonagemClick(position: Int)
    }

    inner class FPersonagemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.fPersonagemClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)
        }
    }
}