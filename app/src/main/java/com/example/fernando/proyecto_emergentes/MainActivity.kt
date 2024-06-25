package com.example.fernando.proyecto_emergentes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val historial_comp: ImageView = findViewById(R.id.historial_compras)
        historial_comp.setOnClickListener {
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, shopping_history::class.java)
            startActivity(intent)
        }

        val carrito: ImageView = findViewById(R.id.carrito_menu)
        carrito.setOnClickListener {
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, details_requested::class.java)
            startActivity(intent)
        }


        val cafe: ImageView = findViewById(R.id.cat_cafe)
        cafe.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "1")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }

        val te: ImageView = findViewById(R.id.cat_te)
        te.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "2")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }

        val pasteles: ImageView = findViewById(R.id.cat_pasteles)
        pasteles.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "3")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }

        val sandwiches: ImageView = findViewById(R.id.cat_sand)
        sandwiches.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "4")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }

        val desayunos: ImageView = findViewById(R.id.cat_desayunos)
        desayunos.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "5")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }

        val bebidas: ImageView = findViewById(R.id.cat_bebidas)
        bebidas.setOnClickListener {
            val sharedPreferences = getSharedPreferences("mi_app_pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("idCategoria", "6")
            editor.apply()
            // Crear el Intent para ir a la actividad de Historial de Compras
            val intent = Intent(this, product_cat::class.java)
            startActivity(intent)
        }











    }
}