package com.example.fernando.proyecto_emergentes.adapter

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.historial
import com.example.fernando.proyecto_emergentes.data.productos

class productosViewHolder(view: View): RecyclerView.ViewHolder(view) {
    //val foto = view.findViewById<ImageView>(R.id.product_image)
    val nombre = view.findViewById<TextView>(R.id.product)
    val boton = view.findViewById<Button>(R.id.add_carrito)


    fun render(producto: productos) {
        nombre.text = producto.nombre
    }

}