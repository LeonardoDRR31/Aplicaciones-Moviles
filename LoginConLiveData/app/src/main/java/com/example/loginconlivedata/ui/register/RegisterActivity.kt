package com.example.loginconlivedata.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.loginconlivedata.R
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.jvm.java

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(saved: Bundle?) {
        super.onCreate(saved)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        val email = findViewById<EditText>(R.id.emailInput)
        val password = findViewById<EditText>(R.id.passwordInput)
        val nombre = findViewById<EditText>(R.id.nameInput)
        val button = findViewById<Button>(R.id.registerButton)
        val status = findViewById<TextView>(R.id.statusText)
        val correoStatus = findViewById<TextView>(R.id.emailStatus)

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.validarCorreoDominio(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        viewModel.correoValidoState.observe(this) {
            correoStatus.text = it
        }
        viewModel.registerState.observe(this) {
            status.text = it
            if (it == "Registro exitoso") {
                finish()  // Regresa al login
            }
        }

        button.setOnClickListener {
            viewModel.register(
                email.text.toString(),
                password.text.toString(),
                nombre.text.toString()
            )
        }
    }
}

