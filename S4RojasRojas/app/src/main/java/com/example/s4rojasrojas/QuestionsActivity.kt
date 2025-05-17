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

    private val preguntas = listOf(
        Pregunta(
            "¿Cuál de las siguientes es una ventaja de las aplicaciones móviles nativas?",
            listOf(
                "Mayor rendimiento y acceso a características del dispositivo",
                "Compatibilidad con todos los sistemas operativos",
                "No requieren conexión a internet",
                "Son más fáciles de desarrollar que las aplicaciones web"
            ),
            0
        ),
        Pregunta(
            "¿Cuál es la principal diferencia entre una aplicación móvil nativa y una híbrida?",
            listOf(
                "Las aplicaciones nativas funcionan solo en un sistema operativo, mientras que las híbridas funcionan en varios.",
                "Las aplicaciones híbridas siempre requieren una conexión a internet, mientras que las nativas no.",
                "Las aplicaciones híbridas son más caras de desarrollar que las nativas.",
                "Las aplicaciones nativas tienen acceso limitado a los recursos del dispositivo, mientras que las híbridas tienen acceso completo."
            ),
            0
        ),
        Pregunta(
            "¿Qué es una PWA (Progressive Web App)?",
            listOf(
                "Una aplicación web que se instala en el dispositivo como una aplicación nativa",
                "Una aplicación móvil que no necesita conexión a internet",
                "Una aplicación diseñada solo para dispositivos iOS",
                "Un tipo de aplicación que solo funciona en dispositivos Android"
            ),
            0
        ),
        Pregunta(
            "¿Cuál de los siguientes es un beneficio principal de las aplicaciones móviles en comparación con los sitios web móviles?",
            listOf(
                "Las aplicaciones móviles siempre requieren menos recursos",
                "Las aplicaciones móviles pueden ofrecer una experiencia de usuario más fluida y accesible",
                "Las aplicaciones móviles solo pueden funcionar en dispositivos Android",
                "Las aplicaciones móviles nunca necesitan ser actualizadas"
            ),
            1
        ),
        Pregunta(
            "¿Cuál de las siguientes es una característica de las \"notificaciones push\" en las aplicaciones móviles?",
            listOf(
                "Solo se pueden enviar mensajes de texto",
                "Son enviadas a los usuarios incluso cuando la aplicación está cerrada",
                "Solo pueden ser enviadas a través de redes sociales",
                "Son parte de la interfaz gráfica de la aplicación"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es el propósito de una API en el desarrollo de aplicaciones móviles?",
            listOf(
                "Crear la interfaz de usuario",
                "Conectar la aplicación con servicios externos y bases de datos",
                "Asegurar la privacidad de los datos del usuario",
                "Optimizar el rendimiento del dispositivo"
            ),
            1
        ),
        Pregunta(
            "¿Qué es el \"SDK\" en el desarrollo de aplicaciones móviles?",
            listOf(
                "Un lenguaje de programación",
                "Un conjunto de herramientas y bibliotecas para desarrollar aplicaciones",
                "Una plataforma para distribuir aplicaciones",
                "Un tipo de base de datos"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es el propósito de una \"app sandbox\" en los dispositivos móviles?",
            listOf(
                "Mejorar el rendimiento de la aplicación",
                "Aislar y proteger los datos de la aplicación del resto del sistema operativo",
                "Permitir que la aplicación use más recursos del dispositivo",
                "Ejecutar aplicaciones solo en segundo plano"
            ),
            1
        ),
        Pregunta(
            "¿Cuál de las siguientes afirmaciones es cierta sobre las aplicaciones móviles?",
            listOf(
                "Solo las aplicaciones nativas pueden acceder a las cámaras y sensores del dispositivo",
                "Las aplicaciones móviles siempre deben estar conectadas a internet",
                "Las aplicaciones móviles híbridas no pueden utilizar bases de datos locales",
                "Las aplicaciones móviles deben ser descargadas desde la tienda de aplicaciones para funcionar"
            ),
            0
        ),
        Pregunta(
            "¿Qué tecnología permite que una aplicación móvil se sincronice con servicios en la nube?",
            listOf(
                "Bluetooth",
                "Wi-Fi",
                "APIs de nube y servicios web",
                "Sistema operativo"
            ),
            2
        ),
        Pregunta(
            "¿Qué significa \"debugging\" en el desarrollo de aplicaciones móviles?",
            listOf(
                "Agregar nuevas funciones a la aplicación",
                "Eliminar errores y fallos en el código de la aplicación",
                "Publicar la aplicación en la tienda de aplicaciones",
                "Hacer que la aplicación funcione en modo sin conexión"
            ),
            1
        ),
        Pregunta(
            "¿Qué componente del sistema operativo móvil se encarga de gestionar la memoria, las aplicaciones y los recursos del dispositivo?",
            listOf(
                "El núcleo o kernel del sistema operativo.",
                "La interfaz de usuario (UI).",
                "El motor de renderizado.",
                "La base de datos local de la aplicación."
            ),
            0
        ),
        Pregunta(
            "¿Qué tipo de aplicaciones móviles requieren un conjunto completo de permisos para acceder a funcionalidades como la cámara, ubicación, contactos, etc.?",
            listOf(
                "Las aplicaciones web.",
                "Las aplicaciones híbridas.",
                "Las aplicaciones nativas.",
                "Las Progressive Web Apps (PWA)."
            ),
            2
        ),
        Pregunta(
            "¿Cuál es el archivo principal que describe los componentes de una app Android?",
            listOf(
                "build.gradle",
                "AndroidManifest.xml",
                "MainActivity.kt",
                "strings.xml"
            ),
            1
        ),
        Pregunta(
            "¿Qué es una Activity en Android?",
            listOf(
                "Una base de datos local",
                "Una pantalla o interfaz que el usuario puede ver",
                "Un permiso de red",
                "Una herramienta para dibujar gráficos"
            ),
            1
        ),
        Pregunta(
            "¿Qué sistema de diseño de interfaz proporciona Google para Android?",
            listOf(
                "Material Design",
                "Apple UI",
                "Windows Metro",
                "Bootstrap"
            ),
            0
        ),
        Pregunta(
            "¿Qué componente se utiliza para almacenar datos localmente en Android?",
            listOf(
                "RecyclerView",
                "Room Database",
                "ConstraintLayout",
                "Toast"
            ),
            1
        ),
        Pregunta(
            "¿Cuál es una práctica recomendada en el diseño de interfaces móviles?",
            listOf(
                "Colocar todos los botones en la parte superior",
                "Evitar el uso de iconos",
                "Diseñar pensando en la experiencia del usuario (UX)",
                "Usar muchos colores brillantes y saturados"
            ),
            2
        ),
        Pregunta(
            "¿Cuál es la principal desventaja de las aplicaciones móviles nativas frente a las aplicaciones híbridas?",
            listOf(
                "Requieren más tiempo y esfuerzo de desarrollo para cada plataforma",
                "No pueden acceder a los recursos del dispositivo",
                "Son más lentas que las aplicaciones híbridas",
                "No pueden ser distribuidas en tiendas de aplicaciones"
            ),
            0
        )

    )

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






