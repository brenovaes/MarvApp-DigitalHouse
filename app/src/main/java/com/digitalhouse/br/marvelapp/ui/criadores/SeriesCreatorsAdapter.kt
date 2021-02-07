package com.digitalhouse.br.marvelapp.ui.criadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.series.ResultsSe
import com.digitalhouse.br.marvelapp.ui.hqs.SeriesComicsAdapter
import com.squareup.picasso.Picasso

class SeriesCreatorsAdapter (var listaSeries: ArrayList<ResultsSe>, val listener: OnSeriesCreatorsClickListener): RecyclerView.Adapter<SeriesCreatorsAdapter.SeriesCreatorsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeriesCreatorsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
        return SeriesCreatorsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesCreatorsViewHolder, position: Int) {
        var serie = listaSeries.get(position)

        if (serie.title != null) {
            holder.tvNomeSerie.text = serie.title
        }
        holder.tvDataInicioSerie.text = serie.startYear.toString()
        holder.tvDataFinalSerie.text = serie.endYear.toString()

        var img = serie.thumbnail.path + "." + serie.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.ivImagemSerie)
    }

    override fun getItemCount() = listaSeries.size

    interface OnSeriesCreatorsClickListener{
        fun seriesCreatorsClick (position: Int)
    }

    inner class SeriesCreatorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
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
                listener.seriesCreatorsClick(position)
        }
    }
}