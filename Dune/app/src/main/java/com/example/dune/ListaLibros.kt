package com.example.dune

import android.content.Intent
import android.os.Bundle
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
                //a la reseña del libro 01
                val libro01 = Intent(this, Libro01::class.java)
                startActivity(libro01)
            }
            1 -> {
                //a la reseña del libro 02
                val libro02 = Intent(this, Libro02::class.java)
                startActivity(libro02)
            }
            2 -> {
                //a la reseña del libro 03
                val libro03 = Intent(this, Libro03::class.java)
                startActivity(libro03)
            }
            3 -> {
                //a la reseña del libro 04
                val libro04 = Intent(this, Libro04::class.java)
                startActivity(libro04)
            }
            4 ->{
                //a la reseña del libro 05
                val libro05 = Intent(this, Libro05::class.java)
                startActivity(libro05)
            }
            5 -> {
                //a la reseña del libro 06
                val libro06 = Intent(this, Libro06::class.java)
                startActivity(libro06)
            }
            6 -> {
                // a la activity Citas
                val citasElegidas = Intent(this, Citas::class.java)
                startActivity(citasElegidas)
            }
        }
    }
}