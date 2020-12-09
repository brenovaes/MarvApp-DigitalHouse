package com.digitalhouse.br.marvelapp.ui.personagens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.br.marvelapp.R
import com.digitalhouse.br.marvelapp.models.Characters

class CharactersAdapter (var listaCharacters: ArrayList<Characters>, val listener: OnCharactersClickListener): RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        return CharactersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        var char = listaCharacters.get(position)
        holder.ivImagemCharacter.setImageResource(char.imagemCharacter)
        holder.tvNomeCharacter.text = char.nomeCharacter.toString()
    }

    override fun getItemCount() = listaCharacters.size

    interface OnCharactersClickListener{
        fun charactersClick (position: Int)
    }

    inner class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        val ivImagemCharacter: ImageView = itemView.findViewById(R.id.cvImagemPersonagem)
        val tvNomeCharacter: TextView = itemView.findViewById(R.id.cvNomePersonagem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (RecyclerView.NO_POSITION != position)
                listener.charactersClick(position)
        }
    }
}
