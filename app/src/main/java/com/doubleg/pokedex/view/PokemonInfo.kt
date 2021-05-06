package com.doubleg.pokedex.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.doubleg.pokedex.R

class PokemonInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info)

        val pkmnome = intent.extras?.get(pkmInfo)
    }
}