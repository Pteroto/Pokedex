package com.doubleg.pokedex.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import java.time.OffsetDateTime

class MainActivity : AppCompatActivity() {

    //onCreate serve para criar a tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView serve para inflar a tela, ou seja, criar a classe da dela


        val search = findViewById<Button>(R.id.search)


        search.setOnClickListener() {

            nextScreen()

        }
    }


    private fun nextScreen() {
        val limit = findViewById<EditText>(R.id.limit).text.toString().toInt()
        val offset = findViewById<EditText>(R.id.offset).text.toString().toInt()

        val sendIntent = Intent(this, ShowPokemon::class.java)
        sendIntent.putExtra("limit", limit)
        sendIntent.putExtra("offset", offset)
        //putextra pode ser usado varias vezes pois ele tem um array de key


        startActivity(sendIntent)
    }

    companion object {
        const val limit = "limit"
        const val offset = "offset"
    }
}