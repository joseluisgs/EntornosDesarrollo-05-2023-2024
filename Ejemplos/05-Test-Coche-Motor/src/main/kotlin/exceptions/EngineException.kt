package dev.joseluisgs.exceptions

sealed class EngineException(message: String) : Exception(message) {
    class EngineExplote(message: String) : EngineException(message)
}