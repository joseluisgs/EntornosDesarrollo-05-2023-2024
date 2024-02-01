package validators

import dev.joseluisgs.exceptions.PersonaException
import dev.joseluisgs.models.Persona
import dev.joseluisgs.validators.PersonaValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PersonaValidatorTest {
    private val validator = PersonaValidator()

    @Test
    fun `validating persona with valid data should return the same persona`() {
        val persona = Persona(name = "John Doe", email = "john.doe@example.com")
        val result = validator.validate(persona)
        assertEquals(persona, result)
    }

    @Test
    fun `validating persona with empty name should throw NameNotValid exception`() {
        val persona = Persona(name = "", email = "john.doe@example.com")
        assertThrows<PersonaException.NameNotValid> {
            validator.validate(persona)
        }
    }

    @Test
    fun `validating persona with empty email should throw EmailNotValid exception`() {
        val persona = Persona(name = "John Doe", email = "")
        assertThrows<PersonaException.EmailNotValid> {
            validator.validate(persona)
        }
    }

    @Test
    fun `validating persona with invalid email should throw EmailNotValid exception`() {
        val persona = Persona(name = "John Doe", email = "john.doe")
        assertThrows<PersonaException.EmailNotValid> {
            validator.validate(persona)
        }
    }
}