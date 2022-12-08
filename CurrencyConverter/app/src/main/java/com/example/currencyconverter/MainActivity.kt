package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se coloca como comentario, cambio por usar viewbinding.
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Invocar el método para equivalencia de monedas.
        getCurrencyData().start()
    }

    private fun getCurrencyData(): Thread
    {
        return Thread {
            val url = URL("https://open.er-api.com/v6/latest/mxn")
            val connection = url.openConnection() as HttpsURLConnection

            Log.d("Resultado Petición: ", connection.responseCode.toString())

            if(connection.responseCode == 200) {
                val inputSystem = connection.inputStream
                val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Request::class.java)
                updateUI(request)
                inputStreamReader.close()
                inputSystem.close()
            }
            else {
                binding.textMonedaBase.text = "PROBLEMA EN CONEXIÓN"
            }
        }
    }

    private fun updateUI(request: Request)
    {
        runOnUiThread {
            kotlin.run {
                binding.textUltimActualizacion.text = request.time_last_update_utc
                binding.textMonedaEuro.text = String.format("EUR: %.2f", request.rates.EUR)
                binding.textMonedaDolar.text = String.format("USD: %.2f", request.rates.USD)
                binding.textMonedaLibra.text = String.format("GBP: %.2f", request.rates.GBP)
            }
        }
    }
}