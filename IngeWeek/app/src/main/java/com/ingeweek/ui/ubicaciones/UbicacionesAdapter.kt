package com.ingeweek.ui.ubicaciones

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingeweek.data.model.Ubicacion
import com.ingeweek.databinding.ItemUbicacionesBinding

class UbicacionesAdapter : RecyclerView.Adapter<UbicacionesAdapter.UbicacionViewHolder>() {

    private var ubicacionesList: List<Ubicacion> = emptyList()

    fun submitList(list: List<Ubicacion>) {
        ubicacionesList = list
        notifyDataSetChanged()
    }

    inner class UbicacionViewHolder(private val binding: ItemUbicacionesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Ubicacion) {
            binding.textNombreUbicacion.text = item.nombre
            binding.textDireccionUbicacion.text = item.direccion
            binding.textDescripcionUbicacion.text = item.descripcion
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UbicacionViewHolder {
        val binding = ItemUbicacionesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UbicacionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UbicacionViewHolder, position: Int) {
        holder.bind(ubicacionesList[position])
    }

    override fun getItemCount(): Int = ubicacionesList.size
}
