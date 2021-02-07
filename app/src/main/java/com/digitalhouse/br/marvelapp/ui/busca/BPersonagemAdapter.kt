package com.digitalhouse.br.marvelapp.ui.busca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.squareup.picasso.Picasso

class BPersonagemAdapter(var listPersonagens: ArrayList<ResultsCh>, val listener: OnBPersonagemClickListener): RecyclerView.Adapter<BPersonagemAdapter.BPersonagemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BPersonagemViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return BPersonagemViewHolder(itemView)
    }


    override fun getItemCount() = listPersonagens.size

    override fun onBindViewHolder(holder: BPersonagemViewHolder, position: Int) {
        var personagens = listPersonagens.get(position)
        holder.typeFunction.text = "Character"
        holder.nomeCard.text = personagens.name


        var img = personagens.thumbnail.path + "." + personagens.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.imgCard)
    }

    interface OnBPersonagemClickListener{
        fun bPersonagemClick(position: Int)
    }

    inner class BPersonagemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
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
                listener.bPersonagemClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)

        }

    }

}