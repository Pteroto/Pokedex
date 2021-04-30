package com.doubleg.pokedex.repository

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiBuilder {

    private val baseUrl = "https://pokeapi.co/api/v2/"

    fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun createPokemonApi(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)
}