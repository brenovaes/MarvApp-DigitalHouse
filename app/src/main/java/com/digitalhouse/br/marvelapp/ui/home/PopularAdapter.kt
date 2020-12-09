package com.digitalhouse.br.marvelapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.EntesMarvel


class PopularAdapter(var listPopulares: ArrayList<EntesMarvel>, val listener: OnPopularClickListener): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
            var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
            return PopularViewHolder(itemView)
        }


        override fun getItemCount() = listPopulares.size

        override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
            var popular = listPopulares.get(position)
            holder.imgCard.setImageResource(popular.img)
            holder.nomeCard.text = popular.nome
        }

        interface OnPopularClickListener{
            fun popularClick(position: Int)
        }

        inner class PopularViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
            val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
            val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
            //falta a parte das estrelas

            init {
                itemView.setOnClickListener(this)
            }


            override fun onClick(v: View?) {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position)
                    listener.popularClick(position)
                //redirecionar para o detalhe do card (personagem, criador ou HQ)

            }

        }

}