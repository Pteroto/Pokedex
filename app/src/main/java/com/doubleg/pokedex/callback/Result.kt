package com.doubleg.pokedex.callback

import android.util.Log

class Result: CalculoCallback {
    override fun onSuccess(result: Int) {
        Log.d("teste", "o resultado foi: $result e na classe Result")
    }

    override fun onFailure() {
        Log.d("teste", "deu falha na classe Result")
    }
}