package com.example.fernando.proyecto_emergentes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.R
import com.example.fernando.proyecto_emergentes.data.historial

class historial_adapter(private var historialList: List<historial>) : RecyclerView.Adapter<historialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): historialViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return historialViewHolder(layoutInflater.inflate(R.layout.item_historial, parent, false))
    }

    override fun getItemCount(): Int {
        return historialList.size
    }

    override fun onBindViewHolder(holder: historialViewHolder, position: Int) {
        val item = historialList[position]
        val fechaFormateada = item.fechapedido.replace("T", " ").substring(0, 16)
        holder.fechapedido.text = fechaFormateada
        holder.nombre.text = item.nombre
        holder.total.text = item.total
    }

    fun actualizarLista(nuevaLista: List<historial>) {
        historialList = nuevaLista
        notifyDataSetChanged()
    }
}
