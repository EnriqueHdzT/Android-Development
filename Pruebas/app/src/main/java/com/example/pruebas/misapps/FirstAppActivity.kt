package com.example.pruebas.misapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.pruebas.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)
        val miName = findViewById<AppCompatEditText>(R.id.miName)
        val ingresar = findViewById<AppCompatButton>(R.id.ingresar)

        ingresar.setOnClickListener {
            val name = miName.text.toString()
            butIngrsar(name)
        }
    }

    fun butIngrsar(name: String) {
        if (name.isNotEmpty()) {
            val intent = Intent(this, ImprimeMiNombreActivity::class.java)
            intent.putExtra("EXTRA", name)
            startActivity(intent)
        }
    }
}