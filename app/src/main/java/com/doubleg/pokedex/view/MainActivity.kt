package com.doubleg.pokedex.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doubleg.pokedex.R
import com.doubleg.pokedex.domain.model.PokemonView
import com.doubleg.pokedex.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObservers()
        viewModel.getPokemonList()
    }

    private fun setListOnScreen(list: List<PokemonView>) {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(list) {
                Log.d("teste", it.name)
            }
        }
    }

    private fun setObservers() {
        //https://refactoring.guru/pt-br/design-patterns/observer
        viewModel.pokemonList.observe(this) {
            setListOnScreen(it)
        }
    }
}