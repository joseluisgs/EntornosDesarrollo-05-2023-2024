package repositories.personas

import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.personas.PersonasRepository
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class PersonasRepositoryTest {

    private val repo = PersonasRepository()

    @BeforeEach
    fun setUp() {
        repo.initRepository()
        repo.initExamples()
    }


    @Test
    fun getAll() {
        // Act
        val personas = repo.getAll()

        // Assert
        assertAll(
            { assertEquals(2, personas.size) },
            { assertEquals(1, personas[0].id) },
            { assertEquals(2, personas[1].id) },
            { assertEquals("Test01", personas[0].name) },
            { assertEquals("Test02", personas[1].name) }
        )
    }

    @Test
    fun getById() {
        // Act
        val persona = repo.getById(1)

        // Assert
        assertAll(
            { assertFalse(persona == null) },
            { assertEquals(1, persona?.id) },
            { assertEquals("Test01", persona?.name) }
        )
    }

    @Test
    fun getByIdNotFound() {
        // Act
        val persona = repo.getById(3)

        // Assert
        assertTrue(persona == null)
    }

    @Test
    fun delete() {
        // Act
        val persona = repo.delete(1)

        // Assert
        assertAll(
            { assertFalse(persona == null) },
            { assertEquals(1, persona?.id) },
            { assertEquals("Test01", persona?.name) },
            { assertEquals(1, repo.getAll().size) }
        )
    }

    @Test
    fun deleteNotFound() {
        // Act
        val persona = repo.delete(3)

        // Assert
        assertAll(
            { assertTrue(persona == null) },
            { assertEquals(2, repo.getAll().size) }
        )
    }

    @Test
    fun update() {
        // Act
        val persona = repo.update(1, Persona(1, "TestUpdate", "testUpdate"))

        // Assert
        assertAll(
            { assertFalse(persona == null) },
            { assertEquals(1, persona?.id) },
            { assertEquals("TestUpdate", persona?.name) },
            { assertEquals("testUpdate", persona?.email) },
            { assertEquals(2, repo.getAll().size) }
        )
    }

    @Test
    fun updateNotFound() {
        // Act
        val persona = repo.update(3, Persona(1, "TestUpdate", "testUpdate"))

        // Assert
        assertAll(
            { assertTrue(persona == null) },
            { assertEquals(2, repo.getAll().size) }
        )
    }

    @Test
    fun save() {
        // Act
        val persona = repo.save(Persona(name = "TestSave", email = "testSave"))

        // Assert
        assertAll(
            { assertFalse(persona == null) },
            { assertEquals(3, persona.id) },
            { assertEquals("TestSave", persona.name) },
            { assertEquals("testSave", persona.email) },
            { assertEquals(3, repo.getAll().size) }
        )
    }

    @Test
    fun repositoryResizedInSave() {
        // Act
        for (i in 1..10) {
            repo.save(Persona(name = "TestSave", email = "testSave"))
        }

        val res = repo.getAll()

        // Assert
        assertAll(
            { assertEquals(12, res.size) },
            { assertEquals(1, res[0].id) },
            { assertEquals("Test01", res[0].name) },
            { assertEquals("test01", res[0].email) },
            { assertEquals("Test02", res[1].name) },
            { assertEquals("test02", res[1].email) },
            { assertEquals("TestSave", res[2].name) },
            { assertEquals("testSave", res[2].email) },
            { assertEquals("TestSave", res[11].name) },
            { assertEquals("testSave", res[11].email) }
        )
    }

    @Test
    fun repositoryResizedInDelete() {
        // Act
        for (i in 1..30) {
            repo.save(Persona(name = "TestSave", email = "testSave"))
        }

        for (i in 1..20) {
            repo.delete(i)
        }

        val res = repo.getAll()

        // Assert
        assertEquals(12, res.size)
    }

    @Test
    fun repositorySortedById() {
        // Act
        for (i in 1..10) {
            repo.save(Persona(name = "TestSave${i}", email = "testSave${i}"))
        }
        
        val res = repo.sortById()

        // Assert
        assertAll(
            { assertEquals(12, res.size) },
            { assertEquals(1, res[0].id) },
            { assertEquals("Test01", res[0].name) },
            { assertEquals("test01", res[0].email) },
            { assertEquals("Test02", res[1].name) },
            { assertEquals("test02", res[1].email) },
            { assertEquals("TestSave1", res[2].name) },
            { assertEquals("testSave1", res[2].email) },
            { assertEquals("TestSave10", res[11].name) },
            { assertEquals("testSave10", res[11].email) }
        )
    }

}