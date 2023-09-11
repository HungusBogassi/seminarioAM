package com.example.dune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dune.ui.theme.DuneTheme

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.CheckBox
import android.content.Intent
import android.view.View
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

                Toast.makeText(this, "Faltan Datos", Toast.LENGTH_LONG).show()

            }else
            {
                if(etUsuarioPass.text.toString().equals(etReingUsuarioPass.text.toString())){

                    // crea el usuario
                    var nuevoUsuario = Registros(etUsuarioPass.text.toString(),etReingUsuarioPass.text.toString())
                    AppDatabase.getDatabase(this).registrosDao().insertUsuario(nuevoUsuario)

                    //vuelve al inicio
                    val intentMain = Intent(this, MainActivity::class.java)
                    intentMain.putExtra("mensaje", "Usuario creado con exito. Puede loguearse ahora.")
                    startActivity(intentMain)
                    finish()
                } else
                {
                    Toast.makeText(this, "Error. Las contraseñas no son iguales", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
