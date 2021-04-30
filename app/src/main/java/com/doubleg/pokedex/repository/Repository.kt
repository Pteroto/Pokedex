package com.doubleg.pokedex.repository

import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.repository.model.ResponseUrl

class Repository {

    private val service: PokemonService

    init {
        val apiBuilder = ApiBuilder()
        val retrofit = apiBuilder.createRetrofit()
        service = apiBuilder.createPokemonApi(retrofit)
    }

    fun getPokemonList(): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        val responseUrlList = getPokemonUrlList()

        responseUrlList.forEach { responseUrl ->
            val id =
                responseUrl.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
            val response = service.getPokemonById(id).execute()
            if (response.isSuccessful) {
                val pokemon = response.body()
                if (pokemon != null) {
                    pokemonList.add(pokemon)
                }
            }
        }

        return pokemonList
    }

    private fun getPokemonUrlList(): List<ResponseUrl> {
        return try {
            val response = service.getPokemonUrlList(50, 151).execute()
            if (response.isSuccessful) {
                response.body()?.results ?: listOf()
            } else {
                listOf()
            }
        } catch (exception: Exception) {
            listOf()
        }
    }
}