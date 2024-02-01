package dev.joseluisgs.services.personas

import dev.joseluisgs.exceptions.PersonaException
import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.personas.PersonasRepository
import dev.joseluisgs.validators.PersonaValidator

class PersonasServiceImpl(
    private val repository: PersonasRepository,
    private val validator: PersonaValidator
) : PersonasService {
    override fun getAll(): Array<Persona> {
        return repository.getAll()
    }

    override fun getById(id: Int): Persona {
        return repository.getById(id) ?: throw PersonaException.NotFound(id)
    }

    override fun save(persona: Persona): Persona {
        validator.validate(persona)
        return repository.save(persona)
    }

    override fun update(id: Int, persona: Persona): Persona {
        validator.validate(persona)
        return repository.update(id, persona) ?: throw PersonaException.NotFound(id)
    }

    override fun delete(id: Int): Persona {
        return repository.delete(id) ?: throw PersonaException.NotFound(id)
    }

    override fun sorted(): Array<Persona> {
        return repository.sortById()
    }
}