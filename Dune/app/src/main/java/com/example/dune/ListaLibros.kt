package com.example.dune

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaLibros : ComponentActivity(), OnLibrosListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_libros)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(onLibrosListener = this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onLibroItemClicked(position: Int) {

        when (position) {
            0 -> {
                val libro01 = Intent(this, Libro01::class.java)
                startActivity(libro01)
            }

            1 -> Toast.makeText(this, "Libro: El Mesias de Dune ", Toast.LENGTH_SHORT).show()
            2 -> Toast.makeText(this, "Libro: Hijos de Dune ", Toast.LENGTH_SHORT).show()
            3 -> Toast.makeText(this, "Libro: Dios Emperador de Dune ", Toast.LENGTH_SHORT).show()
            4 -> Toast.makeText(this, "Libro: Herejes de Dune ", Toast.LENGTH_SHORT).show()
            5 -> Toast.makeText(this, "Libro: Casa Capitular Dune ", Toast.LENGTH_SHORT).show()
            6 -> {
                //val citasElegidas = Intent(this, CitasElegidas::class.java)
                //startActivity(citasElegidas)
                Toast.makeText(this, "Citas de los libros", Toast.LENGTH_SHORT).show()
            }
        }
    }
}