package com.example.seguridad_priv_a

import android.content.Context
import android.util.Base64
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SecurityAuditManager(private val context: Context) {

    private val eventLog = mutableListOf<AuditEvent>()
    private val accessTimestamps = mutableMapOf<String, MutableList<Long>>()  // por clave
    private val rateLimitConfig = mapOf(
        "PERMISSION_REQUEST" to 5,  // 5 por minuto
        "SECURE_OPERATION" to 3     // 3 por minuto
    )

    private val alertListeners = mutableListOf<(String) -> Unit>()

    private val secretKey by lazy {
        val rawKey = ByteArray(32)
        SecureRandom().nextBytes(rawKey)
        SecretKeySpec(rawKey, "HmacSHA256")
    }

    fun registerAlertListener(listener: (String) -> Unit) {
        alertListeners.add(listener)
    }

    fun logEvent(type: String, message: String) {
        val timestamp = System.currentTimeMillis()
        eventLog.add(AuditEvent(timestamp, type, message))
        trackAccess(type, timestamp)
    }

    private fun trackAccess(type: String, timestamp: Long) {
        val now = System.currentTimeMillis()
        val list = accessTimestamps.getOrPut(type) { mutableListOf() }

        // Limpiar accesos viejos (más de 1 minuto)
        list.removeAll { now - it > 60_000 }
        list.add(timestamp)

        // Detección de actividad sospechosa
        val limit = rateLimitConfig[type] ?: return
        if (list.size > limit) {
            val alertMsg = "⚠️ Actividad sospechosa: $type superó el límite de $limit/minuto"
            logEvent("ALERT", alertMsg)
            notifyAlert(alertMsg)
        }
    }

    private fun notifyAlert(msg: String) {
        alertListeners.forEach { it(msg) }
        Log.w("SecurityAudit", msg)
    }

    fun isRateLimited(type: String): Boolean {
        val now = System.currentTimeMillis()
        val list = accessTimestamps.getOrPut(type) { mutableListOf() }
        list.removeAll { now - it > 60_000 }

        val limit = rateLimitConfig[type] ?: return false
        return list.size >= limit
    }

    fun exportLogsAsSignedJson(): String {
        val jsonArray = JSONArray()
        eventLog.forEach {
            jsonArray.put(it.toJson())
        }

        val jsonObject = JSONObject()
        jsonObject.put("events", jsonArray)

        val signature = signJson(jsonArray.toString())
        jsonObject.put("signature", signature)

        return jsonObject.toString(4)
    }

    private fun signJson(content: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(secretKey)
        val hmacBytes = mac.doFinal(content.toByteArray())
        return Base64.encodeToString(hmacBytes, Base64.NO_WRAP)
    }

    fun clearLogs() {
        eventLog.clear()
        accessTimestamps.clear()
    }

    data class AuditEvent(
        val timestamp: Long,
        val type: String,
        val message: String
    ) {
        fun toJson(): JSONObject {
            return JSONObject().apply {
                put("timestamp", timestamp)
                put("type", type)
                put("message", message)
            }
        }
    }
}
