package com.doubleg.pokedex

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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

        val buscar = findViewById<Button>(R.id.bttBuscar)
        val nomePokemon = findViewById<TextView>(R.id.TextViewNomePokemon)
        val idPokemon = findViewById<TextView>(R.id.TextViewIdPokemon)
        val chaveBusca = findViewById<EditText>(R.id.EditTextChave)
        val suaImagem = findViewById<ImageView>(R.id.ImageViewPokemon)

        var url: String

        buscar.setOnClickListener {
            service.getPokemon(chaveBusca.text.toString()).enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        nomePokemon.text = (response.body()?.name)
                        idPokemon.text = (response.body()?.id).toString()
                        Toast.makeText(
                            this@MainActivity,
                            (response.body()?.abilities?.get(1)?.ability?.name),
                            Toast.LENGTH_LONG
                        ).show()

                        url = (response.body()?.sprites?.front_default.toString())

                        Glide.with(this@MainActivity).load(url).into(suaImagem)

                    } else {
                        nomePokemon.text = getString(R.string.not_found_pokemon)
                        idPokemon.text = getString(R.string.not_found_pokemon)
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
                    idPokemon.text = getString(R.string.search_error) + t.localizedMessage
                }
            })
        }
    }
}
