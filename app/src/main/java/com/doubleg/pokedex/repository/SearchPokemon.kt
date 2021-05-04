package com.doubleg.pokedex.repository

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.model.Pokemon
import com.doubleg.pokedex.view.PokemonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPokemon {

    val apiBuilder = ApiBuilder()
    val retrofit = apiBuilder.createRetrofit()
    val service = apiBuilder.createPokemonApi(retrofit)




}