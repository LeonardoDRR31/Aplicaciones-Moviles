package com.example.seguridad_priv_a.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
//Importaciones necesarias para fortalecer la encriptación
import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


class DataProtectionManager(private val context: Context) {
    
    private lateinit var encryptedPrefs: SharedPreferences
    private lateinit var accessLogPrefs: SharedPreferences
    
    fun initialize() {
        try {
            // Crear o obtener la clave maestra
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
                
            // Crear SharedPreferences encriptado para datos sensibles
            encryptedPrefs = EncryptedSharedPreferences.create(
                context,
                "secure_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            
            // SharedPreferences normal para logs de acceso (no son datos sensibles críticos)
            accessLogPrefs = context.getSharedPreferences("access_logs", Context.MODE_PRIVATE)
            
        } catch (e: Exception) {
            // Fallback a SharedPreferences normales si falla la encriptación
            encryptedPrefs = context.getSharedPreferences("fallback_prefs", Context.MODE_PRIVATE)
            accessLogPrefs = context.getSharedPreferences("access_logs", Context.MODE_PRIVATE)
        }
    }
    
    fun storeSecureData(key: String, value: String) {
        encryptedPrefs.edit().putString(key, value).apply()
        logAccess("DATA_STORAGE", "Dato almacenado de forma segura: $key")
    }
    
    fun getSecureData(key: String): String? {
        val data = encryptedPrefs.getString(key, null)
        if (data != null) {
            logAccess("DATA_ACCESS", "Dato accedido: $key")
        }
        return data
    }
    
    fun logAccess(category: String, action: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logEntry = "$timestamp - $category: $action"
        
        // Obtener logs existentes
        val existingLogs = accessLogPrefs.getString("logs", "") ?: ""
        val newLogs = if (existingLogs.isEmpty()) {
            logEntry
        } else {
            "$existingLogs\\n$logEntry"
        }
        
        // Guardar logs actualizados
        accessLogPrefs.edit().putString("logs", newLogs).apply()
        
        // Limitar el número de logs (mantener solo los últimos 100)
        val logLines = newLogs.split("\\n")
        if (logLines.size > 100) {
            val trimmedLogs = logLines.takeLast(100).joinToString("\\n")
            accessLogPrefs.edit().putString("logs", trimmedLogs).apply()
        }
    }
    
    fun getAccessLogs(): List<String> {
        val logsString = accessLogPrefs.getString("logs", "") ?: ""
        return if (logsString.isEmpty()) {
            emptyList()
        } else {
            logsString.split("\\n").reversed() // Mostrar los más recientes primero
        }
    }
    
    fun clearAllData() {
        // Limpiar datos encriptados
        encryptedPrefs.edit().clear().apply()
        
        // Limpiar logs
        accessLogPrefs.edit().clear().apply()
        
        logAccess("DATA_MANAGEMENT", "Todos los datos han sido borrados de forma segura")
    }
    
    fun getDataProtectionInfo(): Map<String, String> {
        return mapOf(
            "Encriptación" to "AES-256-GCM",
            "Almacenamiento" to "Local encriptado",
            "Logs de acceso" to "${getAccessLogs().size} entradas",
            "Última limpieza" to (getSecureData("last_cleanup") ?: "Nunca"),
            "Estado de seguridad" to "Activo"
        )
    }
    
    fun anonymizeData(data: String): String {
        // Implementación básica de anonimización
        return data.replace(Regex("[0-9]"), "*")
            .replace(Regex("[A-Za-z]{3,}"), "***")
    }
    //Implementación luego de importar lo necesario

    private val HMAC_KEY_PREF = "hmac_key"
    private val LAST_KEY_ROTATION_PREF = "last_key_rotation"

    fun rotateEncryptionKey(): Boolean {
        val lastRotation = encryptedPrefs.getString(LAST_KEY_ROTATION_PREF, null)
        val currentTime = System.currentTimeMillis()
        val thirtyDaysMs = 30L * 24 * 60 * 60 * 1000

        if (lastRotation != null) {
            val lastTime = lastRotation.toLong()
            if ((currentTime - lastTime) < thirtyDaysMs) return false // Aún no toca rotar
        }

        val newSalt = ByteArray(16).apply {
            SecureRandom().nextBytes(this)
        }
        val derivedKey = deriveKey("usuario", newSalt)

        // Simula guardar la nueva clave derivada como HMAC_KEY
        val hmacKeyEncoded = Base64.encodeToString(derivedKey.encoded, Base64.DEFAULT)
        encryptedPrefs.edit()
            .putString(HMAC_KEY_PREF, hmacKeyEncoded)
            .putString(LAST_KEY_ROTATION_PREF, currentTime.toString())
            .apply()

        logAccess("KEY_MANAGEMENT", "Clave de encriptación rotada exitosamente")
        return true
    }
    fun deriveKey(username: String, salt: ByteArray): SecretKeySpec {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(username.toCharArray(), salt, 10000, 256)
        val tmp = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "HmacSHA256")
    }
    fun verifyDataIntegrity(key: String): Boolean {
        val data = getSecureData(key) ?: return false
        val hmacKeyString = encryptedPrefs.getString(HMAC_KEY_PREF, null) ?: return false
        val hmacKey = Base64.decode(hmacKeyString, Base64.DEFAULT)

        val hmac = Mac.getInstance("HmacSHA256")
        hmac.init(SecretKeySpec(hmacKey, "HmacSHA256"))
        val calculated = hmac.doFinal(data.toByteArray())

        val storedHmac = encryptedPrefs.getString("$key:hmac", null) ?: return false
        val storedBytes = Base64.decode(storedHmac, Base64.DEFAULT)

        return MessageDigest.isEqual(calculated, storedBytes)
    }

    fun storeSecureDataWithIntegrity(key: String, value: String) {
        storeSecureData(key, value)

        val hmacKeyString = encryptedPrefs.getString(HMAC_KEY_PREF, null) ?: return
        val hmacKey = Base64.decode(hmacKeyString, Base64.DEFAULT)

        val hmac = Mac.getInstance("HmacSHA256")
        hmac.init(SecretKeySpec(hmacKey, "HmacSHA256"))
        val hmacBytes = hmac.doFinal(value.toByteArray())
        val hmacEncoded = Base64.encodeToString(hmacBytes, Base64.DEFAULT)

        encryptedPrefs.edit().putString("$key:hmac", hmacEncoded).apply()
    }


}