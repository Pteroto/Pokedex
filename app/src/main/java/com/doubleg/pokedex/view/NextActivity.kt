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

        val itens = params?.getBoolean("itens")
        val habilidade = params?.getBoolean("habilidade")

//        val mostrarOffset = findViewById<TextView>(R.id.TextViewMostrarOffSet)
//
//        mostrarOffset.text = offSet.toString() + "-" + quantidade.toString()

        var itens1: Boolean = false
        var habilidade1: Boolean = false

        if(itens != null)
        {
            itens1 = itens
        }

        if(habilidade != null)
        {
            habilidade1 = habilidade
        }


        val repository = Repository()

        //https://developer.android.com/kotlin/coroutines
        //Criando Thread em background
        if (offSet != null && quantidade != null) {
            GlobalScope.launch(Dispatchers.IO) {

                val list = repository.getPokemonList(offSet, quantidade)

                //Voltando para a MainThread
                withContext(Dispatchers.Main) {
                    setListOnScreen(list, habilidade1, itens1)
                }
            }
        }

    }

    private fun setListOnScreen(list: List<Pokemon>, habilidade: Boolean, itens: Boolean) {
        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
            layoutManager = GridLayoutManager(this@NextActivity, 3)
            //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = PokemonAdapter(list, habilidade, itens) {

            }
        }
    }
}