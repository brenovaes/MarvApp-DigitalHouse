package com.digitalhouse.br.marvelapp.ui.criadores

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.ResultsCo
import com.digitalhouse.br.marvelapp.entities.series.ResultsSe
import com.digitalhouse.br.marvelapp.ui.hqs.SeriesComicsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics.view.*

class ComicsCreatorsAdapter (var listaComics: ArrayList<ResultsCo>, val listener: OnComicsCreatorsClickListener): RecyclerView.Adapter<ComicsCreatorsAdapter.ComicsCreatorsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsCreatorsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_comics, parent, false)
        return ComicsCreatorsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComicsCreatorsViewHolder, position: Int) {
        var comic = listaComics.get(position)

        holder.tvNomeComic.text = comic.title
        //holder.tvDataComic.text = comic.dates[0]?.date

        holder.tvDataComic.text = "Sale date: " + comic.dates[0]?.date.split("T")[0]
        holder.tvCriadorComic.text = "Creator(s): " + comic.creators.items[0]?.name

        var img = comic.thumbnail.path + "." + comic.thumbnail.extension

        Log.i("TAG", img.toString())

        Picasso.get().load(img).resize(115,100).into(holder.ivImagemComic)

    }

    override fun getItemCount() = listaComics.size

    interface OnComicsCreatorsClickListener{
        fun comicsCreatorsClick (position: Int)
    }

    inner class ComicsCreatorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemComic: ImageView = itemView.cvImagemComic
        val tvNomeComic: TextView = itemView.cvNomeComic
        val tvDataComic: TextView = itemView.cvDataComic
        val tvCriadorComic: TextView = itemView.cvCriadorComic

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.comicsCreatorsClick(position)
        }
    }
}