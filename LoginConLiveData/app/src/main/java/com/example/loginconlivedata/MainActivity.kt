package com.example.loginconlivedata

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loginconlivedata.dashboard.DashboardActivity
import com.example.loginconlivedata.ui.login.LoginViewModel
import com.example.loginconlivedata.ui.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val statusText = findViewById<TextView>(R.id.statusText)
        val goToRegister = findViewById<TextView>(R.id.goToRegister)
        val emailStatus = findViewById<TextView>(R.id.emailStatus)
        val forgotButton = findViewById<Button>(R.id.forgotPasswordButton)

        // Observador de estado de validación de correo
        viewModel.emailValidationState.observe(this) { estado ->
            emailStatus.text = estado
        }

        // TextWatcher para validación de correo en tiempo real
        emailInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.verificarCorreoEnFirestore(s.toString().trim())
            }
        })

        // Observador del estado del login
        viewModel.loginState.observe(this) { estado ->
            statusText.text = estado

            val bloqueada = estado.contains("bloqueada", ignoreCase = true)
            goToRegister.visibility = if (bloqueada) View.GONE else View.VISIBLE

            if (estado == "Login exitoso") {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }


        // Acción de login
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()
            viewModel.login(email, password)

        }

        // Acción de recuperación de contraseña
        forgotButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            if (email.isNotBlank()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        statusText.text = "Correo de recuperación enviado a $email"
                    }
                    .addOnFailureListener {
                        statusText.text = "Error al enviar recuperación: ${it.localizedMessage}"
                    }
            } else {
                statusText.text = "Ingresa tu correo para recuperar la contraseña"
            }
        }

        // Navegar a registro
        goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}
