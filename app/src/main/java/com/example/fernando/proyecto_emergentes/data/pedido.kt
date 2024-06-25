package com.example.fernando.proyecto_emergentes.data

import java.io.Serializable

data class pedido (
    val ClienteID:Int?,
    val Direccion:String?,
    val Total:Int?,
    val Estado:String?,
): Serializable