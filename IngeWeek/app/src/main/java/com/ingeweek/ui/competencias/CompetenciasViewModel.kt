package com.ingeweek.ui.competencias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingeweek.data.model.Competencia

class CompetenciasViewModel : ViewModel() {

    private val _listaCompetencias = MutableLiveData<List<Competencia>>()
    val listaCompetencias: LiveData<List<Competencia>> get() = _listaCompetencias

    init {
        cargarCompetencias()
    }

    private fun cargarCompetencias() {
        _listaCompetencias.value = listOf(
            Competencia(1, "Concurso de Programación", "Software", "09:00 AM"),
            Competencia(2, "Torneo de Drones", "Robótica", "11:00 AM"),
            Competencia(3, "Hackathon", "Innovación", "02:00 PM")
        )
    }
}
