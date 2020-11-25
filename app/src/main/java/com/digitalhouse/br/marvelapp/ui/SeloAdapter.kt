package com.digitalhouse.br.marvelapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_selo.view.*

class SeloAdapter(private val listaSelo: ArrayList<Selo>):
                 RecyclerView.Adapter<SeloAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate((R.layout.card_selo), parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = listaSelo[position]

        holder.ivSelo.setImageResource(currentItem.imageResource)
        holder.tvNomeSelo.text = currentItem.nomeSelo
    }

    override fun getItemCount() = listaSelo.size


    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivSelo: ImageView = itemView.ivSelo
        val tvNomeSelo: TextView = itemView.tvNomeSelo
    }
}