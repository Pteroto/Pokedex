package com.doubleg.pokedex.repository

import android.view.View
import android.widget.EditText
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.repository.model.ResponseUrl

class Repository {

    private val service: PokemonService

    init {
        val apiBuilder = ApiBuilder()
        val retrofit = apiBuilder.createRetrofit()
        service = apiBuilder.createPokemonApi(retrofit)
    }

    fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        val responseUrlList = getPokemonUrlList(limit, offset)

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

    private fun getPokemonUrlList(limit: Int,offset: Int): List<ResponseUrl> {
        return try {
            val response = service.getPokemonUrlList(limit,offset).execute()
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