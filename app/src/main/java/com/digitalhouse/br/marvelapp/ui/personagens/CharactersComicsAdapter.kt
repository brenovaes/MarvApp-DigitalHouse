package com.digitalhouse.br.marvelapp.ui.personagens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics.view.*

class CharactersComicsAdapter (var listaComics: ArrayList<ResultsCo>, val listener: OnCharactersComicsClickListener):
                               RecyclerView.Adapter<CharactersComicsAdapter.CharactersComicsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersComicsAdapter.CharactersComicsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_comics, parent, false)
        return CharactersComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharactersComicsAdapter.CharactersComicsViewHolder, position: Int) {
        var comic = listaComics[position]

        if(comic != null) {
            holder.tvNomeComic.text = comic.title
            //holder.tvDataComic.text = comic.dates[0]?.date
        }

        if (comic.dates.isNotEmpty()){
            holder.tvDataComic.text = "Sale date: " + comic.dates[0]?.date.split("T")[0]
        }

        if (comic.creators.items.isNotEmpty()){
            holder.tvCriadorComic.text = "Creator(s): " + comic.creators.items[0]?.name
        }

        var img = comic.thumbnail.path + "." + comic.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.ivImagemComic)

    }

    override fun getItemCount() = listaComics.size

    interface OnCharactersComicsClickListener {
        fun charactersComicsClick(position: Int)
    }

    inner class CharactersComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
         val ivImagemComic: ImageView = itemView.cvImagemComic
         val tvNomeComic: TextView = itemView.cvNomeComic
         val tvDataComic: TextView = itemView.cvDataComic
         val tvCriadorComic: TextView = itemView.cvCriadorComic

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var position = adapterPosition
            if(RecyclerView.NO_POSITION != position)
                listener.charactersComicsClick(position)
        }

    }

}