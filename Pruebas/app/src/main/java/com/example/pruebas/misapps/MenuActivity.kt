package com.example.pruebas.misapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pruebas.R
import com.example.pruebas.misapps.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var appNombre: Button
    private lateinit var appIMC: Button
    private lateinit var appTODO: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initComponents()
        initListeners()
    }

    private fun initComponents(){
        appNombre = findViewById(R.id.appNombre)
        appIMC = findViewById(R.id.appIMC)
        appTODO = findViewById(R.id.appTODO)
    }

    private fun initListeners(){
        appNombre.setOnClickListener {
            val intent1 = Intent(this, FirstAppActivity::class.java)
            startActivity(intent1)
        }

        appIMC.setOnClickListener {
            val intent2 = Intent(this, CalculadorIMC::class.java)
            startActivity(intent2)
        }

        appTODO.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
    }
}