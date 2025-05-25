package com.example.clinicarobles

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicarobles.adapter.EspecialidadAdapter
import com.example.clinicarobles.data.EspecialidadesData
import com.example.clinicarobles.model.Especialidad

class EspecialidadesActivity : AppCompatActivity() {

    private lateinit var adapter: EspecialidadAdapter
    private lateinit var listaOriginal: List<Especialidad>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especialidades)

        val recycler = findViewById<RecyclerView>(R.id.recyclerEspecialidades)
        val searchView = findViewById<SearchView>(R.id.searchViewEspecialidad)

        listaOriginal = EspecialidadesData.listaEspecialidades
        adapter = EspecialidadAdapter(this, listaOriginal)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtro = newText?.lowercase() ?: ""
                val listaFiltrada = listaOriginal.filter {
                    it.nombre.lowercase().contains(filtro)
                }
                adapter.actualizarLista(listaFiltrada)
                return true
            }
        })
    }
}
