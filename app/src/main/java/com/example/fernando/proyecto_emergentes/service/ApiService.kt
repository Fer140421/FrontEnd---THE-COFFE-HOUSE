package com.example.fernando.proyecto_emergentes.service

import com.example.fernando.proyecto_emergentes.data.LoginResponse
import com.example.fernando.proyecto_emergentes.data.cliente
import com.example.fernando.proyecto_emergentes.data.historial
import com.example.fernando.proyecto_emergentes.data.pedido
import com.example.fernando.proyecto_emergentes.data.productos
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/clientes")
    fun agregarCliente(@Body Cliente: cliente): Call<cliente>

    @POST("/login")
    fun login(@Body Cliente: cliente): Call<LoginResponse>

    @POST("/pedidos")
    fun agregarPedido(@Body Pedido: pedido): Call<pedido>

    @GET("/clientes")
    fun listarClientes():Call<List<cliente>>

    @GET("/productos/{id}")
    fun listarProductosCategoria(@Path("id") id:Int):Call<List<productos>>

    @GET("historial/{clienteId}")
    fun obtenerHistorial(@Path("clienteId") clienteId: Int): Call<List<historial>>
}