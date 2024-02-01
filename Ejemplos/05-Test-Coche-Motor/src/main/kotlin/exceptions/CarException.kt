package dev.joseluisgs.exceptions

sealed class CarException(message: String) : Exception(message) {
    class CarExplote(message: String) : CarException(message)
    class CarWithotGas(message: String) : CarException(message)
    class CarNotStarted(message: String) : CarException(message)
    class CarCantGo(message: String) : CarException(message)
}