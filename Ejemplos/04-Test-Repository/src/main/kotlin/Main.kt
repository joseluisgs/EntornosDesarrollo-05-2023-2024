package dev.joseluisgs

import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.personas.PersonasRepository
import dev.joseluisgs.services.personas.PersonasServiceImpl
import dev.joseluisgs.validators.PersonaValidator

fun main() {
    println("Hola Repositorios y Servicios")
    val personasRepository = PersonasRepository()
    val personasValidator = PersonaValidator()
    val personasService = PersonasServiceImpl(personasRepository, personasValidator)

    try {
        val persona = personasService.getById(1)
        println(persona)
    } catch (e: Exception) {
        println(e.message)
    }

    try {
        val persona = personasService.getById(3)
        println(persona)
    } catch (e: Exception) {
        println(e.message)
    }

    try {
        val persona = personasService.save(Persona(3, "Test03", ""))
        println(persona)
    } catch (e: Exception) {
        println(e.message)
    }
}