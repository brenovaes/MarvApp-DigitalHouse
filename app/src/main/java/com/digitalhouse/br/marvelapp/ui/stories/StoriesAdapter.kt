package com.digitalhouse.br.marvelapp.ui.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.comics.Items
import com.digitalhouse.br.marvelapp.models.Stories

class StoriesAdapter (var listaStories: ArrayList<Items?>, val listener: OnStoriesClickListener): RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoriesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_stories, parent, false)
        return StoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        var serie = listaStories.get(position)
//        holder.ivImagemStories.setImageResource(serie.imagemStories)
//        holder.tvNomeStories.text = serie.nomeStories
//        holder.tvDescricaoStories.text = serie.descricaoStorie
    }

    override fun getItemCount() = listaStories.size

    interface OnStoriesClickListener{
        fun storiesClick (position: Int)
    }

    inner class StoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemStories: ImageView = itemView.findViewById(R.id.cvImagemHistoria)
        val tvNomeStories: TextView = itemView.findViewById(R.id.cvNomeHistoria)
        val tvDescricaoStories: TextView = itemView.findViewById(R.id.cvDescricaoHistoria)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.storiesClick(position)
        }
    }
}