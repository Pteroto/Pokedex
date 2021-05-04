package com.doubleg.pokedex.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
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

        val intent = Intent(this, NextActivity::class.java)

        val offSet = findViewById<EditText>(R.id.EditTextOffset)
        offSet.setHint("OffSet")

        val quantidade = findViewById<EditText>(R.id.EditTextQuantidade)
        quantidade.setHint("Quantidade")

        val habilidade = findViewById<CheckBox>(R.id.CheckBoxHabilidade)
        val itens = findViewById<CheckBox>(R.id.CheckBoxItens)


        val buscar = findViewById<Button>(R.id.BttBuscar)

        val params = Bundle()


        buscar.setOnClickListener {


            if (habilidade.isChecked) {
                params.putBoolean("habilidade", true)
            } else {
                params.putBoolean("habilidade", false)
            }

            if (itens.isChecked) {
                params.putBoolean("itens", true)
            } else {
                params.putBoolean("itens", false)
            }

            params.putString("offSet", offSet.text.toString())
            params.putString("quantidade", quantidade.text.toString())
            intent.putExtras(params)
            startActivity(intent)

        }


//        val repository = Repository()
//
//        //https://developer.android.com/kotlin/coroutines
//        //Criando Thread em background
//        GlobalScope.launch(Dispatchers.IO) {
//            val list = repository.getPokemonList()
//
//            //Voltando para a MainThread
//            withContext(Dispatchers.Main) {
//                setListOnScreen(list)
//            }
//        }
//
//        Log.d("teste", "teste")
    }

//    private fun setListOnScreen(list: List<Pokemon>) {
//        findViewById<RecyclerView>(R.id.recyclerViewPokemon).apply {
//            layoutManager = GridLayoutManager(this@MainActivity, 3)
//                //LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//            adapter = PokemonAdapter(list) {
//                Log.d("teste", it.name)
//            }
//        }
//    }
}