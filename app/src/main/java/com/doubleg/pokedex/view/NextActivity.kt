package com.doubleg.pokedex.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.doubleg.pokedex.R

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)



        var offSet = intent.getStringExtra("offSet")

        var mostrarOffset = findViewById<TextView>(R.id.TextViewMostrarOffSet)

        mostrarOffset.text = offSet



    }
}