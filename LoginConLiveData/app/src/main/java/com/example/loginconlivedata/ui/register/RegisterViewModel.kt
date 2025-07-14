package com.example.loginconlivedata.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {
    val registerState = MutableLiveData<String>()
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    val correoValidoState = MutableLiveData<String>()

    fun validarCorreoDominio(email: String) {
        if (email.isBlank()) {
            correoValidoState.value = ""
            return
        }

        correoValidoState.value = if (email.endsWith("@uns.edu.pe")) {
            "Correo válido"
        } else {
            "El correo debe terminar en @uns.edu.pe"
        }
    }
    fun register(email: String, password: String, nombre: String) {
        if (!email.endsWith("@uns.edu.pe")) {
            registerState.value = "Correo debe terminar en @uns.edu.pe"
            return
        }
        if (nombre.isBlank()) {
            registerState.value = "El nombre no puede estar vacío"
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val usuario = hashMapOf(
                    "nombre" to nombre,
                    "creadoEn" to FieldValue.serverTimestamp()
                )
                db.collection("usuarios").document(email)
                    .set(usuario)
                    .addOnSuccessListener {
                        registerState.value = "Registro exitoso"
                    }
                    .addOnFailureListener {
                        registerState.value = "Error guardando usuario"
                    }
            }
            .addOnFailureListener { exc ->
                registerState.value = "Error: ${exc.localizedMessage}"
            }
    }
}
