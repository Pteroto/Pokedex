package com.doubleg.pokedex

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: PokemonService = retrofit.create(PokemonService::class.java)

        val button = findViewById<Button>(R.id.button)

        //enqueueExample(button, service)
        executeExample(button, service)
    }

    private fun enqueueExample(button: Button, service: PokemonService) {
        button.setOnClickListener {
            service.getPokemon("bulbasaur").enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, response.body()?.name, Toast.LENGTH_SHORT)
                            .show()
                        val name = response.body()?.name
                        Log.d("TESTE", name ?: "")
                        val id = response.body()?.id
                        Log.d("TESTE", id.toString())
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("TESTE", t.message ?: "")
                }
            })
        }
    }

    private fun executeExample(button: Button, service: PokemonService) {
        button.setOnClickListener {
            //MainThread
            AsyncTask.execute(object : Runnable {
                override fun run() {
                    //BackgroundThread
                    val response = service.getPokemon("pikachu").execute()
                    runOnUiThread(object : Runnable {
                        override fun run() {
                            //MainThread
                            Toast.makeText(this@MainActivity, response.body()?.name, Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            })
        }
    }
}