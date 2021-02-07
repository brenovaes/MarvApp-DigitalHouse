package com.digitalhouse.br.marvelapp.ui.series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

import com.digitalhouse.br.marvelapp.entities.creators.ItemsCr


class SeriesAdapter (var listaSeries: ArrayList<ItemsCr?>, val listener: OnSeriesClickListener): RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        var serie = listaSeries.get(position)
//        holder.ivImagemSerie.setImageResource(serie.imagemSerie)
//        if (serie != null) {
//            holder.tvNomeSerie.text = serie.name
//        }
//        holder.tvDataInicioSerie.text = serie.s.toString()
//        holder.tvDataFinalSerie.text = serie.endYear.toString()
    }

    override fun getItemCount() = listaSeries.size

    interface OnSeriesClickListener{
        fun seriesClick (position: Int)
    }

    inner class SeriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemSerie: ImageView = itemView.findViewById(R.id.cvImagemSerie)
        val tvNomeSerie: TextView = itemView.findViewById(R.id.cvNomeSerie)
        val tvDataInicioSerie: TextView = itemView.findViewById(R.id.cvDataInicioSerie)
        val tvDataFinalSerie: TextView = itemView.findViewById(R.id.cvDataFinalSerie)

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