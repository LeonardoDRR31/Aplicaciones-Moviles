package com.example.s3_rojasrojas3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = findViewById<EditText>(R.id.etUsuario)
        val contrasena = findViewById<EditText>(R.id.etContrasena)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val userInput = usuario.text.toString()
            val passInput = contrasena.text.toString()

            val userValido = "admin"
            val passValida = "123456"

            if (userInput == userValido && passInput == passValida) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                // Aquí puedes pasar a otra actividad si quieres
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
