package com.doubleg.pokedex.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()

        var search = findViewById<Button>(R.id.search)

        search.setOnClickListener {
            val limit = findViewById<EditText>(R.id.limit).text.toString().toInt()
            var offset = findViewById<EditText>(R.id.offset).text.toString().toInt()

            //https://developer.android.com/kotlin/coroutines
            //Criando Thread em background
            GlobalScope.launch(Dispatchers.IO) {
                val list = repository.getPokemonList(limit, offset)

                //Voltando para a MainThread
                withContext(Dispatchers.Main) {
                    setListOnScreen(list)
                }
            }

            Log.d("teste", "teste")

        }


    }

    private fun setListOnScreen(list: List<Pokemon>) {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(list) {
                Log.d("teste", it.name)
            }
        }
    }
}