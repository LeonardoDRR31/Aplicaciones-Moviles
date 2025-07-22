package com.example.cuchareableapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.cuchareableapp.view.ui.activities.MainActivity
import com.example.cuchareableapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Lógica para mostrar el Splash durante unos segundos
        Handler().postDelayed({
            // Navegar a la pantalla principal
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Finaliza la actividad del Splash
        }, 2000) // 2000 ms = 2 segundos
    }
}
