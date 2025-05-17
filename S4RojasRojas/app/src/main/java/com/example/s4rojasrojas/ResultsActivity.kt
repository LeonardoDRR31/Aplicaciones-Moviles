package com.example.s4rojasrojas
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val score = intent.getIntExtra("SCORE", 0)
        val userAnswers = intent.getIntegerArrayListExtra("USER_ANSWERS") ?: arrayListOf()
        val scoreText = findViewById<TextView>(R.id.scoreText)
        scoreText.text = "Puntaje: $score"

        // Botón de inicio
        val homeButton = findViewById<Button>(R.id.homeButton)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Botón para ver el resumen
        val summaryButton = findViewById<Button>(R.id.summaryButton)
        summaryButton.setOnClickListener {
            val summaryIntent = Intent(this, SummaryActivity::class.java)
            summaryIntent.putExtra("SCORE", score)
            summaryIntent.putIntegerArrayListExtra("USER_ANSWERS", userAnswers)
            startActivity(summaryIntent)
            finish()
        }

    }
}










