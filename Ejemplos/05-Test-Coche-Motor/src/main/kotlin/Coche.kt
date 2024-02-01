package dev.joseluisgs

import dev.joseluisgs.exceptions.CarException
import org.lighthousegames.logging.logging

private val logger = logging()

class Coche(
    private val motor: Motor
) : Car {
    var combustible = 60


    override fun puestaEnMarcha() {
        logger.debug { "Puesta en marcha del coche" }
        if (motor.revoluciones > 0) {
            logger.error { "El coche ya está arrancado" }
            throw CarException.CarCantGo("El coche ya está arrancado")
        }
        motor.arrancar()
        this.combustible -= 5
        if (this.combustible < 0) {
            logger.error { "El coche no tiene combustible" }
            throw CarException.CarWithotGas("El coche no tiene combustible")
        }
    }

    override fun detener() {
        logger.debug { "Deteniendo coche" }
        if (motor.revoluciones == 0) {
            logger.error { "El coche no está arrancado" }
            throw CarException.CarNotStarted("El coche no está arrancado")
        }
        motor.parar()
    }

    override fun darGas(value: Int) {
        if (motor.revoluciones == 0) {
            logger.error { "El coche no está arrancado" }
            throw CarException.CarNotStarted("El coche no está arrancado")
        }
        logger.debug { "Acelerando coche" }
        if (value !in 10..20) {
            logger.error { "El coche no puede acelerar tanto" }
            throw CarException.CarCantGo("El coche no puede acelerar tanto")
        }
        motor.acelerar(value)
        this.combustible -= (0.9 * value).toInt()
        if (this.combustible <= 0) {
            logger.error { "El coche no tiene combustible" }
            throw CarException.CarWithotGas("El coche no tiene combustible")
        }
    }
}