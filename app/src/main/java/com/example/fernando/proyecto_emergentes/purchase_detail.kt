package com.example.fernando.proyecto_emergentes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.fernando.proyecto_emergentes.data.LoginResponse
import com.example.fernando.proyecto_emergentes.data.productos
import com.example.fernando.proyecto_emergentes.service.ApiService
import com.example.fernando.proyecto_emergentes.service.CarritoSingleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fernando.proyecto_emergentes.data.pedido
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class purchase_detail : AppCompatActivity() {
    private lateinit var montototal: TextView
    private lateinit var apiService: ApiService
    private lateinit var direccionEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_detail)
        montototal=findViewById(R.id.textView5)
        montototal.text=calcular().toString()
        direccionEditText = findViewById(R.id.etUsername)
        val tarjeta: Button = findViewById(R.id.pago)
        tarjeta.setOnClickListener {
            val intent = Intent(this, pago::class.java)
            startActivity(intent)
        }

        val confirmar: Button = findViewById(R.id.boton_comprar)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33:3200/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)


        confirmar.setOnClickListener {
            confirmarCompra()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun calcular(): Int {
        val productosSeleccionados: MutableList<productos> = CarritoSingleton.productosSeleccionados
        var montoTotal=0
        for (producto in productosSeleccionados)
            montoTotal += producto.precio!!
        return montoTotal
    }
    private fun confirmarCompra() {
        // Aquí iría el código para procesar la compra
        val productosSeleccionados: MutableList<productos> = CarritoSingleton.productosSeleccionados
        //agregarPedido()
        val direccionEditText1 = direccionEditText.text.toString()
        val nuevoPedido = pedido(
            ClienteID = 1, // Ejemplo de ID del cliente
            Direccion = direccionEditText1,
            Total = calcular(), // Ejemplo de total del pedido
            Estado = "Pendiente" // Ejemplo de estado del pedido
        )
        agregarPedido(nuevoPedido)

        // Una vez que la compra se confirma, muestra el mensaje de confirmación
        mostrarMensajeConfirmacion()
    }
    private fun agregarPedido(nuevoPedido: pedido) {
        val callAdd: Call<pedido> = apiService.agregarPedido(nuevoPedido)
        callAdd.enqueue(object : Callback<pedido> {
            override fun onResponse(call: Call<pedido>, response: Response<pedido>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Pedido creado correctamente", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(applicationContext, "Error al agregar pedido", Toast.LENGTH_SHORT).show()
                    // Manejar el error en caso de fallo en la solicitud
                }
            }

            override fun onFailure(call: Call<pedido>, t: Throwable) {
                Log.i("TEOTEO", "Se produjo un error al agregar el pedido")
            }
        })
    }

    private fun mostrarMensajeConfirmacion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Compra Exitosa")
        builder.setMessage("Gracias por su compra. Su transacción se ha completado exitosamente.")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss() // Cierra el diálogo al presionar el botón
        }



        val dialog = builder.create()
        dialog.show()
    }
}