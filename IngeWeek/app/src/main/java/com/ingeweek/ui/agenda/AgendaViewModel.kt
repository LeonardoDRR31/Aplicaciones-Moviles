package com.ingeweek.ui.agenda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingeweek.data.model.Agenda

class AgendaViewModel : ViewModel() {

    private val _listaAgenda = MutableLiveData<List<Agenda>>()
    val listaAgenda: LiveData<List<Agenda>> get() = _listaAgenda

    init {
        cargarAgenda()
    }

    private fun cargarAgenda() {
        _listaAgenda.value = listOf(
            Agenda(1, "Ceremonia de apertura", "08:30 AM", "Inicio oficial del evento"),
            Agenda(2, "Concurso de Robótica", "10:00 AM", "Competencia interfacultades"),
            Agenda(3, "Charla de Ingeniería Sustentable", "12:00 PM", "Ponente: Ing. García")
        )
    }
}
