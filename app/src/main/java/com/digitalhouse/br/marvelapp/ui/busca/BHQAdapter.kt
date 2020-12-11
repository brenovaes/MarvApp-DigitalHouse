package com.digitalhouse.br.marvelapp.ui.busca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.squareup.picasso.Picasso

class BHQAdapter(var listHQ: ArrayList<ResultsCo>, val listener: OnBHQClickListener): RecyclerView.Adapter<BHQAdapter.BHQViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BHQViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return BHQViewHolder(itemView)
    }


    override fun getItemCount() = listHQ.size

    override fun onBindViewHolder(holder: BHQViewHolder, position: Int) {
        var comic = listHQ.get(position)
        holder.nomeCard.text = comic.title

        var img = comic.thumbnail.path + "." + comic.thumbnail.extension
        Picasso.get().load(img).resize(115,100).into(holder.imgCard)
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