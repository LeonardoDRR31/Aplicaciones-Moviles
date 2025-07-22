package com.example.cuchareable.model

data class Category(
    val id: String = "",      // ID generado por Firestore
    val name: String = "",    // Ej: "Restaurantes", "Parques"
    val iconUrl: String = ""  // URL de imagen (opcional)
)