package com.example.fernando.proyecto_emergentes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.adapter.historial_adapter
import com.example.fernando.proyecto_emergentes.data.historial
import com.example.fernando.proyecto_emergentes.service.ApiService
import com.example.fernando.proyecto_emergentes.service.ClienteIdSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class shopping_history : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var historialAdapter: historial_adapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_history)

        setupRecyclerView()
        val ClienteId = ClienteIdSingleton.obtenerClienteId()

        val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
   //     val ClienteId = sharedPreferences.getInt("ClienteId", -1)
        if (ClienteId != -1) {
            obtenerHistorial(ClienteId)
        } else {
            Toast.makeText(this, "Error: No se encontró el clienteId en SharedPreferences", Toast.LENGTH_SHORT).show()
            Log.e("shopping_history", "Error: No se encontró el clienteId en SharedPreferences")
        }
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recicler_historial)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historialAdapter = historial_adapter(emptyList())
        recyclerView.adapter = historialAdapter
    }

    private fun obtenerHistorial(ClienteId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33:3200/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val call: Call<List<historial>> = apiService.obtenerHistorial(ClienteId)
        call.enqueue(object : Callback<List<historial>> {
            override fun onResponse(call: Call<List<historial>>, response: Response<List<historial>>) {
                if (response.isSuccessful) {
                    val historialList = response.body() ?: emptyList()
                    historialAdapter.actualizarLista(historialList)
                } else {
                    Toast.makeText(this@shopping_history, "Error al obtener el historial", Toast.LENGTH_SHORT).show()
                    Log.e("shopping_history", "Error en onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<historial>>, t: Throwable) {
                Log.e("shopping_history", "Error de red", t)
                Toast.makeText(this@shopping_history, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
