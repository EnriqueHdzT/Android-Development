package com.example.pruebas.misapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pruebas.R
import com.example.pruebas.misapps.CalculadorIMC.Companion.IMC_key

class ResultadoIMCActivity : AppCompatActivity() {
    private lateinit var tuEstado: TextView
    private lateinit var tuIMC: TextView
    private lateinit var tuDescription: TextView
    private lateinit var recalcular: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_imc)
        val result: Double = intent.extras?.getDouble(IMC_key) ?: -1.0
        initComponents()
        initListeners()
        initUI(result)
    }

    private fun initListeners(){
        recalcular.setOnClickListener { onBackPressed() }
    }

    private fun initComponents() {
        tuEstado = findViewById(R.id.tuEstado)
        tuIMC = findViewById(R.id.tuIMC)
        tuDescription = findViewById(R.id.tuDescription)
        recalcular = findViewById(R.id.recalcular)
    }

    private fun initUI(result: Double) {
        tuIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Vulimia
                tuEstado.text = "Bajo Peso"
                tuEstado.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tuDescription.text = "Tu peso actual esta por debajo de lo normal."
            }

            in 18.51..24.99 -> { // Normal
                tuEstado.text = "Normal"
                tuEstado.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tuDescription.text = "Tu peso actual esta normal."
            }

            in 25.00..29.99 -> { // Sobre peso
                tuEstado.text = "Sobre Peso"
                tuEstado.setTextColor(ContextCompat.getColor(this,R.color.peso_sobrepeso))
                tuDescription.text = "Tu peso actual esta sobre el peso de lo normal."
            }

            in 30.0..99.0 -> { // Obseidad
                tuEstado.text = "Obesidad"
                tuEstado.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tuDescription.text = "Tu peso actual esta muy lejos de lo normal."
            }

            else -> {
                tuIMC.text = "error"
                tuEstado.text = "error"
                tuEstado.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tuDescription.text = "error"
            }

        }

    }


}