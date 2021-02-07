package com.digitalhouse.br.marvelapp.ui.criadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.events.ResultsEv
import com.squareup.picasso.Picasso

class EventsCreatorsAdapter (var listaEvents: ArrayList<ResultsEv>, val listener: OnEventsCreatorsClickListener): RecyclerView.Adapter<EventsCreatorsAdapter.EventsCreatorsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsCreatorsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)
        return EventsCreatorsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventsCreatorsViewHolder, position: Int) {
        var evento = listaEvents.get(position)

        if (evento != null) {
            holder.tvNomeEvent.text = evento.title
        }

        holder.tvDataInicioEvent.text = "Start: "+ evento.start.split(" ")[0]
        holder.tvDataFimEvent.text = "End: " + evento.end.split(" ")[0]
        var img = evento.thumbnail.path + "." + evento.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.ivImagemEvent)

    }

    override fun getItemCount() = listaEvents.size

    interface OnEventsCreatorsClickListener{
        fun eventsCreatorsClick (position: Int)
    }

    inner class EventsCreatorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemEvent: ImageView = itemView.findViewById(R.id.cvImagemEvento)
        val tvNomeEvent: TextView = itemView.findViewById(R.id.cvNomeEvento)
        val tvDataInicioEvent: TextView = itemView.findViewById(R.id.cvDataInicioEvento)
        val tvDataFimEvent: TextView = itemView.findViewById(R.id.cvDataFinalEvento)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.eventsCreatorsClick(position)
        }
    }
}