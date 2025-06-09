package com.ingeweek.ui.ubicaciones

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ingeweek.data.model.Ubicacion

class UbicacionesViewModel : ViewModel() {

    private val _listaUbicaciones = MutableLiveData<List<Ubicacion>>()
    val listaUbicaciones: LiveData<List<Ubicacion>> get() = _listaUbicaciones

    init {
        cargarUbicaciones()
    }

    private fun cargarUbicaciones() {
        _listaUbicaciones.value = listOf(
            Ubicacion(1, "Auditorio Principal", "Av. Universitaria 123", "Lugar para la ceremonia de apertura"),
            Ubicacion(2, "Laboratorio de Robótica", "Edificio C - Piso 2", "Zona para competencias de robótica"),
            Ubicacion(3, "Sala de Conferencias", "Edificio A - Piso 1", "Seminarios y talleres")
        )
    }
}
