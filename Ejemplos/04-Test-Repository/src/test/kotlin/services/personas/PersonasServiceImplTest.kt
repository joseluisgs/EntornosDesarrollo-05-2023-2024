package services.personas

import dev.joseluisgs.exceptions.PersonaException
import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.personas.PersonasRepository
import dev.joseluisgs.services.personas.PersonasServiceImpl
import dev.joseluisgs.validators.PersonaValidator
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PersonasServiceImplTest {
    @MockK
    lateinit var personasRepository: PersonasRepository

    @MockK
    lateinit var personaValidator: PersonaValidator

    @InjectMockKs
    lateinit var personasServiceImpl: PersonasServiceImpl

    // Otra forma de hacerlo
    /*private val repository = mockk<PersonasRepository>()
    private val validator = mockk<PersonaValidator>()
    private val service = PersonasServiceImpl(repository, validator)*/

    @Test
    fun getAll() {
        // Arrange
        val personas = arrayOf(
            Persona(1, "Test01", "test01@test.com"),
            Persona(2, "Test02", "test02@test.com"),
        )

        every { personasRepository.getAll() } returns personas

        // Act
        val result = personasServiceImpl.getAll()

        // Assert
        assertAll(
            { assert(result.size == 2) },
            { assert(result[0].id == personas[0].id) },
            { assert(result[1].id == personas[1].id) },
            { assert(result[0].name == personas[0].name) },
            { assert(result[1].name == personas[1].name) },
            { assert(result[0].email == personas[0].email) },
            { assert(result[1].email == personas[1].email) },
        )

        verify(exactly = 1) { personasRepository.getAll() }

    }

    @Test
    fun getById() {
        // Arrange
        val persona = Persona(1, "Test01", "test01@test.com")

        every { personasRepository.getById(1) } returns persona

        // Act
        val result = personasServiceImpl.getById(1)

        // Assert
        assertAll(
            { assert(result.id == persona.id) },
            { assert(result.name == persona.name) },
            { assert(result.email == persona.email) },
        )

        verify(exactly = 1) { personasRepository.getById(1) }
    }

    @Test
    fun getByIdNotFound() {
        // Arrange
        every { personasRepository.getById(1) } returns null

        // Act
        val result = assertThrows<PersonaException.NotFound> {
            personasServiceImpl.getById(1)
        }
        // Assert
        assertEquals("No se ha encontrado la persona con id: 1", result.message)

        verify(exactly = 1) { personasRepository.getById(1) }
    }

    @Test
    fun save() {
        // Arrange
        val persona = Persona(1, "Test01", "test01@test.com")

        every { personaValidator.validate(persona) } returns persona
        every { personasRepository.save(persona) } returns persona

        // Act
        val result = personasServiceImpl.save(persona)

        // Assert
        assertAll(
            { assert(result.id == persona.id) },
            { assert(result.name == persona.name) },
            { assert(result.email == persona.email) },
        )

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 1) { personasRepository.save(persona) }
    }

    @Test
    fun saveNameInvalid() {
        // Arrange
        val persona = Persona(1, "", "test01@test.com")

        every { personaValidator.validate(persona) } throws PersonaException.NameNotValid()

        // Act
        val result = assertThrows<PersonaException.NameNotValid> {
            personasServiceImpl.save(persona)
        }

        // Assert
        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.save(persona) }
    }

    @Test
    fun saveEmailEmptyInvalid() {
        // Arrange
        val persona = Persona(1, "Test01", "")

        every { personaValidator.validate(persona) } throws PersonaException.EmailNotValid()

        // Act
        val result = assertThrows<PersonaException.EmailNotValid> {
            personasServiceImpl.save(persona)
        }

        // Assert
        assertEquals("El email no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.save(persona) }
    }

    @Test
    fun saveEmailNotEmailInvalid() {
        // Arrange
        val persona = Persona(1, "Test01", "test01")

        every { personaValidator.validate(persona) } throws PersonaException.EmailNotValid()

        // Act
        val result = assertThrows<PersonaException.EmailNotValid> {
            personasServiceImpl.save(persona)
        }

        // Assert
        assertEquals("El email no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.save(persona) }
    }

    @Test
    fun update() {
        // Arrange
        val persona = Persona(1, "Test01", "test01@test.com")

        every { personaValidator.validate(persona) } returns persona
        every { personasRepository.update(1, persona) } returns persona

        // Act
        val result = personasServiceImpl.update(1, persona)

        // Assert
        assertAll(
            { assert(result.id == persona.id) },
            { assert(result.name == persona.name) },
            { assert(result.email == persona.email) },
        )

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 1) { personasRepository.update(1, persona) }
    }

    @Test
    fun updateNameInvalid() {
        // Arrange
        val persona = Persona(1, "", "test01@test.com")

        every { personaValidator.validate(persona) } throws PersonaException.NameNotValid()

        // Act
        val result = assertThrows<PersonaException.NameNotValid> {
            personasServiceImpl.update(1, persona)
        }

        // Assert
        assertEquals("El nombre no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.update(1, persona) }
    }

    @Test
    fun updateEmailEmptyInvalid() {
        // Arrange
        val persona = Persona(1, "Test01", "")

        every { personaValidator.validate(persona) } throws PersonaException.EmailNotValid()

        // Act
        val result = assertThrows<PersonaException.EmailNotValid> {
            personasServiceImpl.update(1, persona)
        }

        // Assert
        assertEquals("El email no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.update(1, persona) }
    }

    @Test
    fun updateEmailNotEmailInvalid() {
        // Arrange
        val persona = Persona(1, "Test01", "test01")

        every { personaValidator.validate(persona) } throws PersonaException.EmailNotValid()

        // Act
        val result = assertThrows<PersonaException.EmailNotValid> {
            personasServiceImpl.update(1, persona)
        }

        // Assert
        assertEquals("El email no es válido", result.message)

        verify(exactly = 1) { personaValidator.validate(persona) }
        verify(exactly = 0) { personasRepository.update(1, persona) }
    }

    @Test
    fun delete() {
        // Arrange
        val persona = Persona(1, "Test01", "test01@test.com")

        every { personasRepository.delete(1) } returns persona

        // Act
        val result = personasServiceImpl.delete(1)

        // Assert
        assertAll(
            { assert(result.id == persona.id) },
            { assert(result.name == persona.name) },
            { assert(result.email == persona.email) },
        )

        verify(exactly = 1) { personasRepository.delete(1) }
    }

    @Test
    fun deleteNotFound() {
        // Arrange
        every { personasRepository.delete(1) } returns null

        // Act
        val result = assertThrows<PersonaException.NotFound> {
            personasServiceImpl.delete(1)
        }
        // Assert
        assertEquals("No se ha encontrado la persona con id: 1", result.message)

        verify(exactly = 1) { personasRepository.delete(1) }
    }

    @Test
    fun sorted() {
        // Arrange
        val personas = arrayOf(
            Persona(1, "Test01", "test01@test.como"),
            Persona(2, "Test02", "test02@test.como"),
        )

        every { personasRepository.sortById() } returns personas

        // Act
        val result = personasServiceImpl.sorted()

        // Assert
        assertAll(
            { assert(result[0].id == personas[0].id) },
            { assert(result[1].id == personas[1].id) },
        )

        verify(exactly = 1) { personasRepository.sortById() }
    }
}