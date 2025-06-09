package com.ingeweek

import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ingeweek.databinding.ActivityMainBinding
import com.ingeweek.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.toolbar == null) {
            Log.e("MainActivity", "toolbar es null!")
        } else {
            setSupportActionBar(binding.toolbar)
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        if (binding.bottomNavigation == null) {
            Log.e("MainActivity", "bottomNavigation es null!")
        } else {
            binding.bottomNavigation.setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = destination.label
        }

        onBackPressedDispatcher.addCallback(this) {
            val navController = findNavController(R.id.nav_host_fragment)
            if (!navController.navigateUp()) {
                // Si no se puede navegar hacia atrás, cerramos la app
                finish()
            }
        }

    }

}
