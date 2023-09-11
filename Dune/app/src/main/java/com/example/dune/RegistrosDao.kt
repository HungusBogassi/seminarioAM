package com.example.dune

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegistrosDao {
    @Query("select * from Registros")//aca uso la clase como nombre de la tabla
    fun getAll(): List<Registros>

    //una funcion que devuelve los usuarios


    @Insert
    fun insertRegistro(registro: Registros)
}
