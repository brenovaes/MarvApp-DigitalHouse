package com.digitalhouse.br.marvelapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

class SeriesAdapter (var listaSeries: ArrayList<Series>, val listener: OnSeriesClickListener): RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        var serie = listaSeries.get(position)
        holder.ivImagemSerie.setImageResource(serie.imagemSerie)
        holder.tvNomeSerie.text = serie.nomeSerie
        holder.tvDataSerie.text = serie.dataSerie.toString()
    }

    override fun getItemCount() = listaSeries.size

    interface OnSeriesClickListener{
        fun seriesClick (position: Int)
    }

    inner class SeriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemSerie: ImageView = itemView.findViewById(R.id.cvImagemSerie)
        val tvNomeSerie: TextView = itemView.findViewById(R.id.cvNomeSerie)
        val tvDataSerie: TextView = itemView.findViewById(R.id.cvDataSerie)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.seriesClick(position)
        }
    }
}