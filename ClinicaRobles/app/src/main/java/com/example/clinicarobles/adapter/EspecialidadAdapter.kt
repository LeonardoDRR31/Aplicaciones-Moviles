package com.example.clinicarobles.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicarobles.BuscarMedicosActivity
import com.example.clinicarobles.data.DataProvider
import com.example.clinicarobles.data.MedicosData
import com.example.clinicarobles.databinding.DialogEspecialidadBinding
import com.example.clinicarobles.databinding.ItemEspecialidadBinding
import com.example.clinicarobles.model.Especialidad

class EspecialidadAdapter(
    private val context: Context,
    private var lista: List<Especialidad>
) : RecyclerView.Adapter<EspecialidadAdapter.EspecialidadViewHolder>() {
    fun actualizarLista(nuevaLista:List<Especialidad>){
        lista=nuevaLista
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadViewHolder {
        val binding = ItemEspecialidadBinding.inflate(LayoutInflater.from(context), parent, false)
        return EspecialidadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EspecialidadViewHolder, position: Int) {
        val item = lista[position]
        with(holder.binding) {
            txtNombreEspecialidad.text = item.nombre
            txtMedico.text = item.medico
            txtHorario.text = item.horario
            imgEspecialidad.setImageResource(item.imagenId)

            root.setOnClickListener {
                val dialogBinding = DialogEspecialidadBinding.inflate(LayoutInflater.from(context))
                val dialog = AlertDialog.Builder(context)
                    .setView(dialogBinding.root)
                    .create()

                dialogBinding.imgDetalle.setImageResource(item.imagenId)
                dialogBinding.txtTitulo.text = item.nombre
                dialogBinding.txtDescripcion.text = item.descripcion

                dialogBinding.btnBuscar.setOnClickListener {
                    DataProvider.medicosFiltrados = MedicosData.medicosPorEspecialidad[item.nombre] ?: listOf()
                    val intent = Intent(context, BuscarMedicosActivity::class.java)
                    context.startActivity(intent)
                    dialog.dismiss()
                }

                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int = lista.size

    class EspecialidadViewHolder(val binding: ItemEspecialidadBinding) :
        RecyclerView.ViewHolder(binding.root)
}
