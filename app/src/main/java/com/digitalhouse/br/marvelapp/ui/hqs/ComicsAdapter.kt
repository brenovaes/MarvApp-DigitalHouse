package com.digitalhouse.br.marvelapp.ui.hqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.Items
import com.digitalhouse.br.marvelapp.entities.comics.ResComics
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.digitalhouse.br.marvelapp.models.Comics


class ComicsAdapter (var listaComics: ArrayList<Items?>, val listener: OnComicsClickListener, var name: String?): RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_comics, parent, false)
        return ComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        var comic = listaComics.get(position)
//        holder.ivImagemComic.setImageResource(comic.imagemComic)
        if (comic != null) {
            holder.tvNomeComic.text = comic.name
        }
//        holder.tvDataVendaComic.text = comic.dates[0].toString()
//        holder.tvCriador.text = name

    }

    override fun getItemCount() = listaComics.size



    interface OnComicsClickListener{
        fun comicsClick (position: Int)
    }

    inner class ComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemComic: ImageView = itemView.findViewById(R.id.cvImagemComic)
        val tvNomeComic: TextView = itemView.findViewById(R.id.cvNomeComic)
        val tvDataVendaComic: TextView = itemView.findViewById(R.id.cvDataComic)
        val tvCriador: TextView = itemView.findViewById(R.id.cvCriadorComic)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.comicsClick(position)
        }
    }
}