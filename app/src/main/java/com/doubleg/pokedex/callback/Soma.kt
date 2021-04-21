package com.doubleg.pokedex.callback

class Soma : Calculo() {
    override fun tipoCalculo(valor1: Int, valor2: Int): Int {
        return valor1 + valor2
    }
}