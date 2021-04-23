package com.doubleg.pokedex

import android.util.Log
import retrofit2.Call
import retrofit2.Response

interface PokemonResponse {
    fun onSuccess(pokemon: Pokemon)
    fun onFailure()
}
//adicionar onFailure e implementar na main a resposta em caso de erro
//rever enqueue
//callback
