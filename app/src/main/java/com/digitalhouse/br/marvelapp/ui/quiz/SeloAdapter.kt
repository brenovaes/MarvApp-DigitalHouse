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

class SeloAdapter(private val listaSelo: ArrayList<Selo>):
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


    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ivSelo: ImageView = itemView.ivSelo
        val tvNomeSelo: TextView = itemView.tvNomeSelo
    }
}