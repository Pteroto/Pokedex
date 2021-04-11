package com.doubleg.pokedex

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nomePokemon = findViewById<TextView>(R.id.TextViewNomePokemon)
        val idPokemon = findViewById<TextView>(R.id.TextViewIdPokemon)
        val chaveBusca = findViewById<EditText>(R.id.EditTextChave)
        val buscar = findViewById<Button>(R.id.bttBuscar)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: PokemonService = retrofit.create(PokemonService::class.java)


        buscar.setOnClickListener {

            service.getPokemon(chaveBusca.text.toString()).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        nomePokemon.text = (response.body()?.name)
                        idPokemon.text = (response.body()?.id).toString()

                    } else {
                        nomePokemon.text = "Pokemon Não Encontrado"
                        idPokemon.text = "Pokemon Não Encontrado"
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    nomePokemon.text = "Erro na Busca" + t.localizedMessage
                    idPokemon.text = "Erro na Busca" + t.localizedMessage

                }
            })


        }


    }


}