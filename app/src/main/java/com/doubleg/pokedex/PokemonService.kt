package com.doubleg.pokedex

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
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") startNumber: Int
    ): Call<PokemonResponse>
}