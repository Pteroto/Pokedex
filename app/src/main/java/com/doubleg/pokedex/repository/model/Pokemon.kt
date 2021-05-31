package com.doubleg.pokedex.repository.model

import com.squareup.moshi.Json

data class Pokemon(
    val name: String,
    val id: Int,
    @field:Json(name = "sprites") val images: Sprites
)
