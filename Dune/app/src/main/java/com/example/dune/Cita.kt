package com.example.dune
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cita(
    var id: Int,
    var cita: String,
    var autor: String
)