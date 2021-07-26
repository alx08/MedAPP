package com.example.medapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RegistroPacCita : AppCompatActivity() {
    var titulo: EditText? = null
    var ubicacion: EditText? = null
    var SelFecha: EditText? = null
    var descripcion: EditText? = null
    var agregarEvento: Button? = null
    var calendario = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_cita)
        supportActionBar!!.hide() ///esconde la barra de accion
        titulo = findViewById(R.id.txtTitulo)
        //ubicacion = findViewById(R.id.txtUbicacion)
        descripcion = findViewById(R.id.txtDescripcion)
        agregarEvento = findViewById(R.id.btnRegistroDoc)
        SelFecha = findViewById<View>(R.id.selFecha) as EditText

    }

    private fun actualizarInput() {
        val formatoDeFecha = "MM/dd/yy" //
        val sdf = SimpleDateFormat(formatoDeFecha, Locale.US)
        SelFecha!!.setText(sdf.format(calendario.time))
    }
}