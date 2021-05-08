package com.doubleg.pokedex.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.model.Pokemon

class PokemonInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_info)

        val pkinfo = intent.extras?.getParcelable<Pokemon>("info")

        var pkname = findViewById<TextView>(R.id.Name)



        if (pkinfo != null){
            pkname.text = pkinfo.name

            val pkimage = findViewById<ImageView>(R.id.imageInfo)
            Glide.with(this).load(pkinfo.sprites?.front_default).into(pkimage)


        }



    }
}