package com.doubleg.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doubleg.pokedex.R
import com.doubleg.pokedex.domain.model.PokemonView

//https://developer.android.com/guide/topics/ui/layout/recyclerview
class PokemonAdapter(
    private val responseList: List<PokemonView>,
    private val onItemClick: (PokemonView) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return responseList.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(responseList[position])
    }

    inner class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonView) {
            val imageView = itemView.findViewById<ImageView>(R.id.pokemonImage)
            Glide.with(itemView).load(pokemon.image).into(imageView)

            itemView.findViewById<TextView>(R.id.pokemonName).text = pokemon.name

            itemView.setOnClickListener {
                onItemClick(pokemon)
            }
        }
    }
}