package com.example.dune

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val onLibrosListener: OnLibrosListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private var titulos = arrayOf("Dune","EL Mesias de Dune", "Hijos de Dune", "Dios Emperador de Dune", "Herejes de Dune", "Casa Capitular Dune", "Citas")
    private var autores = arrayOf("Frank Herbert", "Frank Herbert", "Frank Herbert", "Frank Herbert", "Frank Herbert", "Frank Herbert", "Algunas citas elegidas")
    private var imagenes = intArrayOf(R.drawable.libro01, R.drawable.libro02,R.drawable.libro03,R.drawable.libro04,R.drawable.libro05,R.drawable.libro06,R.drawable.citas)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitulo.text = titulos[i]
        viewHolder.itemAutor.text = autores[i]
        viewHolder.itemImage.setImageResource(imagenes[i])

        //para manejar los clicks sobre los items
        viewHolder.itemView.setOnClickListener {
            onLibrosListener.onLibroItemClicked(i)
        }
    }

    override fun getItemCount(): Int {
        return titulos.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitulo: TextView = itemView.findViewById(R.id.item_titulo)
        var itemAutor: TextView = itemView.findViewById(R.id.item_autor)
    }

}