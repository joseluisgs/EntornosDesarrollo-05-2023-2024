package dev.joseluisgs.services.personas

import dev.joseluisgs.models.Persona

interface PersonasService {
    fun getAll(): Array<Persona>
    fun getById(id: Int): Persona
    fun save(persona: Persona): Persona
    fun update(id: Int, persona: Persona): Persona
    fun delete(id: Int): Persona
    fun sorted(): Array<Persona>
}