package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private var result: String = ""
    private var contPar: Int = 0
    private var comPunto: Boolean = false
    private var compOper: Boolean = false
    private var esMas: Boolean = false
    private var esMenos: Boolean = false
    private var esDivision: Boolean = false
    private var esMultiplicacion: Boolean = false
    private var esParentesis: Boolean = false
    private lateinit var resultado: TextView
    private lateinit var igual: AppCompatButton
    private lateinit var menos: AppCompatButton
    private lateinit var mas: AppCompatButton
    private lateinit var multiplicacion: AppCompatButton
    private lateinit var division: AppCompatButton
    private lateinit var punto: AppCompatButton
    private lateinit var cambio: AppCompatButton
    private lateinit var cero: AppCompatButton
    private lateinit var uno: AppCompatButton
    private lateinit var dos: AppCompatButton
    private lateinit var tres: AppCompatButton
    private lateinit var cuatro: AppCompatButton
    private lateinit var cinco: AppCompatButton
    private lateinit var seis: AppCompatButton
    private lateinit var siete: AppCompatButton
    private lateinit var ocho: AppCompatButton
    private lateinit var nueve: AppCompatButton
    private lateinit var parAbre: AppCompatButton
    private lateinit var parCierra: AppCompatButton
    private lateinit var borrar: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declaración de mis botones y textView
        initComponents()

        //Solucionar el código
        igual.setOnClickListener {
            if (contPar != 0)
                for (i in contPar downTo 1)
                    result += ')'
            resolver()
            result = ""
            comPunto = false
            esOperador("nada")
            contPar = 0
        }

        //BorrarTod
        borrar.setOnClickListener {
            result = ""
            resultado.setText("0")
            comPunto = false
            esOperador("nada")
            contPar = 0
        }

        //Para los numeros
        cero.setOnClickListener {
            if (!result.isEmpty()) {
                result += "0"
                resultado.setText(result)
            } else resultado.setText("0")
        }
        uno.setOnClickListener { concatenaNumeros("1") }
        dos.setOnClickListener { concatenaNumeros("2") }
        tres.setOnClickListener { concatenaNumeros("3") }
        cuatro.setOnClickListener { concatenaNumeros("4") }
        cinco.setOnClickListener { concatenaNumeros("5") }
        seis.setOnClickListener { concatenaNumeros("6") }
        siete.setOnClickListener { concatenaNumeros("7") }
        ocho.setOnClickListener { concatenaNumeros("8") }
        nueve.setOnClickListener { concatenaNumeros("9") }

        //Para punto
        punto.setOnClickListener {
            if (!comPunto) {
                comPunto = true
                result += "."
                resultado.setText(result)
                esOperador("nada")
                esParentesis = false
            }
        }

        //Para los parentesis
        parAbre.setOnClickListener {
            if (result.isEmpty() || compOper) {
                contPar++
                result += "("
                resultado.setText(result)
                esOperador("nada")
                esParentesis = true
            }
        }
        parCierra.setOnClickListener {
            if (contPar != 0) {
                contPar--
                result += ")"
                resultado.setText(result)
            }
        }

        //Para los operadores
        mas.setOnClickListener { concatenaOperadores("+") }
        menos.setOnClickListener { concatenaOperadores("-") }
        multiplicacion.setOnClickListener { concatenaOperadores("*") }
        division.setOnClickListener { concatenaOperadores("/") }

        //Para borrar un solo caracter
        cambio.setOnClickListener {
            if (!result.isEmpty()) {
                when (result[result.length - 1]) {
                    '+', '-', '*', '/' -> esOperador("nada")
                    '(' -> contPar--
                    ')' -> contPar++
                    '.' -> comPunto = false
                    else -> esOperador("nada")
                }
                result = result.substring(0, result.length - 1)
                if (result.isEmpty()) resultado.setText("0")
                else {
                    resultado.setText(result)
                    comPunto = compruebaPunto()
                }
            }
        }
    }

    private fun concatenaOperadores(valor: String) {
        if (!result.isEmpty() && !compOper && !esParentesis) {
            result += valor
            resultado.setText(result)
            esOperador(valor)
        } else if (compOper) {
            result = result.substring(0, result.length - 1) + valor
            resultado.setText(result)
            esOperador(valor)
        }
        comPunto = false
    }

    private fun compruebaPunto(): Boolean {
        for (contador in (result.length - 1) downTo 0) {
            when (result[contador]) {
                '+', '-', '/', '*', '(', ')' -> return false
                '.' -> return true
            }
        }
        return false
    }

    private fun concatenaNumeros(valor: String) {
        result += valor
        resultado.setText(result)
        esOperador("nada")
        esParentesis = false
    }

    private fun esOperador(valor: String) {
        when (valor) {
            "nada" -> {
                compOper = false
                esMas = false
                esMenos = false
                esMultiplicacion = false
                esDivision = false
            }

            "+" -> {
                compOper = true
                esMas = true
                esMenos = false
                esMultiplicacion = false
                esDivision = false
            }

            "-" -> {
                compOper = true
                esMas = false
                esMenos = true
                esMultiplicacion = false
                esDivision = false
            }

            "/" -> {
                compOper = true
                esMas = false
                esMenos = false
                esMultiplicacion = false
                esDivision = true
            }

            "*" -> {
                compOper = true
                esMas = false
                esMenos = false
                esMultiplicacion = true
                esDivision = false
            }
        }
    }

    private fun initComponents() {
        resultado = findViewById(R.id.resultado)
        igual = findViewById(R.id.igual)
        menos = findViewById(R.id.menos)
        mas = findViewById(R.id.mas)
        multiplicacion = findViewById(R.id.multiplicacion)
        division = findViewById(R.id.division)
        punto = findViewById(R.id.punto)
        cambio = findViewById(R.id.cambio)
        cero = findViewById(R.id.cero)
        uno = findViewById(R.id.uno)
        dos = findViewById(R.id.dos)
        tres = findViewById(R.id.tres)
        cuatro = findViewById(R.id.cuatro)
        cinco = findViewById(R.id.cinco)
        seis = findViewById(R.id.seis)
        siete = findViewById(R.id.siete)
        ocho = findViewById(R.id.ocho)
        nueve = findViewById(R.id.nueve)
        parAbre = findViewById(R.id.parAbre)
        parCierra = findViewById(R.id.parCierra)
        borrar = findViewById(R.id.borrar)
    }

    private fun resolver(): String = result
}