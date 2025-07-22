package com.example.cuchareable.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuchareable.databinding.ItemLugarBinding
import com.example.cuchareable.model.Lugar

class LugarAdapter(
    private var lugares: List<Lugar>,
    private val onLugarClick: (Lugar) -> Unit
) : RecyclerView.Adapter<LugarAdapter.LugarViewHolder>() {

    inner class LugarViewHolder(private val binding: ItemLugarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(lugar: Lugar) {
            binding.tvLugarName.text = lugar.name
            binding.tvLugarCategory.text = lugar.categoryId
            binding.root.setOnClickListener { onLugarClick(lugar) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        val binding = ItemLugarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LugarViewHolder(binding)
    }

    override fun getItemCount(): Int = lugares.size

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        holder.bind(lugares[position])
    }

    fun updateList(newLugares: List<Lugar>) {
        lugares = newLugares
        notifyDataSetChanged()
    }
}
