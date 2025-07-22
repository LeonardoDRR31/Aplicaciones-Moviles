package com.example.cuchareable.model

data class User(
    val uid: String = "",         // ID de Firebase Auth
    val email: String = "",
    val displayName: String = "", // Nombre mostrado
    val photoUrl: String? = null, // URL de foto (nullable)
    val isAuthenticated: Boolean = false
)