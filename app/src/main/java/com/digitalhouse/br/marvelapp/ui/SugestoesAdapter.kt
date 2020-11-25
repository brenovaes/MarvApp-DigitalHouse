package com.digitalhouse.br.marvelapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

class SugestoesAdapter(var listSugestoes: ArrayList<Characters>, val listener: OnSugestoesClickListener): RecyclerView.Adapter<SugestoesAdapter.SugestoesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):SugestoesAdapter.SugestoesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return SugestoesViewHolder(itemView)
    }


    override fun getItemCount() = listSugestoes.size

    override fun onBindViewHolder(holder: SugestoesAdapter.SugestoesViewHolder, position: Int) {
        var sugestoes = listSugestoes.get(position)
        holder.imgCard.setImageResource(sugestoes.imagemCharacter)
        holder.nomeCard.text = sugestoes.nomeCharacter
    }

    interface OnSugestoesClickListener{
        fun sugestoesClick(position: Int)
    }

    inner class SugestoesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.sugestoesClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)

        }

    }

}
