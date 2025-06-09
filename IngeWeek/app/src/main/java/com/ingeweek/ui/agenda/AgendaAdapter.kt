package com.ingeweek.ui.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingeweek.data.model.Agenda
import com.ingeweek.databinding.ItemAgendaBinding

class AgendaAdapter : RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    private var agendaList: List<Agenda> = emptyList()

    fun submitList(list: List<Agenda>) {
        agendaList = list
        notifyDataSetChanged()
    }

    inner class AgendaViewHolder(private val binding: ItemAgendaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Agenda) {
            binding.textTitulo.text = item.titulo
            binding.textHora.text = item.hora
            binding.textDescripcion.text = item.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val binding = ItemAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgendaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        holder.bind(agendaList[position])
    }

    override fun getItemCount(): Int = agendaList.size
}
