package com.doubleg.pokedex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        val service: PokemonService = retrofit.create(PokemonService::class.java)

        service.getPokemon("bulbasaur").enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    val name = response.body()?.name
                    Log.d("TESTE", name ?: "")
                    val id = response.body()?.id
                    Log.d("TESTE", id.toString())
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("TESTE", t.message ?: "")
            }
        })
    }
}