package com.doubleg.pokedex.repository.model

data class Pokemon(
    val name: String,
    val id: Int,
    val sprites: Sprites,
    val abilities: Abilities
    )
