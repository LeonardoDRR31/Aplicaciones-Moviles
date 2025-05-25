package com.example.s3_rojasrojas1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa los EditText, Button y TextView
        val text1 = findViewById<EditText>(R.id.text1)
        val text2 = findViewById<EditText>(R.id.text2)
        val text3 = findViewById<EditText>(R.id.text3)
        val text4 = findViewById<EditText>(R.id.text4)
        val text5 = findViewById<EditText>(R.id.text5)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val result = findViewById<TextView>(R.id.result)

        // Configurar el botón para que ordene los números cuando se presione
        btnCalcular.setOnClickListener {
            // Obtener los números de los EditText
            val num1 = text1.text.toString().toDoubleOrNull()
            val num2 = text2.text.toString().toDoubleOrNull()
            val num3 = text3.text.toString().toDoubleOrNull()
            val num4 = text4.text.toString().toDoubleOrNull()
            val num5 = text5.text.toString().toDoubleOrNull()

            // Comprobar si todos los números son válidos
            if (num1 != null && num2 != null && num3 != null && num4 != null && num5 != null) {
                // Crear una lista con los números
                val numbers = mutableListOf(num1, num2, num3, num4, num5)

                // Ordenar la lista usando el método de la burbuja
                bubbleSort(numbers)

                // Mostrar el resultado en el TextView
                result.text = "Resultado: ${numbers.joinToString(", ")}"
            } else {
                // Si hay algún error en la entrada, mostrar un mensaje de error
                Toast.makeText(this, "Por favor ingrese números válidos en todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bubbleSort(list: MutableList<Double>) {
        val n = list.size
        for (i in 0 until n) {
            for (j in 0 until n - 1 - i) {
                if (list[j] > list[j + 1]) {
                    // Intercambiar los elementos si están en el orden incorrecto
                    val temp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = temp
                }
            }
        }
    }
}
