import dev.joseluisgs.Coche
import dev.joseluisgs.Motor
import dev.joseluisgs.exceptions.CarException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class CocheTest {
    @MockK
    private lateinit var motor: Motor

    @InjectMockKs
    private lateinit var coche: Coche

    @Test
    fun getCombustible() {
        every { motor.revoluciones } returns 0 // Está parado
        every { motor.arrancar() } returns Unit

        coche.puestaEnMarcha()

        assertEquals(55, coche.combustible)

        verify(exactly = 1) { motor.arrancar() }

    }

    @Test
    fun puestaEnMarchaConMotorParado() {
        every { motor.revoluciones } returns 0 // Está parado
        every { motor.arrancar() } returns Unit

        coche.puestaEnMarcha()

        verify(exactly = 1) { motor.arrancar() }
    }

    @Test
    fun puestaEnMarchaConMotorArrancado() {
        every { motor.revoluciones } returns 800 // Está parado
        every { motor.arrancar() } returns Unit

        val res = assertThrows<CarException.CarCantGo> {
            coche.puestaEnMarcha()
        }

        assertEquals("El coche ya está arrancado", res.message)

        verify(exactly = 0) { motor.arrancar() }
    }

    @Test
    fun puestaEnMarchaSinCombustible() {
        every { motor.revoluciones } returns 0 // Está parado
        every { motor.arrancar() } returns Unit

        coche.combustible = 0

        val res = assertThrows<CarException.CarWithotGas> {
            coche.puestaEnMarcha()
        }

        assertEquals("El coche no tiene combustible", res.message)

        verify(exactly = 1) { motor.arrancar() }
    }

    @Test
    fun detener() {
        every { motor.revoluciones } returns 800 //
        every { motor.parar() } returns Unit

        coche.detener()

        verify(exactly = 1) { motor.parar() }
    }

    @Test
    fun detenerSiParado() {
        every { motor.revoluciones } returns 0 //
        every { motor.parar() } returns Unit

        val res = assertThrows<CarException.CarNotStarted> {
            coche.detener()
        }

        verify(exactly = 0) { motor.parar() }
    }

    @Test
    fun darGasCon10() {
        every { motor.revoluciones } returns 800 //
        every { motor.acelerar(any()) } returns Unit

        coche.darGas(10)

        verify(exactly = 1) { motor.acelerar(10) }
    }

    @Test
    fun darGasCon20() {
        every { motor.revoluciones } returns 800
        every { motor.acelerar(any()) } returns Unit

        coche.darGas(20)

        verify(exactly = 1) { motor.acelerar(20) }
    }

    @Test
    fun darGasCon9() {
        every { motor.revoluciones } returns 800
        every { motor.acelerar(any()) } returns Unit

        val res = assertThrows<CarException.CarCantGo> {
            coche.darGas(9)
        }

        assertEquals("El coche no puede acelerar tanto", res.message)

        verify(exactly = 0) { motor.acelerar(9) }
    }

    @Test
    fun darGasCon21() {
        every { motor.revoluciones } returns 800
        every { motor.acelerar(any()) } returns Unit

        val res = assertThrows<CarException.CarCantGo> {
            coche.darGas(21)
        }

        assertEquals("El coche no puede acelerar tanto", res.message)

        verify(exactly = 0) { motor.acelerar(21) }
    }

    @Test
    fun darGasSiParado() {
        every { motor.revoluciones } returns 0 //
        every { motor.acelerar(any()) } returns Unit

        val res = assertThrows<CarException.CarNotStarted> {
            coche.darGas(10)
        }

        assertEquals("El coche no está arrancado", res.message)

        verify(exactly = 0) { motor.acelerar(10) }
    }

    @Test
    fun darGasSinCombustible() {
        every { motor.revoluciones } returns 800 //
        every { motor.acelerar(any()) } returns Unit

        coche.combustible = 5
        val res = assertThrows<CarException.CarWithotGas> {
            coche.darGas(15)
        }

        assertEquals("El coche no tiene combustible", res.message)

        verify(exactly = 0) { motor.acelerar(10) }
    }
}