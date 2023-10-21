package com.example.pruebas.misapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.pruebas.R

class ImprimeMiNombreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imprime_mi_nombre)
        val bienvenida = findViewById<TextView>(R.id.bienvenida)
        val nombre: String = intent.extras?.getString("EXTRA").orEmpty()

        bienvenida.text = "Bienvenido ${nombre}"
    }
}