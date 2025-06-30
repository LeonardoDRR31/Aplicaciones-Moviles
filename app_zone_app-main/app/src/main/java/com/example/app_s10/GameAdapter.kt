package com.example.app_s10

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class GameAdapter(private var games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvGameTitle)
        val tvGenre: TextView = itemView.findViewById(R.id.tvGameGenre)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.tvTitle.text = game.title
        holder.tvGenre.text = game.genre
        holder.ratingBar.rating = game.rating

        val context = holder.itemView.context

        // Click normal: editar
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditGameActivity::class.java).apply {
                putExtra("GAME_ID", game.id)
                putExtra("GAME_TITLE", game.title)
                putExtra("GAME_GENRE", game.genre)
                putExtra("GAME_RATING", game.rating)
            }
            context.startActivity(intent)
        }

        // Long click: eliminar
        holder.itemView.setOnLongClickListener {
            // Mostrar confirmación
            androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Eliminar juego")
                .setMessage("¿Estás seguro de que quieres eliminar \"${game.title}\"?")
                .setPositiveButton("Eliminar") { _, _ ->
                    deleteGame(game, holder.itemView.context)
                }
                .setNegativeButton("Cancelar", null)
                .show()
            true
        }
    }



    override fun getItemCount() = games.size

    fun updateGames(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }
    private fun deleteGame(game: Game, context:Context) {
        val userId = game.userId
        val gameId = game.id
        if (userId.isEmpty() || gameId.isEmpty()) {
            return
        }

        val database = com.google.firebase.database.FirebaseDatabase.getInstance().reference
        database.child("games").child(userId).child(gameId)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Juego eliminado",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(
                    context,
                    "Error al eliminar: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


}