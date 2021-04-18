package com.doubleg.pokedex

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class BuscaPokemon {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: PokemonService = retrofit.create(PokemonService::class.java)


    fun buscaNome(chaveBusca: String, responseResult: ResponseResult) {

        service.run {

            getPokemon(chaveBusca).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body().let {
                            if (it != null) {
                                responseResult.onSucess(it)
                            } else {
                                responseResult.notFound("Pokemon Não Encontrado")
                            }
                        }
                        //
                        //                    idPokemon.text = (response.body()?.id).toString()
                        //                    ide = (response.body()?.id).toString().toInt()
                        //
                        //                    Toast.makeText(
                        //                        this@MainActivity,
                        //                        (response.body()?.abilities?.get(1)?.ability?.name),
                        //                        Toast.LENGTH_LONG
                        //                    ).show()
                        //
                        //                    url = (response.body()?.sprites?.front_default.toString())
                        //                    Glide.with(this@MainActivity)
                        //                        .load(url)
                        //                        .into(imageViewFront)
                        //
                        //                    url = (response.body()?.sprites?.back_default.toString())
                        //                    Glide.with(this@MainActivity)
                        //                        .load(url)
                        //                        .into(imageViewBack)

                    } else {

                        responseResult.notFound("Pokemon Not Found")
                        //                    nomePokemon.text = getString(R.string.not_found_pokemon)
                        //                    idPokemon.text = getString(R.string.not_found_pokemon)
                        //                    ide = 999
                    }

                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    responseResult.onError("Deu Ruim " + t.localizedMessage)
                    //                nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
                    //                idPokemon.text = getString(R.string.search_error) + t.localizedMessage
                    //                ide = 9999
                }
            })


        }


    }


}