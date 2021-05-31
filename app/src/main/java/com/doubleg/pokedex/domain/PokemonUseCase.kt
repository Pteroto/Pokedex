package com.doubleg.pokedex.domain

import com.doubleg.pokedex.domain.model.PokemonView
import com.doubleg.pokedex.repository.Repository

class PokemonUseCase(private val repository: Repository) {

    fun getPokemonList(): List<PokemonView> {
        val finalPokemonList = mutableListOf<PokemonView>()
        val pokemonList = repository.getPokemonList()

        pokemonList.forEach { pokemon ->
            val pokemonView = PokemonView(pokemon.name, pokemon.images.front_default)
            finalPokemonList.add(pokemonView)
        }

        return finalPokemonList
    }

    fun savePokemonList(list: List<PokemonView>) {
        //repository.savePokemonList(list)
    }
}