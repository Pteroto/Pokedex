package com.doubleg.pokedex.repository

import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.repository.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemon: String): Call<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Call<Pokemon>

    @GET("pokemon")
    fun getPokemonUrlList(
        @Query("limit") limit: Int,
        @Query("offset") startNumber: Int
    ): Call<PokemonResponse>
}