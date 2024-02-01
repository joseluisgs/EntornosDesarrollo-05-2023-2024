package dev.joseluisgs.controllers

class CalculadoraController(val calculadora: Calculadora<Int>) {

    fun suma(a: Int, b: Int): Int {
        return calculadora.suma(a, b)
    }

    fun resta(a: Int, b: Int): Int {
        return calculadora.resta(a, b)
    }

    fun multiplica(a: Int, b: Int): Int {
        return calculadora.multiplicacion(a, b)
    }

    fun divide(a: Int, b: Int): Int {
        return calculadora.division(a, b)
    }

    fun noHacerNada(a: Int, b: Int): Unit {
        calculadora.nada(a, b)
    }
}