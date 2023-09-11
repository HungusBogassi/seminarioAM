package com.example.dune

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegistrosDao {
    @Query("select * from Registros")
    fun getAll(): List<Registros>

    //una funcion para buscar el registro con usuario, password
    @Query("select * from Registros where usuario = :nombre and password = :pass")
    fun getUsuario(nombre: String, pass: String): Registros
    @Insert
    fun insertUsuario(registro: Registros)
}
