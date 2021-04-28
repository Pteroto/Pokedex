package com.doubleg.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//https://developer.android.com/guide/topics/ui/layout/recyclerview
class PokemonAdapter(
    private val pokemonList: List<PokemonUrl>,
    private val onItemClick: (PokemonUrl) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: PokemonUrl) {
            itemView.findViewById<TextView>(R.id.name).text = pokemon.name
            itemView.findViewById<TextView>(R.id.url).text = pokemon.url
            itemView.setOnClickListener {
                onItemClick(pokemon)
            }
        }
    }
}