package com.example.sharedpreferences

import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextAge: EditText
    private lateinit var spinnerEmailDomain: Spinner
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var imageViewProfile: ImageView
    private lateinit var textViewResult: TextView

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageViewProfile.setImageURI(uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferencesHelper = SharedPreferencesHelper(this)
        initViews()
        setupListeners()
    }

    private fun initViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextAge = findViewById(R.id.editTextAge)
        spinnerEmailDomain = findViewById(R.id.spinnerEmailDomain)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        imageViewProfile = findViewById(R.id.imageViewProfile)
        textViewResult = findViewById(R.id.textViewResult)

        // Adapter del Spinner con dominios desde el object
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            EmailDomains.list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmailDomain.adapter = adapter
    }

    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveUserData()
        }

        buttonLoad.setOnClickListener {
            loadUserData()
        }

        buttonClear.setOnClickListener {
            clearUserData()
        }

        imageViewProfile.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }

    private fun saveUserData() {
        val name = editTextName.text.toString().trim()
        val emailName = editTextEmail.text.toString().trim()
        val domain = spinnerEmailDomain.selectedItem.toString()
        val age = editTextAge.text.toString().trim().toIntOrNull()

        if (name.isEmpty() || emailName.isEmpty() || age == null || age !in 16..120) {
            Toast.makeText(this, "Por favor completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            return
        }

        val fullEmail = "$emailName$domain"

        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, name)
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_EMAIL, fullEmail)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_AGE, age)

        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextAge.text.clear()

        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
    }

    private fun loadUserData() {
        val name = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "No registrado")
        val email = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_EMAIL, "No registrado")
        val age = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_AGE, 0)

        val result = "Nombre: $name\nCorreo: $email\nEdad: $age"
        textViewResult.text = result
    }

    private fun clearUserData() {
        sharedPreferencesHelper.clearAll()
        textViewResult.text = ""
        Toast.makeText(this, "Datos limpiados", Toast.LENGTH_SHORT).show()
    }
}
