package com.digitalhouse.br.marvelapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

class BCriadoresAdapter(var listCriadores: ArrayList<Creators>, val listener: OnBCriadoresClickListener): RecyclerView.Adapter<BCriadoresAdapter.BCriadoresViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BCriadoresAdapter.BCriadoresViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return BCriadoresViewHolder(itemView)
    }


    override fun getItemCount() = listCriadores.size

    override fun onBindViewHolder(holder: BCriadoresAdapter.BCriadoresViewHolder, position: Int) {
        var criador = listCriadores.get(position)
        holder.imgCard.setImageResource(criador.imagemCriador)
        holder.nomeCard.text = criador.nomeCriador
    }

    interface OnBCriadoresClickListener{
        fun bCriadoresClick(position: Int)
    }

    inner class BCriadoresViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.bCriadoresClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)

        }

    }

}