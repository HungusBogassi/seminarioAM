package com.example.dune

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.CheckBox
import android.content.Intent
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : ComponentActivity() {

    lateinit var btnCrearUsuario: Button
    lateinit var btnIniciarSesion: Button
    lateinit var etUsuario: EditText
    lateinit var etContrasenia: EditText
    lateinit var cbChequeo: CheckBox

    val channelId = "test"
    val channelName = "test"

    @SuppressLint("MissingPermission")
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

        //si guardamos los datos, pasa a la lista de libro de forma directa
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
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
            }else {
                //guardo los datos para futuros logueos
                if(cbChequeo.isChecked){

                    val preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), nombreUsuario).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), passwordUsuario).apply()
                }
                //verifico si el usuario existe, para eso lo busca por nombre y password en la base de datos

                val bdd = AppDatabase.getDatabase(this)
                val reg: Registros
                reg = bdd.registrosDao().getUsuario(nombreUsuario, passwordUsuario)//tomo un registro por usuario y password

                //si existe el usuario con el password, pasa a la lista de libros, sino muestra un mensaje de error
                if (reg != null) {
                    startLibrosActivity(nombreUsuario)
                }
                else{
                    Toast.makeText(this, "Usuario inexistente", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //------------------------------------------
        //ENTREGA 3 Notificacion

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName,importancia)

            //manager de notificaciones
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

            cbChequeo.setOnClickListener { view ->
                if ((view as CheckBox).isChecked) {
                    //configurando notificacion
                    val notificacion = NotificationCompat.Builder(this, channelId).also {
                        it.setContentTitle("AVISO AL USUARIO")
                        it.setContentText("El usuario y password ingresado se almacenaran para un futuro ingreso directo")
                        it.setSmallIcon(R.drawable.ic_mensage)
                    }.build()

                    //mostrar la notificacion
                    val notificationManager = NotificationManagerCompat.from(applicationContext)
                    notificationManager.notify(1, notificacion)
                }
            }
        }
        //-------------------------------------------
    }


    private fun registroExitoso() {
        // Obtengo los datos que me mandaron
        val bundle: Bundle? = intent.extras
        // Reviso que efectivamente tenga datos
        if(bundle != null){
            // Obtengo el dato especifico
            val mensaje = bundle?.getString("mensaje")
            // Muestro el mensaje
            //Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    private fun startLibrosActivity(usuarioGuardado: String) {
        // Indicamos a que pantalla queremos ir
        val intentMain = Intent(this, ListaLibros::class.java)
        // Agregamos datos que queremos pasar a la proxima pantalla
        intentMain.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)
        // Cambiamos de pantalla y cerramos la anterior
        startActivity(intentMain)
        //startActivity(intentMain)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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