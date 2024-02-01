package dev.joseluisgs.repositories.personas

import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.base.CrudRepository

class PersonasRepository : CrudRepository<Persona, Int> {
    private var personas: Array<Persona?> = arrayOfNulls<Persona>(10)
    private var key: Int = 0

    override fun getAll(): Array<Persona> {
        // Creamos un nuevo array con el tamaño de los elementos no nulos
        /* val personasNoNulas = Array<Persona>(personas.size - contarNulos()) { i ->
             personas[i]!!
         }
         return personasNoNulas*/
        return personas.filterNotNull().toTypedArray()
    }


    override fun getById(key: Int): Persona? {
        return personas.firstOrNull { it?.id == key }
    }

    override fun delete(key: Int): Persona? {
        // sacamos su indice
        var persona: Persona? = null
        this.personas.indexOfFirst { it?.id == key }
            .takeIf { it != -1 }
            ?.let {
                // Si existe lo borramos
                persona = personas[it]
                personas[it] = null
            }.also {
                // Redimensionamos el array si hay mas de 10 nulos
                if (personas.count { it == null } > 10) {
                    personas = personas.filterNotNull().toTypedArray()
                }
            }
        return persona
    }

    override fun update(key: Int, value: Persona): Persona? {
        return getById(key)?.apply {
            name = value.name
            email = value.email
        }
    }

    override fun save(value: Persona): Persona {
        // Si el array está lleno (no tiene nulos)
        if (personas.count { it == null } == 0) {
            // Redimensionamos el array
            personas = personas.copyOf(personas.size + 10)
        }
        // Añadimos el elemento en el primer nulo que encontremos
        val index = personas.indexOfFirst { it == null }
        key++ // Incrementamos la clave
        value.id = key // Asignamos la clave
        personas[index] = value // Asignamos el valor
        return value // Devolvemos el valor
    }

    fun initRepository() {
        personas = arrayOfNulls<Persona>(10)
        key = 0

    }

    fun initExamples() {
        personas[0] = Persona(1, "Test01", "test01")
        personas[1] = Persona(2, "Test02", "test02")
        key = 2
    }

    override fun sortById(): Array<Persona> {
        return personas
            .filterNotNull()
            .sortedBy { it.id }
            .toTypedArray()
    }
}