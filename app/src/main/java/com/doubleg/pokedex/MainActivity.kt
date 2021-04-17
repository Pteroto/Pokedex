package com.doubleg.pokedex

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity(),ResponseResult {

    private lateinit var nomePokemon: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buscaPokemon = BuscaPokemon()

//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://pokeapi.co/api/v2/")
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//
//        val service: PokemonService = retrofit.create(PokemonService::class.java)

        nomePokemon = findViewById<TextView>(R.id.TextViewNomePokemon)

        val buscar = findViewById<Button>(R.id.bttBuscar)

        val idPokemon = findViewById<TextView>(R.id.TextViewIdPokemon)
        var chaveBusca = findViewById<EditText>(R.id.EditTextChave)

        val imageViewFront = findViewById<ImageView>(R.id.ImageViewPokemonFront)
        val imageViewBack = findViewById<ImageView>(R.id.ImageViewPokemonBack)

        val imageNext = findViewById<ImageView>(R.id.ImageViewNext)
        val imageBack = findViewById<ImageView>(R.id.ImageViewBack)


        var url: String
        var cont: Int
        var cont2: Int

        var ide: Int

        cont = 0
        cont2 = 0



        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/fnLTpcJ/direita-removebg-preview.png")
            .into(imageNext)

        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/hZGWqYx/esquerda-removebg-preview.png")
            .into(imageBack)



        ide = 0



        buscar.setOnClickListener {


            buscaPokemon.buscaNome(chaveBusca.text.toString(),this@MainActivity )


//            service.getPokemon(chaveBusca.text.toString()).enqueue(object : Callback<Pokemon> {
//                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
//                    if (response.isSuccessful) {
//                        nomePokemon.text = (response.body()?.name)
//                        idPokemon.text = (response.body()?.id).toString()
//                        ide = (response.body()?.id).toString().toInt()
//
////                        Toast.makeText(
////                            this@MainActivity,
////                            (response.body()?.abilities?.get(1)?.ability?.name),
////                            Toast.LENGTH_LONG
////                        ).show()
//
//                        url = (response.body()?.sprites?.front_default.toString())
//
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewFront)
//
//                        url = (response.body()?.sprites?.back_default.toString())
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewBack)
//
//                    } else {
//                        nomePokemon.text = getString(R.string.not_found_pokemon)
//                        idPokemon.text = getString(R.string.not_found_pokemon)
//                        ide = 999
//                    }
//                }
//
//                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                    nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
//                    idPokemon.text = getString(R.string.search_error) + t.localizedMessage
//                    ide = 9999
//                }
//            })
        }

//
//        imageNext.setOnClickListener {
//
//            ide = ide+1

//            service.getPokemon((ide).toString()).enqueue(object : Callback<Pokemon> {
//                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
//                    if (response.isSuccessful) {
//                        nomePokemon.text = (response.body()?.name)
//                        idPokemon.text = (response.body()?.id).toString()
//                        Toast.makeText(
//                            this@MainActivity,
//                            (response.body()?.abilities?.get(1)?.ability?.name),
//                            Toast.LENGTH_LONG
//                        ).show()
//
//                        url = (response.body()?.sprites?.front_default.toString())
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewFront)
//
//                        url = (response.body()?.sprites?.back_default.toString())
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewBack)
//
//                    } else {
//                        nomePokemon.text = getString(R.string.not_found_pokemon)
//                        idPokemon.text = getString(R.string.not_found_pokemon)
//                    }
//                }
//
//                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                    nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
//                    idPokemon.text = getString(R.string.search_error) + t.localizedMessage
//                }
//            })
//
//        }
//
//
//        imageBack.setOnClickListener {
//
//           ide = ide - 1
//
//            service.getPokemon((ide).toString()).enqueue(object : Callback<Pokemon> {
//                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
//                    if (response.isSuccessful) {
//                        nomePokemon.text = (response.body()?.name)
//                        idPokemon.text = (response.body()?.id).toString()
//                        Toast.makeText(
//                            this@MainActivity,
//                            (response.body()?.abilities?.get(1)?.ability?.name),
//                            Toast.LENGTH_LONG
//                        ).show()
//
//                        url = (response.body()?.sprites?.front_default.toString())
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewFront)
//
//                        url = (response.body()?.sprites?.back_default.toString())
//                        Glide.with(this@MainActivity)
//                            .load(url)
//                            .into(imageViewBack)
//
//                    } else {
//                        nomePokemon.text = getString(R.string.not_found_pokemon)
//                        idPokemon.text = getString(R.string.not_found_pokemon)
//                    }
//                }
//
//                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                    nomePokemon.text = getString(R.string.search_error) + t.localizedMessage
//                    idPokemon.text = getString(R.string.search_error) + t.localizedMessage
//                }
//            })
//
//        }
//
//
//
//
   }

    override fun onResponse(nome: String) {

        nomePokemon.text = nome

        Log.d("onResponse", nome)

    }
}
