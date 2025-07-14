package com.example.loginconlivedata.dashboard

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.loginconlivedata.MainActivity
import com.example.loginconlivedata.R
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        val animView = findViewById<LottieAnimationView>(R.id.lottieAnim)

        // Animación al cargar el TextView
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        welcomeText.startAnimation(slideUp)

        // Escuchar cambios del ViewModel
        viewModel.nombreUsuario.observe(this) { saludo ->
            welcomeText.text = saludo
        }

        viewModel.cargarNombreUsuario()

        // Acción logout
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Animación con salida
        animView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                animView.animate()
                    .translationXBy(500f)
                    .alpha(0f)
                    .setDuration(800)
                    .start()
            }
        })
    }
}
