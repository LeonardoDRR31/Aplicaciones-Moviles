package com.ingeweek.ui.seminarios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingeweek.data.model.Seminario

class SeminariosViewModel : ViewModel() {

    private val _listaSeminarios = MutableLiveData<List<Seminario>>()
    val listaSeminarios: LiveData<List<Seminario>> get() = _listaSeminarios

    init {
        cargarSeminarios()
    }

    private fun cargarSeminarios() {
        _listaSeminarios.value = listOf(
            Seminario(1, "Introducción a IA", "Dr. Pérez", "10:00 AM"),
            Seminario(2, "Robótica Avanzada", "Ing. Sánchez", "01:00 PM"),
            Seminario(3, "Energías Renovables", "MSc. Gómez", "03:30 PM")
        )
    }
}
