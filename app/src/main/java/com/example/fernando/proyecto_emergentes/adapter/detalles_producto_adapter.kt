package com.example.fernando.proyecto_emergentes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.detalles_productos
import com.example.fernando.proyecto_emergentes.data.productos
import com.example.fernando.proyecto_emergentes.service.CarritoSingleton

class detalles_producto_adapter(private val detallesList: MutableList<detalles_productos>, private val productosList: MutableList<productos>) : RecyclerView.Adapter<detallesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detallesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return detallesViewHolder(layoutInflater.inflate(R.layout.item_select, parent, false))
    }

    override fun getItemCount(): Int = detallesList.size

    override fun onBindViewHolder(holder: detallesViewHolder, position: Int) {
        val item = detallesList[position]
        holder.render(item)
        holder.boton.setOnClickListener {
            CarritoSingleton.quitarProducto(productosList[position])
            detallesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, detallesList.size)
        }
    }
}