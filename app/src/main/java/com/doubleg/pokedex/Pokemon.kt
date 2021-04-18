package com.doubleg.pokedex


data class Pokemon(
    val name: String,
    val id: Int,
    val abilities: List<Abilities>,
    val base_experience: String,
    val forms: List<ZeroForms>,
    val game_indices: List<ZeroGameIndicies>,
    val height: Int,
    val sprites: Sprites
)
