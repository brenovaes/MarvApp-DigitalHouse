package com.digitalhouse.br.marvelapp.ui.busca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.squareup.picasso.Picasso

class BCriadoresAdapter(var listCriadores: ArrayList<ResultsCr>, val listener: OnBCriadoresClickListener): RecyclerView.Adapter<BCriadoresAdapter.BCriadoresViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BCriadoresViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return BCriadoresViewHolder(itemView)
    }


    override fun getItemCount() = listCriadores.size

    override fun onBindViewHolder(holder: BCriadoresViewHolder, position: Int) {
        var criador = listCriadores.get(position)
        holder.typeFunction.text = "Creator"
        holder.nomeCard.text = criador.fullName

        var img = criador.thumbnail.path + "." + criador.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.imgCard)
    }

    interface OnBCriadoresClickListener{
        fun bCriadoresClick(position: Int)
    }

    inner class BCriadoresViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        val typeFunction: TextView = itemView.findViewById(R.id.tvTipoFuncao)
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