package com.example.dune

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitaAdapter(val citaModel:List<Cita>):RecyclerView.Adapter<CitaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cita, parent, false)
        return CitaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citaModel.size
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        return holder.bindView(citaModel[position])
    }
}

class CitaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val tvCita: TextView = itemView.findViewById(R.id.tvCita)
    private val tvAutor: TextView = itemView.findViewById(R.id.tvAutor)

    fun bindView(citaModel: Cita){
        tvCita.text = citaModel.cita
        tvAutor.text = citaModel.autor
    }
}