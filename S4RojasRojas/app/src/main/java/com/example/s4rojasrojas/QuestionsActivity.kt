package com.example.s4rojasrojas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuestionsActivity : AppCompatActivity() {

    private var currentQuestion = 0
    private var score = 0

    private val preguntas = PreguntaRepositorio.preguntas

    private lateinit var questionText: TextView
    private lateinit var optionsGroup: RadioGroup
    private lateinit var nextButton: Button
    private val userAnswers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        questionText = findViewById(R.id.questionText)
        optionsGroup = findViewById(R.id.optionsGroup)
        nextButton = findViewById(R.id.nextButton)
        loadNextQuestion()

        nextButton.setOnClickListener {
            val selectedId = optionsGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Determinar cuál opción fue seleccionada
            val selectedAnswer = when (selectedId) {
                R.id.option1 -> 0
                R.id.option2 -> 1
                R.id.option3 -> 2
                R.id.option4 -> 3
                else -> -1
            }

            if (selectedAnswer != -1) {
                userAnswers.add(selectedAnswer)
            }

            // Comprobar si la respuesta es correcta
            if (selectedAnswer == preguntas[currentQuestion].indiceCorrecto) {
                score++
            }
            currentQuestion++  // Incrementar la pregunta

            optionsGroup.clearCheck()

            // Cargar la siguiente pregunta si hay más
            if (currentQuestion < preguntas.size) {
                loadNextQuestion()
            } else {
                showResults()  // Cuando ya no hay más preguntas, muestra los resultados
            }
        }

    }
    private fun loadNextQuestion() {
        val currentPregunta = preguntas[currentQuestion]
        questionText.text = currentPregunta.enunciado

        // Limpiar las opciones anteriores antes de asignar las nuevas
        optionsGroup.clearCheck()

        // Asegurarse de que siempre haya 4 opciones para cada pregunta
        for (i in currentPregunta.opciones.indices) {
            val option = optionsGroup.getChildAt(i) as RadioButton
            option.text = currentPregunta.opciones[i]
            option.visibility = View.VISIBLE
        }
        // Hacer invisibles los RadioButtons restantes si hay menos de 4 opciones
        for (i in currentPregunta.opciones.size until 4) {
            val option = optionsGroup.getChildAt(i) as RadioButton
            option.visibility = View.INVISIBLE
        }
    }

    private fun checkAnswer() {
        val selectedId = optionsGroup.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedAnswer = optionsGroup.indexOfChild(findViewById(selectedId))
            userAnswers.add(selectedAnswer)
            if (selectedAnswer == preguntas[currentQuestion].indiceCorrecto) {
                score++
            }
        } else {
            Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show()
            return
        }
        optionsGroup.clearCheck()
    }
    private fun showResults() {
        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("SCORE", score) // Asegúrate de pasar el puntaje correctamente.
        intent.putIntegerArrayListExtra("USER_ANSWERS", ArrayList(userAnswers))
        startActivity(intent)

    }
}






