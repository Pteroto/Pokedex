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

    var cont: Int = 1


    val service: PokemonService = retrofit.create(PokemonService::class.java)


    fun montaLista(responseResult: ResponseResult) {


//        service.run {
//
//            getPokemon(1.toString()).enqueue(object : Callback<Pokemon> {
//                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
//                    if (response.isSuccessful && response.body() != null) {
//                        response.body().let {
//                            if (it != null) {
//                                responseResult.onSucess(it)
//                            } else {
//                                responseResult.notFound("Pokemon Não Encontrado")
//                            }
//                        }
//
//                    } else {
//                        responseResult.notFound("Pokemon Not Found")
//                    }
//                }
//
//                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                    responseResult.onError("Deu Ruim " + t.localizedMessage)
//
//                }
//
//
//            })
//
//            cont = cont + 1
//
//
//        }
    }


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

                    } else {
                        responseResult.notFound("Pokemon Not Found")
                    }

                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    responseResult.onError("Deu Ruim " + t.localizedMessage)
                }
            })
        }
    }
}