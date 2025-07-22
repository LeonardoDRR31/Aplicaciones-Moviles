package com.example.cuchareable.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cuchareable.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        updateCurrentUser()
    }

    private fun updateCurrentUser() {
        val currentUser = auth.currentUser
        _user.value = currentUser?.let {
            User(
                uid = it.uid,
                email = it.email ?: "",
                displayName = it.displayName ?: "",
                isAuthenticated = true
            )
        }
    }

    fun signOut() {
        auth.signOut()
        _user.value = null
    }

    fun isUserAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentFirebaseUser(): FirebaseUser? {
        return auth.currentUser
    }
}
