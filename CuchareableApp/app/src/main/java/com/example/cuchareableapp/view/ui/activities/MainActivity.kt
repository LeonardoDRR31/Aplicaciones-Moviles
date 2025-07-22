package com.example.cuchareableapp.view.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cuchareableapp.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.location.LocationManager

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest

    // Ubicación por defecto
    private val defaultLocation = LatLng(-9.1221348, -78.5311208)

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar MapView
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // Inicializar servicios de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Configurar LocationRequest
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 10000
            fastestInterval = 5000
        }

        // Configurar LocationCallback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    showUserLocation(location.latitude, location.longitude)
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        checkLocationState()
    }

    private fun checkLocationState() {
        when {
            hasLocationPermission() && isLocationEnabled() -> {
                // Caso ideal: permisos y ubicación activados
                getCurrentLocation()
            }

            hasLocationPermission() && !isLocationEnabled() -> {
                // Permisos concedidos pero ubicación desactivada
                showLocationActivationDialog()
            }

            else -> {
                // Falta permisos o ambos
                requestLocationPermission()
            }
        }
    }

    private fun showLocationActivationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Ubicación desactivada")
            .setMessage("Para mostrar tu ubicación en tiempo real, necesitas activar el servicio de ubicación. ¿Deseas activarlo ahora?")
            .setPositiveButton("Activar") { _, _ ->
                // Mostrar ubicación por defecto mientras el usuario activa manualmente
                showToast("Por favor activa la ubicación en los ajustes de tu dispositivo")
                showDefaultLocation()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                showToast("Mostrando ubicación por defecto")
                showDefaultLocation()
            }
            .setCancelable(false)
            .show()
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )) {
            AlertDialog.Builder(this)
                .setTitle("Permiso requerido")
                .setMessage("La aplicación necesita acceso a tu ubicación para mostrarte en el mapa")
                .setPositiveButton("Entendido") { _, _ ->
                    actuallyRequestPermission()
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    showToast("Mostrando ubicación por defecto")
                    showDefaultLocation()
                }
                .show()
        } else {
            actuallyRequestPermission()
        }
    }

    private fun actuallyRequestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (hasLocationPermission()) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    showUserLocation(location.latitude, location.longitude)
                    startLocationUpdates()
                } else {
                    startLocationUpdates()
                }
            }.addOnFailureListener {
                showToast("Error al obtener ubicación")
                showDefaultLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (hasLocationPermission() && isLocationEnabled()) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun showUserLocation(latitude: Double, longitude: Double) {
        val userLocation = LatLng(latitude, longitude)
        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
        mMap.addMarker(MarkerOptions().position(userLocation).title("Tu ubicación"))
    }

    private fun showDefaultLocation() {
        mMap.clear()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15f))
        mMap.addMarker(MarkerOptions().position(defaultLocation).title("Ubicación por defecto"))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isLocationEnabled()) {
                        getCurrentLocation()
                    } else {
                        showLocationActivationDialog()
                    }
                } else {
                    showToast("Permiso de ubicación denegado. Mostrando ubicación por defecto")
                    showDefaultLocation()
                }
            }
        }
    }

    // Ciclo de vida
    override fun onResume() {
        super.onResume()
        mapView.onResume()
        // Verificar si el usuario activó la ubicación después del diálogo
        if (hasLocationPermission() && isLocationEnabled()) {
            getCurrentLocation()
        }
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        stopLocationUpdates()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        stopLocationUpdates()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}