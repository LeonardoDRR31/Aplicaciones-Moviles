package com.example.clinicarobles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicarobles.R
import com.example.clinicarobles.model.Medico

class MedicoAdapter(private var lista: List<Medico>) :
    RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medico, parent, false)
        return MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoViewHolder, position: Int) {
        val medico = lista[position]
        holder.nombre.text = medico.nombre
        holder.correo.text = medico.correo
        holder.telefono.text = medico.telefono
        holder.imagen.setImageResource(medico.imagenId)
    }

    override fun getItemCount(): Int = lista.size

    fun actualizarLista(nuevaLista: List<Medico>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    class MedicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imgMedico)
        val nombre: TextView = itemView.findViewById(R.id.txtNombreMedico)
        val correo: TextView = itemView.findViewById(R.id.txtCorreo)
        val telefono: TextView = itemView.findViewById(R.id.txtTelefono)
    }
}
