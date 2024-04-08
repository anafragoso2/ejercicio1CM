package com.example.ejercicio1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio1.databinding.ActivityMainBinding
import com.example.ejercicio1.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombre")?: "):"
        val exito = intent.getBooleanExtra("exito",false)
        val montoAPagar = intent.getIntExtra("montoAPagar",0)
        val correo = intent.getStringExtra("correo")?: "):"
        val tipoTarjeta = intent.getStringExtra("tipoTarjeta")?: "):"

        if(exito) {
            binding.tvMonto.visibility = View.VISIBLE
            binding.tvMontoAPagar.visibility = View.VISIBLE
            binding.tvNombre.visibility = View.VISIBLE
            binding.tvCorreo.visibility = View.VISIBLE
            binding.tvTipoTarjeta.visibility = View.VISIBLE

            binding.tvOperacion.text = "Operación Realizada Correctamente"
            binding.tvTryAgain.visibility = View.GONE
            binding.tvMontoAPagar.text = "$"+montoAPagar+".00"
            binding.tvNombre.text = nombre
            binding.tvCorreo.text = correo
            binding.tvTipoTarjeta.text = tipoTarjeta
        } else {
            binding.tvOperacion.text = "Operación Fallida"
            binding.tvTryAgain.visibility = View.VISIBLE
            binding.tvMonto.visibility = View.GONE
            binding.tvMontoAPagar.visibility = View.GONE
            binding.tvNombre.visibility = View.GONE
            binding.tvCorreo.visibility = View.GONE
            binding.tvTipoTarjeta.visibility = View.GONE
        }

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}