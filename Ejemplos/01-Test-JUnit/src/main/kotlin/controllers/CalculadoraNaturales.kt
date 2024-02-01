package dev.joseluisgs.controllers

class CalculadoraNaturales : Calculadora<Int> {
    override fun suma(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0) { "Los números deben ser naturales" }
        //if (a < 0 || b < 0) throw IllegalArgumentException("Los números deben ser naturales")
        return a + b
    }

    override fun resta(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0) { "Los números deben ser naturales" }
        if (b > a) return 0
        return a - b
    }

    override fun multiplicacion(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0) { "Los números deben ser naturales" }
        return a * b
    }

    override fun division(a: Int, b: Int): Int {
        require(a >= 0 && b >= 0) { "Los números deben ser naturales" }
        require(b != 0) { "El divisor debe ser mayor a cero" }
        return a / b
    }

}