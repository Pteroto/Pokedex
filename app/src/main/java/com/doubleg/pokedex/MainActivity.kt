package com.doubleg.pokedex

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.doubleg.pokedex.callback.CalculoCallback
import com.doubleg.pokedex.callback.Result
import com.doubleg.pokedex.callback.ResultLog
import com.doubleg.pokedex.callback.Soma

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        val soma = Soma()
        soma.seForPar(2, 2, object : CalculoCallback {
            override fun onSuccess(result: Int) {
                mostrarNoLog(result, "Par")
            }

            override fun onFailure() {
                mostrarErroNoLog()
            }
        })

        soma.seForImpar(2, 2, object : CalculoCallback {
            override fun onSuccess(result: Int) {
                mostrarNoLog(result, "Impar")
            }

            override fun onFailure() {
                mostrarErroNoLog()
            }
        })

        val resultLog = ResultLog()

        soma.seForPar(2, 2, Result())
        soma.seForPar(2, 3, Result())
        soma.teste(2, 2, resultLog)
    }

    fun mostrarNoLog(resultado: Int, tipo: String) {
        textView.text = resultado.toString()
        Log.d("teste", "o resultado foi: $resultado e é $tipo")
    }

    fun mostrarErroNoLog() {
        Log.d("teste", "O numero nao atendeu aos requisitos")
    }
}
