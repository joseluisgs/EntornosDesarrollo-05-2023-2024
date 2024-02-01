package dev.joseluisgs

import dev.joseluisgs.exceptions.EngineException
import org.lighthousegames.logging.logging

private val logger = logging()

class Motor : Engine {
    var revoluciones: Int = 0
        private set

    override fun arrancar() {
        logger.debug { "Arrancando motor" }
        this.revoluciones = 800
    }

    override fun parar() {
        logger.debug { "Parando motor" }
        this.revoluciones = 0
    }

    override fun acelerar(value: Int) {
        logger.debug { "Acelerando motor" }
        this.revoluciones += (0.75 * value).toInt()
        if (this.revoluciones > 6000) {
            logger.error { "El motor ha explotado" }
            throw EngineException.EngineExplote("El motor ha explotado")
        }
        if (this.revoluciones < 0) {
            logger.debug { "El motor ha parado" }
            this.revoluciones = 0
        }
    }
}