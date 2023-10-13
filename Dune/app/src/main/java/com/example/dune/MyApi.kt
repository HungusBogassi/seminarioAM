package com.example.dune

import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("/citas")
    fun getCitas() : Call<List<Cita>>
}