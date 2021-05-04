package com.doubleg.pokedex.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.doubleg.pokedex.ResponsePokemon
import com.doubleg.pokedex.PokemonService
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.ApiBuilder
import com.doubleg.pokedex.repository.PokemonResponse
import javax.security.auth.callback.Callback


class MainActivity : AppCompatActivity() {
    val service: PokemonService

    init {
        val apiBuilder = ApiBuilder()
        val retrofit = apiBuilder.createRetrofit()
        service = apiBuilder.createPokemonApi(retrofit)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        service.gePokemonUrlList(100,151).enqueue(object : Callback<PokemonResponse>)
    }
}


