package com.example.ejercicio1

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio1.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val montoAPagar = Random.nextInt(100, 5001)
        binding.tvMontoAPagar.text = "$"+montoAPagar.toString()+".00"

        var tipoTarjetaSelected = false
        binding.etTipoTarjeta.setOnItemClickListener { parent, view, position, id ->
            tipoTarjetaSelected = true
        }

        var mesSelected = false
        binding.etMes.setOnItemClickListener { parent, view, position, id ->
            mesSelected = true
        }

        var anhoSelected = false
        binding.etAno.setOnItemClickListener { parent, view, position, id ->
            anhoSelected = true
        }

        binding.btnPagar.setOnClickListener {
            val nombre = binding.etNombreTarjetahabiente.text ?: ""
            val numeroTarjeta = binding.etNumeroTarjeta.text ?: ""
            val cvv = binding.etCvv.text ?: ""
            val tipoTarjeta = binding.etTipoTarjeta.text ?: ""

            if (nombre.isNotEmpty() &&
                numeroTarjeta.isNotEmpty() &&
                cvv.isNotEmpty() &&
                tipoTarjetaSelected &&
                mesSelected &&
                anhoSelected &&
                isValidEmail(binding.etCorreo.text.toString())
                ) {

                val operacionExitosa = (Math.random() < 0.75)

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("exito",operacionExitosa)
                intent.putExtra("nombre",nombre.toString())
                intent.putExtra("tipoTarjeta",tipoTarjeta.toString())
                intent.putExtra("montoAPagar",montoAPagar)
                intent.putExtra("correo",binding.etCorreo.text.toString())
                startActivity(intent)

            } else {
                Toast.makeText(this,"Verifique que los datos sean validos",Toast.LENGTH_SHORT).show()
            }
        }

        tipoDeTarjetaSpinner()
        mes()
        anho()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun tipoDeTarjetaSpinner() {
        val tiposDeTarjeta = mutableListOf(
            "Visa",
            "MasterCard",
            "American Express"
        )

        val adapterArray = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            tiposDeTarjeta
        )

        binding.etTipoTarjeta.setAdapter(adapterArray)
    }

    private fun mes() {
        val meses = mutableListOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12"
        )
        val adapterArray = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            meses
        )
        binding.etMes.setAdapter(adapterArray)
    }

    private fun anho() {
        val anhos = mutableListOf(
            "2024",
            "2025",
            "2026",
            "2027",
            "2028",
            "2029",
            "2030"
        )
        val adapterArray = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            anhos
        )
        binding.etAno.setAdapter(adapterArray)
    }
}