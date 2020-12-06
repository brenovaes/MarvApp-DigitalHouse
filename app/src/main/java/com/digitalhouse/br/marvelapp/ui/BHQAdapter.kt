package com.digitalhouse.br.marvelapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

class BHQAdapter(var listHQ: ArrayList<Comics>, val listener: OnBHQClickListener): RecyclerView.Adapter<BHQAdapter.BHQViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BHQAdapter.BHQViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return BHQViewHolder(itemView)
    }


    override fun getItemCount() = listHQ.size

    override fun onBindViewHolder(holder: BHQAdapter.BHQViewHolder, position: Int) {
        var comic = listHQ.get(position)
        holder.imgCard.setImageResource(comic.imagemComic)
        holder.nomeCard.text = comic.nomeComic
    }

    interface OnBHQClickListener{
        fun bHQClick(position: Int)
    }

    inner class BHQViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.bHQClick(position)
                //redirecionar para o detalhe do card (personagem, criador ou HQ)

        }

    }

}