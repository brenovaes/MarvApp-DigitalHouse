package com.digitalhouse.br.marvelapp.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_selo.view.*

class SeloAdapter(private val listaSelo: ArrayList<Selo>, val listener:OnSeloClickListener):
                 RecyclerView.Adapter<SeloAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate((R.layout.card_selo), parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = listaSelo[position]
//        Picasso.get().load(currentItem.imageResource).into(holder.ivSelo)
        Picasso.get().load(currentItem.imageResource).fit().into(holder.ivSelo)
//        holder.ivSelo.setImageResource(currentItem.imageResource)
        holder.tvNomeSelo.text = currentItem.nomeSelo
    }

    override fun getItemCount() = listaSelo.size

    interface OnSeloClickListener{
        fun seloClick(position: Int)
    }


    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivSelo: ImageView = itemView.ivSelo
        val tvNomeSelo: TextView = itemView.tvNomeSelo

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.seloClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)
        }
    }
}