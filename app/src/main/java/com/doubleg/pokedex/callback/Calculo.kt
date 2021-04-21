package com.doubleg.pokedex.callback

abstract class Calculo {

    abstract fun tipoCalculo(valor1: Int, valor2: Int): Int

    fun seForPar(valor1: Int, valor2: Int, callback: CalculoCallback) {
        val result = tipoCalculo(valor1, valor2)
        if (result % 2 == 0) {
            callback.onSuccess(result)
        } else {
            callback.onFailure()
        }
    }

    fun seForImpar(valor1: Int, valor2: Int, callback: CalculoCallback) {
        val result = tipoCalculo(valor1, valor2)
        if (result % 2 != 0) {
            callback.onSuccess(result)
        } else {
            callback.onFailure()
        }
    }

    fun teste(valor1: Int, valor2: Int, resultLog: ResultLog) {
        val result = tipoCalculo(valor1, valor2)
        resultLog.log(result)
    }
}