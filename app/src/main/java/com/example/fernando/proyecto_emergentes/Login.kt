package com.example.fernando.proyecto_emergentes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fernando.proyecto_emergentes.R.*
import com.example.fernando.proyecto_emergentes.data.LoginResponse
import com.example.fernando.proyecto_emergentes.data.cliente
import com.example.fernando.proyecto_emergentes.service.ApiService
import com.example.fernando.proyecto_emergentes.service.ClienteIdSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private lateinit var usuarioEditText: EditText
    private lateinit var contraseñaEditText: EditText

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_login)

        usuarioEditText = findViewById(id.etUsername)
        contraseñaEditText = findViewById(id.etPassword)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33:3200/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val create_acount: TextView = findViewById(id.crear)
        create_acount.setOnClickListener {
            val intent = Intent(this, create_account::class.java)
            startActivity(intent)
        }

        val Login: Button = findViewById(id.login)
        Login.setOnClickListener {

            val usuario = usuarioEditText.text.toString()
            val contraseña = contraseñaEditText.text.toString()

            val clienteVerificar: cliente = cliente(null, usuario, contraseña)

            login(clienteVerificar)
        }
    }

    fun login(Cliente:cliente){
        apiService.login(Cliente).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    val clienteId = response.body()?.ClienteId ?: -1

                    // Guardar el token en SharedPreferences
                    val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("token", token)
                    editor.putInt("ClienteId", clienteId ?: -1)
                    editor.apply()

                    // Guardar el ClienteId en ClienteIdSingleton
                    ClienteIdSingleton.guardarClienteId(clienteId)



                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Manejar respuesta de error del servidor
                    Toast.makeText(this@Login, "Error en el login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Manejar error de conexión u otros errores
                Toast.makeText(this@Login, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    fun verToken(){
        val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")

        if (token != null) {
            if (token.isNotEmpty()) {
                // Mostrar el token por consola
                Log.d("Token JWT", token)
            } else {
                Log.d("Token JWT", "No se encontró ningún token almacenado")
            }
        }
    }
}