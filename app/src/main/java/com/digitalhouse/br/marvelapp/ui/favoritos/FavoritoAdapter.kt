package com.digitalhouse.br.marvelapp.ui.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.UserFav
import com.squareup.picasso.Picasso

class FavoritoAdapter (var listFav: ArrayList<UserFav?>, val listener: OnFavoritoPersonagemClickListener): RecyclerView.Adapter<FavoritoAdapter.FavoritoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_modelo, parent,false)
        return FavoritoViewHolder(itemView)
    }

    override fun getItemCount() = listFav.size

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        var favorito = listFav.get(position)
        var img = favorito!!.fav.path + "." + favorito.fav.extension
        Picasso.get().load(img).fit().into(holder.imgCard)
        holder.nomeCard.text = favorito.fav.name
        holder.typeFunction.text = favorito.fav.type
    }

    interface OnFavoritoPersonagemClickListener{
        fun fPersonagemClick(position: Int, id:Int)
    }

    inner class FavoritoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imgCard: ImageView = itemView.findViewById(R.id.ivCard)
        val nomeCard: TextView = itemView.findViewById(R.id.tvNomeCard)
        val typeFunction: TextView = itemView.findViewById(R.id.tvTipoFuncao)

        //falta a parte das estrelas

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val id = listFav.get(position)?.id
            if (RecyclerView.NO_POSITION != position)
                listener.fPersonagemClick(position, id!!)
            //redirecionar para o detalhe do card (personagem, criador ou HQ)
        }
    }
}