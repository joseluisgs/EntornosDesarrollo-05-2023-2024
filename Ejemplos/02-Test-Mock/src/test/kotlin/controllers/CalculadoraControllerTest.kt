package controllers

import dev.joseluisgs.controllers.Calculadora
import dev.joseluisgs.controllers.CalculadoraController
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import kotlin.test.assertEquals

@Extensions(ExtendWith(MockKExtension::class))
class CalculadoraControllerTest {

    @MockK // Mockeamos la calculadora
    lateinit var calculadora: Calculadora<Int> // o CalculadoraNaturales

    @InjectMockKs // Inyectamos el mock
    lateinit var controller: CalculadoraController

    @Test
    fun suma() {
        // Arrange
        val a = 2
        val b = 3
        val res = 5

        // // Mockeamos el método suma de la calculadora
        every { calculadora.suma(a, b) } returns res

        // Act
        val result = controller.suma(a, b)

        // Assert
        assertEquals(res, result)
        verify(exactly = 1) { calculadora.suma(a, b) }
    }

    @Test
    fun resta() {
    }

    @Test
    fun multiplica() {
    }

    @Test
    fun divide() {
    }

    @Test
    fun divideBesCero() {
        // Arrange
        val a = 2
        val b = 0

        // // Mockeamos el método suma de la calculadora
        every { calculadora.division(a, b) } throws IllegalArgumentException("El divisor debe ser mayor a cero")

        // Act
        val res = assertThrows<IllegalArgumentException> {
            controller.divide(a, b)
        }

        // Assert
        assertEquals("El divisor debe ser mayor a cero", res.message)
        verify(exactly = 1) { calculadora.division(a, b) }
    }

    @Test
    fun noHacerNada() {
        val a = 2
        val b = 3

        // Mockeamos el método nada de la calculadora
        every { calculadora.nada(a, b) } returns Unit

        controller.noHacerNada(a, b)

        verify(exactly = 1) { calculadora.nada(a, b) }
    }
}