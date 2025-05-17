package com.example.s3rojasrojas4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los EditText
        val etNombres = findViewById<EditText>(R.id.etNombres)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etDni = findViewById<EditText>(R.id.etDni)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // Configuramos el botón de registro
        btnRegistrar.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val dni = etDni.text.toString().trim()
            val codigo = etCodigo.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validaciones básicas
            if (nombres.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || codigo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (codigo.length != 10) {
                Toast.makeText(this, "El código debe tener 10 dígitos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si todo es correcto
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

        }
    }
}
