package com.example.dune

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegistrosDao {
    //retorna una lista de todos los registros en la tabla
    @Query("select * from Registros")
    fun getAll(): List<Registros>

    //dado un nombre de usuario, lo busca en la tabla y retorna el registro completo, puede retornar null
    @Query("select * from Registros where usuario = :nombre")
    fun getRegistro(nombre: String): Registros

    //una funcion para buscar el registro completo con usuario y password
    @Query("select * from Registros where usuario = :nombre and password = :pass")
    fun getUsuario(nombre: String, pass: String): Registros
    @Insert
    fun insertUsuario(registro: Registros)
}
