package dev.joseluisgs.exceptions

sealed class PersonaException(message: String) : Exception(message) {
    class NotFound(id: Int) : PersonaException("No se ha encontrado la persona con id: $id")
    class NameNotValid : PersonaException("El nombre no es válido")
    class EmailNotValid : PersonaException("El email no es válido")
}