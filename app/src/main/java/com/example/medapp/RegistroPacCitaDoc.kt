package com.example.medapp

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class RegistroPacCitaDoc : AppCompatActivity() {
    var titulo: EditText? = null
    var ubicacion: EditText? = null
    var SelFecha: EditText? = null
    var descripcion: EditText? = null
    var agregarEvento: Button? = null
    var calendario = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_citadoc2)
        supportActionBar!!.hide() ///esconde la barra de accion






    }


}