package com.example.fernando.proyecto_emergentes

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fernando.proyecto_emergentes.data.cliente
import com.example.fernando.proyecto_emergentes.service.ApiService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class create_account : AppCompatActivity() {
    private lateinit var usuarioEditText: EditText
    private lateinit var contraseñaEditText: EditText
    private lateinit var repetirContraseñaEditText: EditText
    private lateinit var create_acount: Button

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        usuarioEditText = findViewById(R.id.etName)
        contraseñaEditText = findViewById(R.id.etPassword)
        repetirContraseñaEditText = findViewById(R.id.etConfirmPassword)
        create_acount = findViewById(R.id.create_cuenta)



        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33:3200/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        create_acount.setOnClickListener {

            val usuario = usuarioEditText.text.toString()
            val contraseña = contraseñaEditText.text.toString()
            val repetirContraseña = repetirContraseñaEditText.text.toString()

            if (contraseña != repetirContraseña) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nuevoCliente: cliente = cliente(null, usuario, contraseña)

            agregarCliente(nuevoCliente)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()


        }
    }

    private fun agregarCliente(nuevoCliente:cliente){
        val callAdd: Call<cliente> = apiService.agregarCliente(nuevoCliente)
        callAdd.enqueue(object : Callback<cliente> {
            override fun onResponse(p0: Call<cliente>, p1: Response<cliente>) {
                if (p1.isSuccessful) {
                    Toast.makeText(applicationContext, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                    // Aquí puedes manejar la respuesta exitosa, por ejemplo, limpiar los campos de texto
                    usuarioEditText.text.clear()
                    contraseñaEditText.text.clear()
                    repetirContraseñaEditText.text.clear()
                } else {
                    Toast.makeText(applicationContext, "Error al agregar cliente", Toast.LENGTH_SHORT).show()
                    // Manejar el error en caso de fallo en la solicitud
                }
            }


            override fun onFailure(p0: Call<cliente>, p1: Throwable) {
                Log.i("TEOTEO", "Se produjo un error al agregar")
            }
        })
    }


fun listar(){
  val listarCall: Call<List<cliente>> = apiService.listarClientes()
  listarCall.enqueue(object : Callback<List<cliente>> {
      override fun onResponse(call: Call<List<cliente>>, response: Response<List<cliente>>) {
          if (response.isSuccessful) {
              Log.i("TEOTEO","conexion exitosa")

          }
      }

      override fun onFailure(call: Call<List<cliente>>, t: Throwable) {
          // Handle failure
          Log.i("TEOTEO","Fallo en la conexion"+t.message+"\n"+t.stackTrace)
          Log.d("TEOTEO", Log.getStackTraceString(t));
      }
  })
}
}
