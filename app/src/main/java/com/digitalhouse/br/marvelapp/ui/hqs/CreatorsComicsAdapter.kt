package com.digitalhouse.br.marvelapp.ui.hqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.Items
import com.digitalhouse.br.marvelapp.entities.creators.ResCreators
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.service.serviceCo
import com.digitalhouse.br.marvelapp.ui.criadores.CreatorsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_hq.*

class CreatorsComicsAdapter (var listaCreators: ArrayList<ResultsCr>, val listener: OnCreatorsComicsClickListener, val funcao: ArrayList<Items?>): RecyclerView.Adapter<CreatorsComicsAdapter.CreatorsComicsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatorsComicsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_creators, parent, false)


        return CreatorsComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CreatorsComicsViewHolder, position: Int) {
        var criador = listaCreators[position]

        holder.tvNomeCreator.text = criador.fullName

        if (funcao.get(position)?.role != null){
            holder.tvFuncaoCreator.text = funcao[position]?.role
        }

        var img = criador.thumbnail.path + "." + criador.thumbnail.extension
        Picasso.get().load(img).resize(115,100).into(holder.ivImagemCreator)

    }

    override fun getItemCount() = listaCreators.size

    interface OnCreatorsComicsClickListener{
        fun creatorsComicsClick (position: Int)
    }

    inner class CreatorsComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemCreator: ImageView = itemView.findViewById(R.id.cvImagemCreator)
        val tvNomeCreator: TextView = itemView.findViewById(R.id.cvNomeCriador)
        val tvFuncaoCreator: TextView = itemView.findViewById(R.id.cvFuncaoCriador)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.creatorsComicsClick(position)
        }
    }
}