package com.example.fernando.proyecto_emergentes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.adapter.historial_adapter
import com.example.fernando.proyecto_emergentes.adapter.productos_adapter
import com.example.fernando.proyecto_emergentes.data.historial
import com.example.fernando.proyecto_emergentes.data.productos
import com.example.fernando.proyecto_emergentes.service.ApiService
import com.example.fernando.proyecto_emergentes.service.CarritoSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class product_cat : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productosAdapter: productos_adapter

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_cat)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33:3200/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val carrito: ImageView = findViewById(R.id.ver_carrito)
        carrito.setOnClickListener {
            val productosSeleccionados:List<productos> = CarritoSingleton.productosSeleccionados

            //Intent que lleva a la actividad del detalle del carrito
            val intent = Intent(this, details_requested::class.java)
            intent.putExtra("productosSeleccionados", ArrayList(productosSeleccionados))
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
        val idCat = sharedPreferences.getString("idCategoria", "")

        if (idCat != null) {
            listaProductosCategoria(idCat)
        }
    }

    private fun setupRecyclerView(listaProductos:List<productos>) {
        recyclerView = findViewById(R.id.categoria_producto)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productosAdapter = productos_adapter(listaProductos)
        recyclerView.adapter = productosAdapter
    }

    private fun listaProductosCategoria(idCat:String){
        apiService.listarProductosCategoria(idCat.toInt()).enqueue(object : Callback<List<productos>> {
            override fun onResponse(call: Call<List<productos>>, response: Response<List<productos>>) {
                if (response.isSuccessful) {
                    val productos: List<productos>? = response.body()
                    if (productos != null) {
                        setupRecyclerView(productos)
                    }
                } else {
                    // Manejar respuesta de error del servidor
                    Log.e("Error", "Error en la solicitud: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<productos>>, t: Throwable) {
                // Manejar error de conexión u otros errores
                Log.e("Error", "Error de conexión: ${t.message}")
            }
        })
    }
}