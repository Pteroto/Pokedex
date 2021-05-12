package com.doubleg.pokedex.repository.model

import retrofit2.http.Field

data class Pokemon(
    val name: String,
    val id: Int,
    @Field("sprites") val sprites: Sprites
)
