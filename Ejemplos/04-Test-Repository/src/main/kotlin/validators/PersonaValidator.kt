package dev.joseluisgs.validators

import dev.joseluisgs.exceptions.PersonaException
import dev.joseluisgs.models.Persona

class PersonaValidator {
    fun validate(persona: Persona): Persona {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        if (persona.name.isEmpty()) {
            throw PersonaException.NameNotValid()
        }
        if (persona.email.isEmpty() || !emailRegex.matches(persona.email)) {
            throw PersonaException.EmailNotValid()
        }
        return persona
    }
}