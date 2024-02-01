package dev.joseluisgs.controllers

interface Calculadora<T> {
    fun suma(a: T, b: T): T
    fun resta(a: T, b: T): T
    fun multiplicacion(a: T, b: T): T
    fun division(a: T, b: T): T

    fun nada(a: T, b: T)
}