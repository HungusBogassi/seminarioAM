package com.example.dune

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class Citas : ComponentActivity() {

    lateinit var btnVolver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)

        //--------------------------------------------------
        //PRE- RecyclerView para la citas
        val recyclerView = findViewById<RecyclerView>(R.id.citaRecyclerView)

        //--------------------------------------------------
        //DURANTE- busco las citas en la API Rest
        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callGetPost = api.getCitas()
        callGetPost.enqueue(object : retrofit2.Callback<List<Cita>> {
            override fun onResponse(call: Call<List<Cita>>, response: Response<List<Cita>>) {
                val citas = response.body()
                if (citas != null){

                    //completo el RecyclerView
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@Citas)
                        adapter = CitaAdapter(citas)
                    }
                }
            }
            override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                Toast.makeText(this@Citas, "FALLO LA BUSQUEDA DE LA API REST", Toast.LENGTH_SHORT).show()
            }
        })

        //--------------------------------------------------
        //POST-

        //--------------------------------------------------
        btnVolver = findViewById(R.id.btVolverLibros)
        btnVolver.setOnClickListener {
            //regresa a la lista de libros
            val intentLibros = Intent(this, ListaLibros::class.java)
            startActivity(intentLibros)
            finish()
        }
        //--------------------------------------------------
    }
}