package dev.joseluisgs.models

data class Persona(var id: Int = 0, var name: String, var email: String) {

    override fun toString(): String {
        return "Persona(id=$id, name='$name', email='$email')"
    }
}