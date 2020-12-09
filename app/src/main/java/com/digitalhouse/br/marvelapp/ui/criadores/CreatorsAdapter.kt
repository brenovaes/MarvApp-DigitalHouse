package com.digitalhouse.br.marvelapp.ui.criadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.Creators

class CreatorsAdapter (var listaCreators: ArrayList<Creators>, val listener: OnCreatorsClickListener): RecyclerView.Adapter<CreatorsAdapter.CreatorsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatorsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_creators, parent, false)
        return CreatorsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatorsViewHolder, position: Int) {
        var criador = listaCreators.get(position)
        holder.ivImagemCreator.setImageResource(criador.imagemCriador)
        holder.tvNomeCreator.text = criador.nomeCriador
        holder.tvFuncaoCreator.text = criador.funcaoCriador

    }

    override fun getItemCount() = listaCreators.size

    interface OnCreatorsClickListener{
        fun creatorsClick (position: Int)
    }

    inner class CreatorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemCreator: ImageView = itemView.findViewById(R.id.cvImagemCreator)
        val tvNomeCreator: TextView = itemView.findViewById(R.id.cvNomeCriador)
        val tvFuncaoCreator: TextView = itemView.findViewById(R.id.cvFuncaoCriador)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.creatorsClick(position)
        }
    }
}