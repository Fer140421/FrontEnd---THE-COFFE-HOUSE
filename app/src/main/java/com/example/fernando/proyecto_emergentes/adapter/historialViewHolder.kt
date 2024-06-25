package com.example.fernando.proyecto_emergentes.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.historial

class historialViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val fechapedido = view.findViewById<TextView>(R.id.fechapedido)
    val nombre = view.findViewById<TextView>(R.id.nombre)
    val total = view.findViewById<TextView>(R.id.total)
    fun render(historial:historial){
        fechapedido.text = historial.fechapedido
        nombre.text = historial.nombre
        total.text=historial.total
    }
}