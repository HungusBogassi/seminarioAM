package com.example.dune

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : ComponentActivity() {

    lateinit var btnCrearUsuario: Button
    lateinit var btnIniciarSesion: Button
    lateinit var etUsuario: EditText
    lateinit var etContrasenia: EditText
    lateinit var cbChequeo: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        registroExitoso()

        etUsuario = findViewById(R.id.usuario)
        etContrasenia = findViewById(R.id.contraseña)
        cbChequeo = findViewById(R.id.chequeo)

        val preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        val usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        val passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        if(usuarioGuardado != null && passwordGuardado != ""){
            startLibrosActivity(usuarioGuardado)
        }

        btnCrearUsuario = findViewById(R.id.crearUsuario)
        btnCrearUsuario.setOnClickListener {
            //pasa a la activity Registro y crea un usuario nuevo
            val intentRegistro = Intent(this, Registro::class.java)
            startActivity(intentRegistro)
        }

        btnIniciarSesion = findViewById(R.id.iniciarSesion)
        btnIniciarSesion.setOnClickListener{
            //intentara pasar a la activity de datos propiamente dicha

            val nombreUsuario = etUsuario.text.toString()
            val passwordUsuario = etContrasenia.text.toString()

            if(nombreUsuario.isEmpty() || passwordUsuario.isEmpty()){
                //mensaje+= ". Faltan Datos"
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show()
            }else {
                if(cbChequeo.isChecked){

                    val preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), nombreUsuario).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), passwordUsuario).apply()
                }
                startLibrosActivity(nombreUsuario)
            }
        }
    }

    private fun registroExitoso() {
        // Obtengo los datos que me mandaron
        val bundle: Bundle? = intent.extras
        // Reviso que efectivamente tenga datos
        if(bundle != null){
            // Obtengo el dato especifico
            val mensaje = bundle?.getString("mensaje")
            // Muestro el mensaje
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startLibrosActivity(usuarioGuardado: String) {
        // Indicamos a que pantalla queremos ir
        val intentMain = Intent(this, ListaLibros::class.java)
        // Agregamos datos que queremos pasar a la proxima pantalla
        intentMain.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)
        // Cambiamos de pantalla
        startActivity(intentMain)
        // Eliminamos la Activity actual para sacarla de la Pila
        finish()
    }

    override fun onCreateOptionsMenu(menu:  Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_en_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.guardar ->Toast.makeText(this, "Boton Guardar", Toast.LENGTH_SHORT).show()
            R.id.compartir ->Toast.makeText(this, "Boton Compartir", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}