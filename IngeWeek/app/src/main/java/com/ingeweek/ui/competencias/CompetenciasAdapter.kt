package com.ingeweek.ui.competencias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingeweek.data.model.Competencia
import com.ingeweek.databinding.ItemCompetenciaBinding

class CompetenciasAdapter : RecyclerView.Adapter<CompetenciasAdapter.CompetenciaViewHolder>() {

    private var competenciasList: List<Competencia> = emptyList()

    fun submitList(list: List<Competencia>) {
        competenciasList = list
        notifyDataSetChanged()
    }

    inner class CompetenciaViewHolder(private val binding: ItemCompetenciaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Competencia) {
            binding.textNombreCompetencia.text = item.nombre
            binding.textDescripcionCompetencia.text = item.descripcion
            binding.textHorario.text = item.horario
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetenciaViewHolder {
        val binding = ItemCompetenciaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetenciaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetenciaViewHolder, position: Int) {
        holder.bind(competenciasList[position])
    }

    override fun getItemCount(): Int = competenciasList.size
}
