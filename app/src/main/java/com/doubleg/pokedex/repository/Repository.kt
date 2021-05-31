package com.doubleg.pokedex.repository

import com.doubleg.pokedex.repository.model.Pokemon

interface Repository {
    fun getPokemonList(): List<Pokemon>
}