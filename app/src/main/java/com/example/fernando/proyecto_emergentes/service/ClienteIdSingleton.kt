package com.example.fernando.proyecto_emergentes.service

object ClienteIdSingleton {

    private var clienteId: Int = -1

    fun guardarClienteId(id: Int) {
        clienteId = id
    }

    fun obtenerClienteId(): Int {
        return clienteId
    }

    fun limpiarClienteId() {
       clienteId = -1
    }
}