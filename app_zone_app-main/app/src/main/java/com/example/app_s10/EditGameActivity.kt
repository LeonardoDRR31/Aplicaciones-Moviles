package com.example.app_s10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditGameActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etGenre: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSave: Button

    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    private var gameId: String? = null // ID del juego

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game) // Reusa tu layout de agregar

        etTitle = findViewById(R.id.etGameTitle)
        etGenre = findViewById(R.id.etGameGenre)
        ratingBar = findViewById(R.id.ratingBar)
        btnSave = findViewById(R.id.btnSaveGame)

        auth = FirebaseAuth.getInstance()

        // Obtener datos recibidos
        gameId = intent.getStringExtra("GAME_ID")
        val title = intent.getStringExtra("GAME_TITLE") ?: ""
        val genre = intent.getStringExtra("GAME_GENRE") ?: ""
        val rating = intent.getFloatExtra("GAME_RATING", 0f)

        // Prellenar campos
        etTitle.setText(title)
        etGenre.setText(genre)
        ratingBar.rating = rating

        btnSave.text = "Guardar Cambios"

        btnSave.setOnClickListener {
            saveChanges()
        }
    }

    private fun saveChanges() {
        val title = etTitle.text.toString().trim()
        val genre = etGenre.text.toString().trim()
        val rating = ratingBar.rating

        if (title.isEmpty() || genre.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val currentUser = auth.currentUser ?: return
        val userId = currentUser.uid

        val updatedGame = Game(
            id = gameId ?: "",
            title = title,
            genre = genre,
            platform = "",
            rating = rating,
            description = "",
            releaseYear = 0,
            completed = false,
            userId = userId,
            createdAt = System.currentTimeMillis()
        )

        if (gameId != null) {
            database.child("games").child(userId).child(gameId!!).setValue(updatedGame)
                .addOnSuccessListener {
                    Toast.makeText(this, "Juego actualizado", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "No se encontró el ID del juego", Toast.LENGTH_SHORT).show()
        }
    }

}