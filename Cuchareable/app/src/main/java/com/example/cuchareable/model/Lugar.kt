package com.example.cuchareable.model

import com.google.firebase.firestore.GeoPoint

data class Lugar(
    val id: String = "",              // ID generado por Firestore
    val name: String = "",            // Nombre del lugar
    val description: String = "",
    val categoryId: String = "",      // Relación con Category.id
    val location: GeoPoint = GeoPoint(0.0, 0.0), // Lat/Lng
    val imageUrl: String = "",        // Foto principal
    val createdBy: String = "",       // UID del usuario creador
    val likedBy: List<String> = emptyList() // Lista de UIDs
)