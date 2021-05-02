package com.doubleg.pokedex.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doubleg.pokedex.R
import com.doubleg.pokedex.repository.Repository
import com.doubleg.pokedex.repository.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val params = intent.extras

        val offSet = params?.getString("offSet")?.toInt()

        val quantidade = params?.getString("quantidade")?.toInt()

        val mostrarOffset = findViewById<TextView>(R.id.TextViewMostrarOffSet)

        mostrarOffset.text = offSet.toString() + "-" + quantidade.toString()

        var inicio: Int = 0
        var fim: Int = 0








        val repository = Repository()

        //https://developer.android.com/kotlin/coroutines
        //Criando Thread em background
        GlobalScope.launch(Dispatchers.IO) {

            if (offSet != null) {
                inicio = offSet
            }

            if (quantidade != null) {
                fim = quantidade
            }


            val list = repository.getPokemonList(inicio, fim)

            //Voltando para a MainThread
            withContext(Dispatchers.Main) {
                setListOnScreen(list)
            }
        }

        Log.d("teste", "teste")



    }

    private fun setListOnScreen(list: List<Pokemon>) {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager = GridLayoutManager(this@NextActivity, 3)
            //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(list) {

            }
        }
    }
}