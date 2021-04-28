package com.doubleg.pokedex

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var listPokemon: List<PokemonUrl>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: PokemonService = retrofit.create(PokemonService::class.java)

        service.getPokemonList(100, 151).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
//                    val body = response.body()
//                    if(body != null) {
//                        listPokemon = body.results
//                    }

                    response.body()?.let {
                        listPokemon = it.results
                        setListOnScreen()
                    }
                }
            }

            override fun onFailure(call: Call<com.doubleg.pokedex.PokemonResponse>, t: Throwable) {
                Log.d("teste", "teste")
            }
        })
    }

    fun setListOnScreen() {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(listPokemon) {
                Log.d("teste", it.name)
            }
        }
    }
}