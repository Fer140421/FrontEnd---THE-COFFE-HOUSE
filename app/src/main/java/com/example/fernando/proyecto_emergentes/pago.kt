package com.example.fernando.proyecto_emergentes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class pago : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        val registro: Button = findViewById(R.id.registro)
        registro.setOnClickListener {
            val intent = Intent(this, purchase_detail::class.java)
            startActivity(intent)
        }
    }
}