package com.example.cuchareable.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cuchareable.model.Category
import com.example.cuchareable.network.Callback
import com.example.cuchareable.network.FirestoreService

class CategoryViewModel : ViewModel() {

    private val firestoreService = FirestoreService()

    private val _categories = MutableLiveData<List<Category>>()      // Lista completa
    private val _filteredCategories = MutableLiveData<List<Category>>() // Lista filtrada
    val filteredCategories: LiveData<List<Category>> get() = _filteredCategories

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun loadCategories() {
        firestoreService.getCategories(object : Callback<List<Category>> {
            override fun onSuccess(result: List<Category>?) {
                val categoryList = result ?: emptyList()
                _categories.value = categoryList
                _filteredCategories.value = categoryList // Se muestra todo al inicio
            }

            override fun onFailed(exception: Exception) {
                _error.value = exception.message
            }
        })
    }

    fun filterCategories(query: String) {
        val currentList = _categories.value ?: return
        _filteredCategories.value = if (query.isBlank()) {
            currentList
        } else {
            currentList.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }
        }
    }
}
