package com.doubleg.pokedex

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pkname = findViewById<EditText>(R.id.textViewBusca)
        val busca = findViewById<Button>(R.id.btn_search)
        val nomeapi = findViewById<TextView>(R.id.pkn_nomeapi)
        val idapi = findViewById<TextView>(R.id.pkn_idapi)
        val textViewtype = findViewById<TextView>(R.id.pkn_types)
        val textViewStats = findViewById<TextView>(R.id.pkn_stats)


        var teste = SearchPokemon()



        busca.setOnClickListener {


            if (pkname.text.toString() == "") {
                pkname.text = SpannableStringBuilder("bulbasaur")
            }

            val teste5 = object : PokemonResponse{
                override fun onSuccess(pokemon: Pokemon) {
                    nomeapi.text = pokemon.name
                    idapi.text = pokemon.id.toString()
                    Log.d("TESTE", "onSuccess ${pokemon.name}")
                }

                override fun onFailure() {
                    Log.d("TESTE", "esse pokemon ${pkname.text} não existe")
//                    idapi.text = "Esse pokemon não existe"
                }


            }
           teste.pesquisa(pkname.text.toString(),teste5)


//            Log.d("TESTE", "nome_pokemon: $retorno" ?: "")
//
//            val id = pokemon?.id
//            idapi.text = id.toString()
//            Log.d("TESTE", "id_pokemon: " + id.toString())
//
//            var poketype = ""
//            pokemon?.types?.forEach {
//                poketype += it.type.name + " "
//            }
//            textViewtype.text = poketype
//
//            var pokestats = ""
//            pokemon?.stats?.forEach {
//                pokestats += it.stat.name + ": " + it.base_stat + "\n"
//            }
//            textViewStats.text = pokestats
        }

    }

//    override fun onSuccess(name: String) {
//        Log.d("TESTE", "onSuccess $name")
//    }


}


