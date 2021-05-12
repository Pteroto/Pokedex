package com.doubleg.pokedex.repository

import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.repository.model.ResponseUrl
import com.doubleg.pokedex.repository.retrofit.ApiBuilder
import com.doubleg.pokedex.repository.retrofit.PokemonService

class Repository {

    private val retrofitService: PokemonService

    init {
        val apiBuilder = ApiBuilder()
        val retrofit = apiBuilder.createRetrofit()
        //roomService
        retrofitService = apiBuilder.createPokemonApi(retrofit)
    }

    fun getPokemonList(): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        val responseUrlList = getPokemonUrlList()

        responseUrlList.forEach { responseUrl ->
            val id =
                responseUrl.url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
            val response = retrofitService.getPokemonById(id).execute()
            if (response.isSuccessful) {
                val pokemon = response.body()
                if (pokemon != null) {
                    pokemonList.add(pokemon)
                }
            }
        }
        //room.saveList(pokemonList)
        return pokemonList
    }

    private fun getPokemonUrlList(): List<ResponseUrl> {
        return try {
            val response = retrofitService.getPokemonUrlList(50, 151).execute()
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