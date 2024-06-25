package com.example.fernando.proyecto_emergentes.data

import java.io.Serializable

data class productos (
    val productoid:Int?,
    val nombre:String?,
    val descripcion:String?,
    val precio:Int?,
    val categoriaid:String?,
    val stock: Int?,
):Serializable