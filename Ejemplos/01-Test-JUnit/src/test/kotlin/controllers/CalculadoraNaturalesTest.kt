package controllers

import dev.joseluisgs.controllers.CalculadoraNaturales
import org.junit.jupiter.api.*
import org.junit.jupiter.api.condition.DisabledOnJre
import org.junit.jupiter.api.condition.JRE
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertTrue

// https://www.baeldung.com/kotlin/junit-5-kotlin
// https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-name-generator

@DisplayName("Test de la calculadora de números naturales")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class) // Para que se ejecuten en orden
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Para que se cree una instancia por clase y no por método
class CalculadoraNaturalesTest {

    val calculadora = CalculadoraNaturales()


    @BeforeAll
    fun setUpAll() {
        println("Antes de todos los test")
    }

    @AfterAll
    fun tearDownAll() {
        println("Después de todos los test")
    }


    @BeforeEach
    fun setUp() {
        println("Antes de cada test")
    }

    @AfterEach
    fun tearDown() {
        println("Después de cada test")
    }

    // @Test // Test de prueba
    @DisplayName("Suma de dos números naturales") // Display para que se vea bonito
    @Order(1) // Para que se ejecute en el orden que queramos
    @RepeatedTest(10) // Para que se repita el test las veces que queramos
    fun sumaOk() {

        // arrange
        val a = 2
        val b = 3

        // act
        val resultado = calculadora.suma(a, b)

        // assert
        Assertions.assertEquals(5, resultado)
    }

    @Test
    @DisplayName("Suma de dos números naturales cuando A es menor que cero")
    // @EnabledOnOs(value = [OS.LINUX, OS.MAC]) // Para que se ejecute en un sistema operativo
    // @EnabledOnJre(value = [JRE.JAVA_11]) // Para que se ejecute en una versión de Java
    // @DisabledOnOs(value = [OS.WINDOWS]) // Para que no se ejecute en un sistema operativo
    @DisabledOnJre(value = [JRE.JAVA_11]) // Para que no se ejecute en una versión de Java
    fun sumaSiAesMenorACero() {

        // arrange
        val a = -2
        val b = 3

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.suma(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    fun sumaSiBesMenorACero() {

        // arrange
        val a = 2
        val b = -3

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.suma(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    // @Test
    @DisplayName("Resta de dos números naturales")
    @ParameterizedTest()
    @CsvSource(
        "1, 1, 0",
        "4, 2, 2",
        "9, 3, 6"
    )
    fun restaOk(a: Int, b: Int, resultado: Int) {

        // arrange
        // val a = 3
        // val b = 4

        // act
        val res = calculadora.resta(a, b)

        // assert
        Assertions.assertEquals(resultado, res)
    }

    @Test
    @Tag("Resta")
    fun restaSiBesMayorQueA() {
        // arrange
        val a = 3
        val b = 4

        // act
        val res = calculadora.resta(a, b)

        // assert
        Assertions.assertEquals(0, res)
        assertTrue(res == 0)
    }

    @Test
    fun restaSiAesMenorACero() {
        // arrange
        val a = -3
        val b = 4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.resta(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    fun restaSiBesMenorACero() {
        // arrange
        val a = 3
        val b = -4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.resta(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    // @Test
    @DisplayName("Multiplicación de dos números naturales")
    @ParameterizedTest()
    @CsvFileSource(resources = ["/multiplicacion-ok.csv"], numLinesToSkip = 1)
    fun multiplicacionOk(a: Int, b: Int, resultado: Int) {

        // arrange
        //val a = 3
        //val b = 2

        // act
        val res = calculadora.multiplicacion(a, b)

        // assert
        Assertions.assertEquals(resultado, res)
    }


    @Test
    fun multiplicacionSiAesMenorACero() {
        // arrange
        val a = -3
        val b = 4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.multiplicacion(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    fun multiplicacionSiBesMenorACero() {
        // arrange
        val a = 3
        val b = -4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.multiplicacion(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    fun divisionOk() {

        // arrange
        val a = 6
        val b = 2

        // act
        val resultado = calculadora.division(a, b)

        // assert
        Assertions.assertEquals(3, resultado)
    }

    @Test
    fun divisionSiAesMenorACero() {
        // arrange
        val a = -3
        val b = 4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.division(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    fun divisionSiBesMenorACero() {
        // arrange
        val a = 3
        val b = -4

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.division(a, b)
        }

        // assert
        Assertions.assertEquals("Los números deben ser naturales", res.message)
    }

    @Test
    @Order(2)
    @DisplayName("División de dos números naturales cuando B es cero")
    fun divisionSiBesCero() {
        // arrange
        val a = 3
        val b = 0

        // act
        val res = assertThrows<IllegalArgumentException> {
            calculadora.division(a, b)
        }

        // assert
        Assertions.assertEquals("El divisor debe ser mayor a cero", res.message)
    }
}