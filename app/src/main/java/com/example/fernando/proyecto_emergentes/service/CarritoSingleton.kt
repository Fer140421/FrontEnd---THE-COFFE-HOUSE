package com.example.fernando.proyecto_emergentes.service

import com.example.fernando.proyecto_emergentes.data.productos

object CarritoSingleton {
    val productosSeleccionados: MutableList<productos> = mutableListOf()
    var listener: (() -> Unit)? = null

    fun quitarProducto(producto: productos) {
        productosSeleccionados.remove(producto)
        listener?.invoke()
    }

    fun limpiarCarrito() {
        productosSeleccionados.clear()
        listener?.invoke()
    }
}