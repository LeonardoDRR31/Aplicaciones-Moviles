package com.example.app_s10

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GamesListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GameAdapter
    private val gameList = mutableListOf<Game>()

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)

        recyclerView = findViewById(R.id.recyclerViewGames)
        val btnBack = findViewById<Button>(R.id.btnBack)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GameAdapter(gameList)
        recyclerView.adapter = adapter

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val currentUser = auth.currentUser
        if (currentUser == null) {
            finish()
            return
        }

        val userId = currentUser.uid

        database.child("games").child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newGames = mutableListOf<Game>()
                    for (gameSnap in snapshot.children) {
                        val game = gameSnap.getValue(Game::class.java)
                        game?.let { newGames.add(it) }
                    }
                    adapter.updateGames(newGames)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@GamesListActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })

        btnBack.setOnClickListener {
            finish()
        }
    }
}