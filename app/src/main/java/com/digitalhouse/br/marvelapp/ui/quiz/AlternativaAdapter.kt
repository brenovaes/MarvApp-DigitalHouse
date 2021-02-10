package com.digitalhouse.br.marvelapp.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import kotlinx.android.synthetic.main.card_alternativa.view.*


class AlternativaAdapter (private val listaAlternativa: ArrayList<Alternativa>,
                          private val listener: OnItemClickListener):
        RecyclerView.Adapter<AlternativaAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val tvAlternativa: TextView = itemView.tvAlternativa

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate((R.layout.card_alternativa), parent, false)
        return CardViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentItem = listaAlternativa[position]
        holder.tvAlternativa.text = listaAlternativa[position].opcaoResposta
    }

    override fun getItemCount() = listaAlternativa.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

