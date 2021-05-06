package com.doubleg.pokedex.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.model.Pokemon

@GlideModule
class PokemonInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info)

        val pkinfo = intent.extras?.getParcelable<Pokemon>("pkminfo")

        var pkname = findViewById<TextView>(R.id.Name).text?.toString()
        val pkimage = findViewById<ImageView>(R.id.pokemonImage)

        if (pkinfo != null){
            pkname = pkinfo.name
            Glide.with(pkimage).load(pkinfo.sprites?.front_default).into(pkimage)
        }
    }
}