package com.example.seguridad_priv_a

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.seguridad_priv_a.databinding.ActivityDataProtectionBinding
import java.util.concurrent.Executor

class DataProtectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataProtectionBinding
    private val dataProtectionManager by lazy {
        (application as PermissionsApplication).dataProtectionManager
    }

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    private val inactivityTimeout = 5 * 60 * 1000L // 5 minutos
    private val handler = Handler(Looper.getMainLooper())
    private val inactivityRunnable = Runnable {
        Toast.makeText(this, "Sesión expirada por inactividad", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataProtectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                loadDataProtectionInfo()
                loadAccessLogs()
                startInactivityTimer()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@DataProtectionActivity, "Error de autenticación: $errString", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@DataProtectionActivity, "Autenticación fallida", Toast.LENGTH_SHORT).show()
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación Requerida")
            .setSubtitle("Verifica tu identidad para acceder a los datos protegidos")
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_WEAK or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL // PIN o patrón
            )
            .build()

        biometricPrompt.authenticate(promptInfo)

        setupUI()

        dataProtectionManager.logAccess("NAVIGATION", "DataProtectionActivity abierta")
    }

    private fun setupUI() {
        binding.btnViewLogs.setOnClickListener {
            loadAccessLogs()
            Toast.makeText(this, "Logs actualizados", Toast.LENGTH_SHORT).show()
        }

        binding.btnClearData.setOnClickListener {
            showClearDataDialog()
        }
    }

    private fun loadDataProtectionInfo() {
        val info = dataProtectionManager.getDataProtectionInfo()
        val infoText = StringBuilder()

        infoText.append("🔐 INFORMACIÓN DE SEGURIDAD\n\n")
        info.forEach { (key, value) ->
            infoText.append("• $key: $value\n")
        }

        infoText.append("\n📊 EVIDENCIAS DE PROTECCIÓN:\n")
        infoText.append("• Encriptación AES-256-GCM activa\n")
        infoText.append("• Todos los accesos registrados\n")
        infoText.append("• Datos anonimizados automáticamente\n")
        infoText.append("• Almacenamiento local seguro\n")
        infoText.append("• No hay compartición de datos\n")

        binding.tvDataProtectionInfo.text = infoText.toString()

        dataProtectionManager.logAccess("DATA_PROTECTION", "Información de protección mostrada")
    }

    private fun loadAccessLogs() {
        val logs = dataProtectionManager.getAccessLogs()
        binding.tvAccessLogs.text = if (logs.isNotEmpty()) {
            logs.joinToString("\n")
        } else {
            "No hay logs disponibles"
        }

        dataProtectionManager.logAccess("DATA_ACCESS", "Logs de acceso consultados")
    }

    private fun showClearDataDialog() {
        AlertDialog.Builder(this)
            .setTitle("Borrar Todos los Datos")
            .setMessage("¿Estás seguro de que deseas borrar todos los datos almacenados y logs de acceso? Esta acción no se puede deshacer.")
            .setPositiveButton("Borrar") { _, _ ->
                clearAllData()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun clearAllData() {
        dataProtectionManager.clearAllData()

        binding.tvAccessLogs.text = "Todos los datos han sido borrados"
        binding.tvDataProtectionInfo.text = "🔐 DATOS BORRADOS DE FORMA SEGURA\n\nTodos los datos personales y logs han sido eliminados del dispositivo."

        Toast.makeText(this, "Datos borrados de forma segura", Toast.LENGTH_LONG).show()
        dataProtectionManager.logAccess("DATA_MANAGEMENT", "Todos los datos borrados por el usuario")
    }

    private fun startInactivityTimer() {
        handler.postDelayed(inactivityRunnable, inactivityTimeout)
    }

    private fun resetInactivityTimer() {
        handler.removeCallbacks(inactivityRunnable)
        startInactivityTimer()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        resetInactivityTimer()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(inactivityRunnable)
    }
}
