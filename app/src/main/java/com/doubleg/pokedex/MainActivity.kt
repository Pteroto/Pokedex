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
    private lateinit var npokemon: Pokemon
    private lateinit var retornopokemon: Pokemon
    //criando lista de pokemon
    var listaPokemon: MutableList<Pokemon>?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buscaPokemon = BuscaPokemon()

        //inicializando a mutablelist
        listaPokemon = mutableListOf()




        var imagemListaPokemon = findViewById<ImageView>(R.id.ImageViewListaPokemon)

        nomePokemon = findViewById(R.id.TextViewNomePokemon)

        val buscar = findViewById<Button>(R.id.bttBuscar)

        idPokemon = findViewById(R.id.TextViewIdPokemon)
        var chaveBusca = findViewById<EditText>(R.id.EditTextChave)

        imageViewFront = findViewById(R.id.ImageViewPokemonFront)
        imageViewBack = findViewById(R.id.ImageViewPokemonBack)

        val criarListaPokemon = findViewById<Button>(R.id.bttMontarLista)


        val imageNext = findViewById<ImageView>(R.id.ImageViewNext)
        val imageBack = findViewById<ImageView>(R.id.ImageViewBack)


        val lista1 = findViewById<ImageView>(R.id.ImageViewListaPokemon2)
        val lista2 = findViewById<ImageView>(R.id.ImageViewListaPokemon)


        var ide: Int

        var cont: Int

        var ide2: Int // andar com id do pokemon para contar



        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/fnLTpcJ/direita-removebg-preview.png")
            .into(imageNext)

        Glide.with(this@MainActivity)
            .load("https://i.ibb.co/hZGWqYx/esquerda-removebg-preview.png")
            .into(imageBack)


        buscar.setOnClickListener {
            buscaPokemon.buscaNome(chaveBusca.text.toString(), this@MainActivity)
//            listaPokemon.
        }

        imageNext.setOnClickListener {


            ide = npokemon.id + 1
            if (ide>897) {
                ide = 1
            }
            buscaPokemon.buscaNome(ide.toString(), this@MainActivity)
        }

        imageBack.setOnClickListener {
            ide = npokemon.id - 1

            if(ide<1){
                ide = 897
            }
            buscaPokemon.buscaNome(ide.toString(), this@MainActivity)


        }

        criarListaPokemon.setOnClickListener {

//            buscaPokemon.montaLista(this)

            Glide.with(this)
                .load(listaPokemon?.randomOrNull()?.sprites?.front_default)
                .into(lista1)

            Glide.with(this)
                .load(listaPokemon?.randomOrNull()?.sprites?.front_default)
                .into(lista2)

        }




    }

    override fun onSucess(pokemon: Pokemon) {
        retornopokemon = pokemon
        npokemon = pokemon
        nomePokemon.text = pokemon.name
        idPokemon.text = pokemon.id.toString()

        listaPokemon?.add(pokemon)


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
