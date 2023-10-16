package com.example.dune

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class Libro05 : ComponentActivity() {

    lateinit var btnVolver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_libro05)

        btnVolver = findViewById(R.id.volver)
        btnVolver.setOnClickListener {
            //regresa a la lista de libros
            val intentLibros = Intent(this, ListaLibros::class.java)
            startActivity(intentLibros)
            finish()
        }
    }
}