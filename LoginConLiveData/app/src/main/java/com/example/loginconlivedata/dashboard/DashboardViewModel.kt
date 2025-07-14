package com.example.loginconlivedata.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardViewModel : ViewModel() {
    val nombreUsuario = MutableLiveData<String>()
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun cargarNombreUsuario() {
        val email = auth.currentUser?.email ?: return

        db.collection("usuarios").document(email).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val nombre = doc.getString("nombre") ?: "Usuario"
                    nombreUsuario.value = "¡Bienvenido, $nombre!"
                } else {
                    nombreUsuario.value = "¡Bienvenido!"
                }
            }
            .addOnFailureListener {
                nombreUsuario.value = "Error al cargar nombre"
            }
    }
}
