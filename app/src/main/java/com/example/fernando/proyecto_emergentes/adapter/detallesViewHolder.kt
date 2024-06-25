package com.example.fernando.proyecto_emergentes.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.detalles_productos
import com.example.fernando.proyecto_emergentes.data.historial

class detallesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nombre = view.findViewById<TextView>(R.id.product_name)
    val cantidad = view.findViewById<TextView>(R.id.product_quantity)
    val total = view.findViewById<TextView>(R.id.product_total_price)
    val boton = view.findViewById<Button>(R.id.remove_product)

    fun render(detalles: detalles_productos) {
        nombre.text = detalles.producto
        cantidad.text = detalles.cantidad
        total.text = detalles.precio_total
    }
}