package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "AppPreferences"
        const val KEY_USERNAME = "username"
        const val KEY_EMAIL = "email"
        const val KEY_AGE = "age"
        const val KEY_THEME_MODE = "theme_mode"
        const val KEY_IS_FIRST_TIME = "is_first_time"
        const val KEY_USER_ID = "user_id"
        const val KEY_VISIT_COUNT = "visit_count"
    }

    // Guardar el nombre de usuario
    fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    // Obtener el nombre de usuario
    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    // Guardar el estado del tema (oscuro o claro)
    fun saveThemeMode(isDarkMode: Boolean) {
        editor.putBoolean(KEY_THEME_MODE, isDarkMode).apply()
    }

    // Obtener el estado del tema (oscuro o claro)
    fun getThemeMode(): Boolean {
        return sharedPreferences.getBoolean(KEY_THEME_MODE, false)  // Por defecto, modo claro (false)
    }

    // Guardar si es la primera vez que se abre la app
    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    // Obtener el valor booleano de una clave
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Guardar un número entero (ej. Edad)
    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    // Obtener un número entero
    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    // Limpiar todos los datos guardados
    fun clearAll() {
        editor.clear().apply()
    }
}
