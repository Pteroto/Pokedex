package com.doubleg.pokedex.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.Repository
import com.doubleg.pokedex.repository.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowPokemon : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_pokemon)

        val limit = intent.extras?.getInt("limit")
        val offset = intent.extras?.getInt("offset")

        val repository = Repository()

        if (limit != null && offset != null) {
            //https://developer.android.com/kotlin/coroutines
            //Criando Thread em background
            GlobalScope.launch(Dispatchers.IO) {
                val list = repository.getPokemonList(limit, offset)

                //Voltando para a MainThread
                withContext(Dispatchers.Main) {
                    setListOnScreen(list)
                }
            }


        }
        Log.d("teste", "teste")


    }

    private fun setListOnScreen(list: List<Pokemon>) {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager = GridLayoutManager(this@ShowPokemon, 3)
            //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(list) {
                Log.d("teste", it.name)
            }
        }
    }
}