package com.digitalhouse.br.marvelapp.ui.hqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.series.ResultsSe

import com.squareup.picasso.Picasso

class SeriesComicsAdapter(var listaSeries: ArrayList<ResultsSe>, val listener: OnSeriesComicsClickListener): RecyclerView.Adapter<SeriesComicsAdapter.SeriesComicsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesComicsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesComicsViewHolder, position: Int) {
        var serie = listaSeries.get(position)

        if (serie.title != null) {
            holder.tvNomeSerie.text = serie.title
        }
        holder.tvDataSerie.text = serie.startYear.toString()

        var img = serie.thumbnail.path + "." + serie.thumbnail.extension
        Picasso.get().load(img).resize(66,100).into(holder.ivImagemSerie)
    }

    override fun getItemCount() = listaSeries.size

    interface OnSeriesComicsClickListener{
        fun seriesComicsClick (position: Int)
    }

    inner class SeriesComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemSerie: ImageView = itemView.findViewById(R.id.cvImagemSerie)
        val tvNomeSerie: TextView = itemView.findViewById(R.id.cvNomeSerie)
        val tvDataSerie: TextView = itemView.findViewById(R.id.cvDataSerie)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.seriesComicsClick(position)
        }
    }
}