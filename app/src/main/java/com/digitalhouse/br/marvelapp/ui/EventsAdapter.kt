package com.digitalhouse.br.marvelapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R

class EventsAdapter (var listaEvents: ArrayList<Events>, val listener: OnEventsClickListener): RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)
        return EventsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        var evento = listaEvents.get(position)
        holder.ivImagemEvent.setImageResource(evento.imagemEvent)
        holder.tvNomeEvent.text = evento.nomeEvent
        holder.tvDataInicioEvent.text = evento.dataInicioEvent.toString()
        holder.tvDataFimEvent.text = evento.dataFimEvent.toString()

    }

    override fun getItemCount() = listaEvents.size

    interface OnEventsClickListener{
        fun eventsClick (position: Int)
    }

    inner class EventsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
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
                listener.eventsClick(position)
        }
    }
}