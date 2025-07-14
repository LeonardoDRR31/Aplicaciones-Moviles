package com.example.loginconlivedata.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class LoginViewModel : ViewModel() {
    val loginState = MutableLiveData<String>()
    val emailValidationState = MutableLiveData<String>()

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun login(email: String, password: String) {
        if (!email.endsWith("@uns.edu.pe")) {
            loginState.value = "El correo debe terminar en @uns.edu.pe"
            return
        }
        if (password.isBlank()) {
            loginState.value = "La contraseña no puede estar vacía"
            return
        }

        val docRef = db.collection("usuarios").document(email)

        docRef.get().addOnSuccessListener { doc ->
            if (!doc.exists()) {
                loginState.value = "El ID no está registrado"
                return@addOnSuccessListener
            }

            // 🔐 Intentar login, incluso si estaba bloqueado (por si ya cambió contraseña)
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    // 🔓 Si login exitoso → limpiar el bloqueo y los intentos
                    val reinicio = mapOf(
                        "failedAttempts" to 0,
                        "bloqueado" to false
                    )
                    docRef.set(reinicio, SetOptions.merge())
                        .addOnSuccessListener {
                            loginState.value = "Login exitoso"
                        }
                        .addOnFailureListener {
                            loginState.value = "Error al restablecer estado de la cuenta"
                        }
                }
                .addOnFailureListener {
                    // 😵 Falló el login → leer intento actual y actualizar
                    val currentAttempts = (doc.getLong("failedAttempts") ?: 0).toInt()
                    val nuevosIntentos = currentAttempts + 1

                    val updates = mutableMapOf<String, Any>(
                        "failedAttempts" to nuevosIntentos
                    )
                    if (nuevosIntentos >= 3) {
                        updates["bloqueado"] = true
                    }

                    docRef.update(updates)
                        .addOnSuccessListener {
                            if (nuevosIntentos >= 3) {
                                loginState.value = "Cuenta bloqueada. Debes restablecer tu contraseña"
                            } else {
                                loginState.value = "Contraseña incorrecta ($nuevosIntentos/3)"
                            }
                        }
                        .addOnFailureListener {
                            loginState.value = "Contraseña incorrecta ($nuevosIntentos/3)" // fallback
                        }
                }

        }.addOnFailureListener {
            loginState.value = "Error al verificar en Firestore"
        }
    }

    fun verificarCorreoEnFirestore(email: String) {
        if (email.isBlank()) {
            emailValidationState.value = ""
            return
        }

        db.collection("usuarios").document(email).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    emailValidationState.value = "Correo válido"
                } else {
                    emailValidationState.value = "Este correo no está registrado"
                }
            }
            .addOnFailureListener {
                emailValidationState.value = "Error al validar correo"
            }
    }
}
