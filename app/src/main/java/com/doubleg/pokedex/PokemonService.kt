package com.doubleg.pokedex

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{pokemon}")
    fun getPokemon(@Path("pokemon") pokemon: String): Call<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: String): Call<Pokemon>
}