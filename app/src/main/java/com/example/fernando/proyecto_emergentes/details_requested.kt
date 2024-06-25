package com.example.fernando.proyecto_emergentes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.adapter.detalles_producto_adapter
import com.example.fernando.proyecto_emergentes.adapter.productos_adapter
import com.example.fernando.proyecto_emergentes.data.detalles_productos
import com.example.fernando.proyecto_emergentes.data.productos
import com.example.fernando.proyecto_emergentes.service.CarritoSingleton

class details_requested : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var detallesAdapter: detalles_producto_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_requested)
        setupRecyclerView()

        val next: Button = findViewById(R.id.next)
        next.setOnClickListener {
            val intent = Intent(this, purchase_detail::class.java)
            startActivity(intent)
        }

        CarritoSingleton.listener = {
            detallesAdapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.select_carrito)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val productosSeleccionados: MutableList<productos> = CarritoSingleton.productosSeleccionados
        val listaDetalle: MutableList<detalles_productos> = productosSeleccionados.map { producto ->
            detalles_productos(producto.nombre, "1", producto.precio.toString())
        }.toMutableList()

        //Detalles de los productos seleccionados
        detallesAdapter = detalles_producto_adapter(listaDetalle, productosSeleccionados)
        recyclerView.adapter = detallesAdapter
    }

}