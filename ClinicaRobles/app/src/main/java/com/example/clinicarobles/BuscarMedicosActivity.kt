package com.example.clinicarobles

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicarobles.adapter.MedicoAdapter
import com.example.clinicarobles.data.DataProvider
import com.example.clinicarobles.data.MedicosData
import com.example.clinicarobles.model.Medico

class BuscarMedicosActivity : AppCompatActivity() {

    private lateinit var medicoAdapter: MedicoAdapter
    private lateinit var listaOriginal: List<Medico>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_medicos)

        listaOriginal=DataProvider.medicosFiltrados

        val recycler = findViewById<RecyclerView>(R.id.recyclerMedicos)
        val searchView = findViewById<SearchView>(R.id.searchViewMedicos)

        medicoAdapter = MedicoAdapter(listaOriginal)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = medicoAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtro = newText?.lowercase() ?: ""
                val listaFiltrada = listaOriginal.filter {
                    it.nombre.lowercase().contains(filtro)
                }
                medicoAdapter.actualizarLista(listaFiltrada)
                return true
            }
        })
    }
}
