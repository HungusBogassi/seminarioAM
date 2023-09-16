package com.example.dune

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.Toast

class Registro : ComponentActivity() {

    lateinit var btnCrearUsuario: Button
    lateinit var etUsuario: EditText
    lateinit var etUsuarioPass: EditText
    lateinit var etReingUsuarioPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etUsuario = findViewById(R.id.usuarioNuevo)
        etUsuarioPass = findViewById(R.id.password)
        etReingUsuarioPass = findViewById(R.id.reingPassword)
        btnCrearUsuario = findViewById(R.id.botonCrear)

        btnCrearUsuario.setOnClickListener {
            //control de que no haya datos imcompletos
            if(etUsuario.text.toString().isEmpty() || etUsuarioPass.text.toString().isEmpty() || etReingUsuarioPass.text.toString().isEmpty()){

                Toast.makeText(this, "Faltan Datos", Toast.LENGTH_SHORT).show()
            }else
            {
                if(etUsuarioPass.text.toString().equals(etReingUsuarioPass.text.toString())){

                    var bdd = AppDatabase.getDatabase(this) //la base de datos
                    var registroUsuario: Registros
                    registroUsuario = bdd.registrosDao().getRegistro(etUsuario.text.toString()) //registro completo dado por el nombre del usuario

                    if (registroUsuario != null) {
                        Toast.makeText(this, "Usuario Existente", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        //no existe el usuario, entonces lo crea y lo guarda
                        var nuevoUsuario = Registros(etUsuario.text.toString(),etUsuarioPass.text.toString())
                        AppDatabase.getDatabase(this).registrosDao().insertUsuario(nuevoUsuario)//agrega el nuevo usuario

                        //vuelve al inicio
                        val intentMain = Intent(this, MainActivity::class.java)
                        intentMain.putExtra("mensaje", "Usuario creado con exito. Puede loguearse ahora.")
                        startActivity(intentMain)
                        finish()
                    }

                } else
                {
                    Toast.makeText(this, "Error. Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
