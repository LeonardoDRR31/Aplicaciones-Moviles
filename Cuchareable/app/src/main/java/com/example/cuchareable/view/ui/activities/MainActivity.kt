package com.example.cuchareable.view.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cuchareable.R
import com.example.cuchareable.databinding.ActivityMainBinding
import com.example.cuchareable.network.FirebaseAuthManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authManager = FirebaseAuthManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_categories -> {
                    navController.navigate(R.id.navigation_categories)
                    true
                }
                R.id.navigation_favorites -> {
                    if (authManager.isUserLoggedIn()) {
                        navController.navigate(R.id.navigation_favorites)
                    } else {
                        // Aquí podrías mostrar un diálogo o redirigir a login
                        showLoginRequiredMessage()
                    }
                    true
                }
                R.id.navigation_add -> {
                    if (authManager.isUserLoggedIn()) {
                        navController.navigate(R.id.navigation_add)
                    } else {
                        showLoginRequiredMessage()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun showLoginRequiredMessage() {
        // Puedes usar Snackbar, Toast o abrir LoginActivity
        // Por ejemplo:
        Toast.makeText(this, "Inicia sesión para acceder", Toast.LENGTH_SHORT).show()
    }
}
