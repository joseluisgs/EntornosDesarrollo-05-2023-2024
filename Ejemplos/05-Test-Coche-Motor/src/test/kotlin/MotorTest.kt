import dev.joseluisgs.Motor
import dev.joseluisgs.exceptions.EngineException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MotorTest {

    lateinit var motor: Motor

    @BeforeEach
    fun setUp() {
        motor = Motor()
    }

    @Test
    fun getRevoluciones() {
        assertEquals(0, motor.revoluciones)
        motor.arrancar()
        assertEquals(800, motor.revoluciones)
    }


    @Test
    fun arrancar() {
        motor.arrancar()
        assertEquals(800, motor.revoluciones)
    }

    @Test
    fun parar() {
        motor.arrancar()
        assertEquals(800, motor.revoluciones)
        motor.parar()
        assertEquals(0, motor.revoluciones)
    }

    @Test
    fun acelerar() {
        motor.arrancar()
        assertEquals(800, motor.revoluciones)
        motor.acelerar(1000)
        assertEquals(1550, motor.revoluciones)
    }

    @Test
    fun acelerarExplotado() {
        motor.arrancar()
        assertEquals(800, motor.revoluciones)

        val res = assertThrows<EngineException.EngineExplote> {
            motor.acelerar(10000)
        }
        assertEquals("El motor ha explotado", res.message)
    }

    @Test
    fun acelerarParado() {
        motor.arrancar()
        assertEquals(800, motor.revoluciones)
        motor.acelerar(-2000)
        assertEquals(0, motor.revoluciones)
    }
}