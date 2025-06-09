package com.ingeweek.ui.seminarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingeweek.data.model.Seminario
import com.ingeweek.databinding.ItemSeminarioBinding

class SeminariosAdapter : RecyclerView.Adapter<SeminariosAdapter.SeminarioViewHolder>() {

    private var seminariosList: List<Seminario> = emptyList()

    fun submitList(list: List<Seminario>) {
        seminariosList = list
        notifyDataSetChanged()
    }

    inner class SeminarioViewHolder(private val binding: ItemSeminarioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Seminario) {
            binding.textTema.text = item.tema
            binding.textPonente.text = item.ponente
            binding.textHorario.text = item.horario
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeminarioViewHolder {
        val binding = ItemSeminarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeminarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeminarioViewHolder, position: Int) {
        holder.bind(seminariosList[position])
    }

    override fun getItemCount(): Int = seminariosList.size
}
