package com.example.sharedpreferences

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    companion object {
        var isChangingTheme = false
    }

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonRegister: Button
    private lateinit var textViewResult: TextView
    private lateinit var textViewVisitCount: TextView
    private lateinit var themeSwitch: Switch
    private var isThemeSwitchTriggered = false
    private lateinit var buttonResetCounter: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferencesHelper = SharedPreferencesHelper(this)
        val isDarkMode = sharedPreferencesHelper.getThemeMode()
        applyTheme(isDarkMode)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupListeners()
        setupThemeSwitchListener()

        if (!isChangingTheme) {
            checkFirstTime()
            incrementVisitCount()
        } else {
            isChangingTheme = false
        }
    }


    private fun initViews() {
        editTextUsername = findViewById(R.id.editTextUsername)
        buttonSave = findViewById(R.id.buttonSave)
        buttonLoad = findViewById(R.id.buttonLoad)
        buttonClear = findViewById(R.id.buttonClear)
        buttonRegister = findViewById(R.id.buttonRegister)
        textViewResult = findViewById(R.id.textViewResult)
        themeSwitch = findViewById(R.id.themeSwitch)
        textViewVisitCount = findViewById(R.id.textViewVisitCount)
        buttonResetCounter = findViewById(R.id.buttonResetCounter)
        themeSwitch.isChecked = sharedPreferencesHelper.getThemeMode()

        // Mostrar las visitas en el TextView
        updateVisitCountDisplay()
    }

    private fun setupListeners() {
        buttonSave.setOnClickListener {
            saveData()
        }

        buttonLoad.setOnClickListener {
            loadData()
        }

        buttonClear.setOnClickListener {
            clearUserDataOnly()
        }

        buttonRegister.setOnClickListener {
            navigateToLogin()
        }
        buttonResetCounter.setOnClickListener {
            resetVisitCount()
        }

    }

    private fun saveData() {
        val username = editTextUsername.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar datos
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, username)
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, (1000..9999).random())

        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
        editTextUsername.setText("")
    }

    private fun loadData() {
        val username =
            sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USERNAME, "Sin nombre")
        val isFirstTime =
            sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        val userId = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        val result =
            "Usuario: $username\nID: $userId\nPrimera vez: ${if (isFirstTime) "Sí" else "No"}"
        textViewResult.text = result
    }

    private fun checkFirstTime() {
        val isFirstTime =
            sharedPreferencesHelper.getBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)

        if (isFirstTime) {
            Toast.makeText(this, "¡Bienvenido por primera vez!", Toast.LENGTH_LONG).show()
            sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, false)
            // Aumentar contador solo la primera vez que se abre la app
            incrementVisitCount()
        }
    }

    private fun setupThemeSwitchListener() {
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            isChangingTheme = true
            sharedPreferencesHelper.saveThemeMode(isChecked)
            applyTheme(isChecked) // Esto reinicia la actividad automáticamente
        }
    }


    private fun applyTheme(isDarkMode: Boolean) {
        // Cambiar el tema directamente sin recrear la actividad
        if (isDarkMode) {
            // Activar el modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Activar el modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    // Función para incrementar el contador de visitas
    private fun incrementVisitCount() {
        val currentVisitCount =
            sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_VISIT_COUNT, 0)
        val newVisitCount = currentVisitCount + 1
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_VISIT_COUNT, newVisitCount)
        updateVisitCountDisplay()
    }

    // Función para actualizar el contador en la UI
    private fun updateVisitCountDisplay() {
        val visitCount = sharedPreferencesHelper.getInt(SharedPreferencesHelper.KEY_VISIT_COUNT, 0)
        textViewVisitCount.text = "Visitas: $visitCount"
    }

    // Función para reiniciar el contador
    private fun resetVisitCount() {
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_VISIT_COUNT, 0)
        updateVisitCountDisplay()  // Actualizar la vista con el nuevo contador
        Toast.makeText(this, "Contador de visitas reiniciado", Toast.LENGTH_SHORT).show()
    }

    private fun clearUserDataOnly() {
        // Borra solo los datos del usuario, NO el contador
        sharedPreferencesHelper.saveString(SharedPreferencesHelper.KEY_USERNAME, "")
        sharedPreferencesHelper.saveBoolean(SharedPreferencesHelper.KEY_IS_FIRST_TIME, true)
        sharedPreferencesHelper.saveInt(SharedPreferencesHelper.KEY_USER_ID, 0)

        editTextUsername.setText("")
        textViewResult.text = ""
        Toast.makeText(this, "Datos de usuario eliminados", Toast.LENGTH_SHORT).show()
    }

}
