package com.example.cuchareable.network

import com.example.cuchareable.model.Lugar
import com.example.cuchareable.model.Category
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FirestoreService {

    private val firestore = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder()
        .apply {
            isPersistenceEnabled = true
        }
        .build()


    init {
        firestore.firestoreSettings = settings
    }

    fun getCategories(callback: Callback<List<Category>>) {
        firestore.collection("categories")
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(Category::class.java)
                callback.onSuccess(list)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    fun getPlaces(callback: Callback<List<Lugar>>) {
        firestore.collection("places")
            .get()
            .addOnSuccessListener { result ->
                val list = result.toObjects(Lugar::class.java)
                callback.onSuccess(list)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    fun addPlace(place: Lugar, callback: Callback<Void>) {
        firestore.collection("places")
            .add(place)
            .addOnSuccessListener {
                callback.onSuccess(null)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    fun authenticatePlace(placeId: String, callback: Callback<Void>) {
        firestore.collection("places").document(placeId)
            .update("isAuthenticated", true)
            .addOnSuccessListener {
                callback.onSuccess(null)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    fun likePlace(placeId: String, userId: String, callback: Callback<Void>) {
        val placeRef = firestore.collection("places").document(placeId)
        placeRef.update("likedBy", com.google.firebase.firestore.FieldValue.arrayUnion(userId))
            .addOnSuccessListener {
                callback.onSuccess(null)
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }
}
