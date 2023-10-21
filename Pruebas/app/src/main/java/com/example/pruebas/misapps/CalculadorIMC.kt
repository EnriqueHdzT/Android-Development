package com.example.pruebas.misapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.pruebas.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class CalculadorIMC : AppCompatActivity() {
    private lateinit var viewFamale: CardView
    private lateinit var viewMale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvPeso: TextView
    private lateinit var btnMasPeso: FloatingActionButton
    private lateinit var btnMenosPeso: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var btnMasEdad: FloatingActionButton
    private lateinit var btnMenosEdad: FloatingActionButton
    private lateinit var calcular: Button

    private var isFemaleSelected: Boolean = false
    private var isMaleSelected: Boolean = true
    private var edad: Int = 20
    private var peso: Int = 60
    private var altura: Int = 120

    companion object{
        const val IMC_key = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculador_imc)
        initComponents()
        initLiseners()
        initUI()
    }

    private fun initComponents() {
        viewFamale = findViewById(R.id.viewFemale)
        viewMale = findViewById(R.id.viewMale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvPeso = findViewById(R.id.tvPeso)
        btnMasPeso = findViewById(R.id.btnMasPeso)
        btnMenosPeso = findViewById(R.id.btnMenosPeso)
        tvEdad = findViewById(R.id.tvEdad)
        btnMasEdad = findViewById(R.id.btnMasEdad)
        btnMenosEdad = findViewById(R.id.btnMenosEdad)
        calcular = findViewById(R.id.calcular)
    }

    private fun initLiseners() {
        viewFamale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            altura = df.format(value).toInt()
            tvHeight.text = "$altura cm"
        }

        btnMasPeso.setOnClickListener {
            peso += 1
            changePeso()
        }

        btnMenosPeso.setOnClickListener {
            peso -= 1
            changePeso()
        }

        btnMasEdad.setOnClickListener {
            edad += 1
            changeEdad()
        }

        btnMenosEdad.setOnClickListener {
            edad -= 1
            changeEdad()
        }

        calcular.setOnClickListener {
            val result = calculaIMC()
            viewToResult(result)
        }
    }

    private fun viewToResult(result: Double){
        val intent = Intent(this, ResultadoIMCActivity::class.java)
        intent.putExtra(IMC_key, result)
        startActivity(intent)
    }

    private fun calculaIMC():Double{
        val df = DecimalFormat("#.##")
        val imc = peso / (altura.toDouble()/100 * altura.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun changeEdad() {
        tvEdad.text = edad.toString()
    }

    private fun changePeso() {
        tvPeso.text = peso.toString()
    }

    private fun changeGender() {
        isFemaleSelected = !isFemaleSelected
        isMaleSelected = !isMaleSelected
    }

    private fun setGenderColor() {
        viewFamale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        changeEdad()
        changePeso()
    }
}