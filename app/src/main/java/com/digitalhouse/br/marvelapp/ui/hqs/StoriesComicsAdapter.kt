package com.digitalhouse.br.marvelapp.ui.hqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.stories.ResultsSt
import com.squareup.picasso.Picasso

class StoriesComicsAdapter(
    var listaStories: ArrayList<ResultsSt>,
    val listener: OnStoriesComicsClickListener
) : RecyclerView.Adapter<StoriesComicsAdapter.StoriesComicsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoriesComicsViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stories, parent, false)
        return StoriesComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StoriesComicsViewHolder, position: Int) {
        var story = listaStories.get(position)

        holder.tvNomeStories.text = story.title
        holder.tvDescricaoStories.text = story.description

        var img = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
        Picasso.get().load(img).resize(115, 100).into(holder.ivImagemStories)
    }

    override fun getItemCount() = listaStories.size

    interface OnStoriesComicsClickListener {
        fun storiesComicsClick(position: Int)
    }

    inner class StoriesComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val ivImagemStories: ImageView = itemView.findViewById(R.id.cvImagemHistoria)
        val tvNomeStories: TextView = itemView.findViewById(R.id.cvNomeHistoria)
        val tvDescricaoStories: TextView = itemView.findViewById(R.id.cvDescricaoHistoria)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.storiesComicsClick(position)
        }
    }
}
