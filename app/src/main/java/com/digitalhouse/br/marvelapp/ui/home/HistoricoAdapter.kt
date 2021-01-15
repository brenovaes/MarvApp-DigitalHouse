package com.digitalhouse.br.marvelapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.HistoryDB


class HistoricoAdapter(var listHistorico: ArrayList<HistoryDB>, val listener: OnHistoricoClickListener): RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return HistoricoViewHolder(itemView)
    }


    override fun getItemCount() = listHistorico.size

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        var historico = listHistorico.get(position)
//        holder.imgCard.setImageResource(historico.extension[0].toString())
        holder.nomeCard.text = historico.nome
    }

    interface OnHistoricoClickListener{
        fun historicoClick(position: Int)
    }

    inner class HistoricoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.historicoClick(position)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)

        }

    }

}
