package com.example.cuchareable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuchareable.model.Lugar
import com.example.cuchareable.network.Callback
import com.example.cuchareable.network.FirebaseAuthManager
import com.example.cuchareable.network.FirestoreService

class LugarViewModel : ViewModel() {

    private val firestoreService = FirestoreService()

    private val _lugares = MutableLiveData<List<Lugar>>()
    val lugares: LiveData<List<Lugar>> get() = _lugares

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchLugares() {
        firestoreService.getPlaces(object : Callback<List<Lugar>> {
            override fun onSuccess(result: List<Lugar>?) {
                _lugares.value = result ?: emptyList()
            }

            override fun onFailed(exception: Exception) {
                _error.value = exception.message
            }
        })
    }

    fun addLugar(lugar: Lugar) {
        firestoreService.addPlace(lugar, object : Callback<Void> {
            override fun onSuccess(result: Void?) {
                fetchLugares()
            }

            override fun onFailed(exception: Exception) {
                _error.value = exception.message
            }
        })
    }

    fun autenticarLugar(placeId: String) {
        firestoreService.authenticatePlace(placeId, object : Callback<Void> {
            override fun onSuccess(result: Void?) {
                fetchLugares()
            }

            override fun onFailed(exception: Exception) {
                _error.value = exception.message
            }
        })
    }

    fun likeLugar(placeId: String) {
        val userId = FirebaseAuthManager.getUserUid() ?: return
        firestoreService.likePlace(placeId, userId, object : Callback<Void> {
            override fun onSuccess(result: Void?) {
                fetchLugares()
            }

            override fun onFailed(exception: Exception) {
                _error.value = exception.message
            }
        })
    }
}
