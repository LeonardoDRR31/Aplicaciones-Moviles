package com.example.seguridad_priv_a

import android.app.Application
import com.example.seguridad_priv_a.data.DataProtectionManager

class PermissionsApplication : Application() {

    val dataProtectionManager by lazy {
        DataProtectionManager(this)
    }

    val auditManager by lazy {
        SecurityAuditManager(this)
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializar el sistema de protección de datos
        dataProtectionManager.initialize()

        // Log de inicio de aplicación
        dataProtectionManager.logAccess("APPLICATION", "App iniciada")

        // Registrar alertas globales
        auditManager.registerAlertListener { alert ->
            // Aquí podrías mostrar una notificación o reportar el incidente
            android.util.Log.e("ALERTA_GLOBAL", alert)
        }
    }
}
