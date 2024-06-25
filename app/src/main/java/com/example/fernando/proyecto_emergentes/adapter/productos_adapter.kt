package com.example.fernando.proyecto_emergentes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.productos
import com.example.fernando.proyecto_emergentes.service.CarritoSingleton

class productos_adapter(private val productosList:List<productos> ): RecyclerView.Adapter<productosViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return productosViewHolder(layoutInflater.inflate(R.layout.item_productos, parent, false))
    }

    override fun getItemCount(): Int = productosList.size

    override fun onBindViewHolder(holder: productosViewHolder, position: Int) {
        val item = productosList[position]
        holder.render(item)
        holder.boton.setOnClickListener {
            if (!CarritoSingleton.productosSeleccionados.contains(item)) {
                CarritoSingleton.productosSeleccionados.add(item)
                Toast.makeText(holder.itemView.context, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(holder.itemView.context, "El producto ya está en el carrito", Toast.LENGTH_SHORT).show()
            }
        }
    }
}