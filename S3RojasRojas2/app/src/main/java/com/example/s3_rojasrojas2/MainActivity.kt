package com.example.s3_rojasrojas2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numero1 = findViewById<EditText>(R.id.numero1)
        val numero2 = findViewById<EditText>(R.id.numero2)
        val spinner = findViewById<Spinner>(R.id.spOperaciones)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val resultado = findViewById<TextView>(R.id.resultado)

        // Opciones del spinner
        val operaciones = arrayOf("Sumar", "Restar", "Multiplicar", "Dividir")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operaciones)

        btnCalcular.setOnClickListener {
            val n1 = numero1.text.toString().toDoubleOrNull()
            val n2 = numero2.text.toString().toDoubleOrNull()
            val operacion = spinner.selectedItem.toString()

            if (n1 == null || n2 == null) {
                Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultadoCalculado = when (operacion) {
                "Sumar" -> n1 + n2
                "Restar" -> n1 - n2
                "Multiplicar" -> n1 * n2
                "Dividir" -> {
                    if (n2 == 0.0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    } else n1 / n2
                }
                else -> 0.0
            }

            resultado.text = "Resultado: $resultadoCalculado"
        }
    }
}
