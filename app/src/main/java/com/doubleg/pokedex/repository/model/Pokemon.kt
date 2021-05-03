package com.doubleg.pokedex.repository.model

data class Pokemon(
    val name: String,
    val id: Int,
    val sprites: Sprites,
    val abilities: List<Abilities>,
    val held_items: List<Held_Items>
    )
