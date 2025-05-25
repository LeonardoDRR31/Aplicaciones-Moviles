package com.example.s4rojasrojas

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        // Recuperar el puntaje y las respuestas del usuario
        val score = intent.getIntExtra("SCORE", 0)
        val userAnswers = intent.getIntegerArrayListExtra("USER_ANSWERS") ?: arrayListOf()

        // Verificación para asegurar que los datos se recibieron correctamente
        Log.d("SummaryActivity", "Puntaje: $score")
        Log.d("SummaryActivity", "Respuestas: $userAnswers")

        // Luego, lo que tienes que hacer es procesar las preguntas
        val preguntas = PreguntaRepositorio.preguntas

        // Mostrar el puntaje
        val scoreText = findViewById<TextView>(R.id.scoreText)
        scoreText.text = "Puntaje: $score"

        // Mostrar el resumen de las respuestas
        val summaryText = findViewById<TextView>(R.id.summaryText)
        val summary = StringBuilder()

        // Mostrar las respuestas seleccionadas por el usuario
        for (i in preguntas.indices) {
            val pregunta = preguntas[i]
            val userAnswerIndex = userAnswers.getOrNull(i) ?: -1
            val correctAnswer = pregunta.opciones[pregunta.indiceCorrecto]
            val userAnswer = if (userAnswerIndex >= 0 && userAnswerIndex < pregunta.opciones.size) {
                pregunta.opciones[userAnswerIndex]
            } else {
                "No respondida"
            }

            val resultText = if (userAnswerIndex == pregunta.indiceCorrecto) "Correcta" else "Incorrecta"

            summary.append("Pregunta ${i + 1}:\n")
            summary.append("${pregunta.enunciado}\n")
            summary.append("Tu respuesta: $userAnswer ($resultText)\n")
            summary.append("Respuesta correcta: $correctAnswer\n\n")


        }

        summaryText.text = summary.toString()
    }
}



