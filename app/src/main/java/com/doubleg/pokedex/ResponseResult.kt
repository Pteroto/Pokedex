package com.doubleg.pokedex

interface ResponseResult {
    fun onSucess(pokemon: Pokemon)
    fun onError(erro: String)
    fun notFound(msg: String)
}