package com.example.dune

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class Citas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)

        //--------------------------------------------------
        val recyclerView = findViewById<RecyclerView>(R.id.citaRecyclerView)

        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callGetPost = api.getCitas()
        callGetPost.enqueue(object : retrofit2.Callback<List<Cita>> {
            override fun onResponse(call: Call<List<Cita>>, response: Response<List<Cita>>) {
                val citas = response.body()
                if (citas != null){
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@Citas)
                        adapter = CitaAdapter(citas)
                    }
                }
            }
            override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                Toast.makeText(this@Citas, "FALLO", Toast.LENGTH_SHORT).show()
            }
        })
        //--------------------------------------------------
    }
}