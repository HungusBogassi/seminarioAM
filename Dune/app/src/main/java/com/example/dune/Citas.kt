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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Citas : ComponentActivity() {

    lateinit var btnVolver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)

        //--------------------------------------------------
        //ENTREGA 3: utilizar una API Rest en un RecyclerView

        val recyclerView = findViewById<RecyclerView>(R.id.citaRecyclerView)
        val api = RetrofitClient.retrofit.create(MyApi::class.java)

        //Utilizamos una Corrutina para obtener los datos a mostrar
        CoroutineScope(Dispatchers.IO).launch {
            val callGetPost = api.getCitas()
            callGetPost.enqueue(object : retrofit2.Callback<List<Cita>> {
                override fun onResponse(call: Call<List<Cita>>, response: Response<List<Cita>>) {
                    val citas = response.body()
                    //al hilo principal envia los datos para mostrarlos en el RecyclerView
                    runOnUiThread(){
                        if (citas != null){
                            //cargo los datos en el RecyclerView
                            recyclerView.apply {
                                layoutManager = LinearLayoutManager(this@Citas)
                                adapter = CitaAdapter(citas)
                            }
                        }else{
                            Toast.makeText(this@Citas, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                        }
                    }
                    //fin hilo principal
                }
                override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                    Toast.makeText(this@Citas, "FALLO LA BUSQUEDA DE LA API REST", Toast.LENGTH_SHORT).show()
                }
            })
        }
        //--------------------------------------------------

        //Button para volver a la lista de libros
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