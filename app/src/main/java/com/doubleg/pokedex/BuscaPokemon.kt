package com.doubleg.pokedex

import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.String as String1

class BuscaPokemon {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: PokemonService = retrofit.create(PokemonService::class.java)



    fun buscaNome(chaveBusca: kotlin.String, responseResult: ResponseResult ) {


        var name: String1
        name = "ab"

        service.run {
            name = "ab"

            getPokemon(chaveBusca).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        //retornar uma string pro nomePokemon.text


                        name = (response.body()?.name.toString())
                        responseResult.onResponse(name)


                        Log.d("ruan", name)
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
                        name = "Pokemon Not Found"
                        //                    nomePokemon.text = getString(R.string.not_found_pokemon)
                        //                    idPokemon.text = getString(R.string.not_found_pokemon)
                        //                    ide = 999
                    }

                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    name = "Busca deu Ruim"
                    //                nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
                    //                idPokemon.text = getString(R.string.search_error) + t.localizedMessage
                    //                ide = 9999
                }
            })


        }


    }


}