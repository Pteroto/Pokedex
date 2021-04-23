package com.doubleg.pokedex

import android.util.Log
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SearchPokemon() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: PokemonService = retrofit.create(PokemonService::class.java)

    fun pesquisa(pkname: String, pokemonresponse: PokemonResponse) {
        service.getPokemon(pkname).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    var pokemon = response.body()
                    if (pokemon != null) {
                        pokemonresponse.onSuccess(pokemon)
                    }
                }
                else{
                    pokemonresponse.onFailure()
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                pokemonresponse.onFailure()
                Log.d("TESTE", "erro: " + t.message ?: "")
            }
        }
        )
    }
}
