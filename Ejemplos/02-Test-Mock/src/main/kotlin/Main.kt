package dev.joseluisgs

import dev.joseluisgs.controllers.CalculadoraController
import dev.joseluisgs.controllers.CalculadoraNaturales

fun main() {
    println("Hello World!")
    val calc = CalculadoraNaturales()
    println(calc.suma(2, 3))

    val controller = CalculadoraController(calc)
}