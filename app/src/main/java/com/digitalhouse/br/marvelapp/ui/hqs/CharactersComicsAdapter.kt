package com.digitalhouse.br.marvelapp.ui.hqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.entities.characters.ResultsCh
import com.digitalhouse.br.marvelapp.entities.creators.ResultsCr
import com.squareup.picasso.Picasso

class CharactersComicsAdapter (var listaCreators: ArrayList<ResultsCh>, val listener: OnCharactersComicsClickListener): RecyclerView.Adapter<CharactersComicsAdapter.CharactersComicsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersComicsViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)


        return CharactersComicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharactersComicsViewHolder, position: Int) {
        var character = listaCreators[position]

        holder.tvNomeCharacter.text = character.name

        var img = character.thumbnail.path + "." + character.thumbnail.extension
        Picasso.get().load(img).fit().into(holder.ivImagemCharacter)

    }

    override fun getItemCount() = listaCreators.size

    interface OnCharactersComicsClickListener{
        fun charactersComicsClick (position: Int)
    }

    inner class CharactersComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val ivImagemCharacter: ImageView = itemView.findViewById(R.id.cvImagemPersonagem)
        val tvNomeCharacter: TextView = itemView.findViewById(R.id.cvNomePersonagem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.charactersComicsClick(position)
        }
    }
}