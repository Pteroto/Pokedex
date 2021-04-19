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

class MainActivity : AppCompatActivity(), ResponseResult {

    private lateinit var nomePokemon: TextView
    private lateinit var idPokemon: TextView
    private lateinit var imageViewFront: ImageView
    private lateinit var imageViewBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buscaPokemon = BuscaPokemon()

        nomePokemon = findViewById(R.id.TextViewNomePokemon)

        val buscar = findViewById<Button>(R.id.bttBuscar)

        idPokemon = findViewById(R.id.TextViewIdPokemon)
        var chaveBusca = findViewById<EditText>(R.id.EditTextChave)

        imageViewFront = findViewById(R.id.ImageViewPokemonFront)
        imageViewBack = findViewById(R.id.ImageViewPokemonBack)

        val imageNext = findViewById<ImageView>(R.id.ImageViewNext)
        val imageBack = findViewById<ImageView>(R.id.ImageViewBack)

//        var ide: Int





        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/fnLTpcJ/direita-removebg-preview.png")
            .into(imageNext)

        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/hZGWqYx/esquerda-removebg-preview.png")
            .into(imageBack)


//        ide = idPokemon.text.toString().toInt()


        buscar.setOnClickListener {
            buscaPokemon.buscaNome(chaveBusca.text.toString(), this@MainActivity)
        }
//
//        imageNext.setOnClickListener {
//            ide = ide+1
//            buscaPokemon.buscaNome(ide.toString(), this@MainActivity)
//        }
//
//        imageBack.setOnClickListener {
//            ide = ide-1
//            buscaPokemon.buscaNome(ide.toString(), this@MainActivity)
//        }

    }

    override fun onSucess(pokemon: Pokemon){
        nomePokemon.text = pokemon.name
        idPokemon.text = pokemon.id.toString()



        Glide.with(this@MainActivity)
            .load(pokemon.sprites.front_default)
            .into(imageViewFront)

        Glide.with(this@MainActivity)
            .load(pokemon.sprites.back_default)
            .into(imageViewBack)


    }

    override fun onError(erro: String) {
        nomePokemon.text = erro
        idPokemon.text = erro
    }

    override fun notFound(msg: String) {
        nomePokemon.text = msg
        idPokemon.text = msg

    }


}
