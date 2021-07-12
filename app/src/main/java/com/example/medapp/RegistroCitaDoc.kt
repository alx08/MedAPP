package com.example.medapp

import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RegistroCitaDoc : AppCompatActivity() {
    var SelFecha2: EditText? = null
    var calendario = Calendar.getInstance()
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_citadoc)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@RegistroCitaDoc, RegistroPacCita::class.java)
            startActivity(inicio)
        }
        SelFecha2 = findViewById<View>(R.id.selFecha2) as EditText
        SelFecha2 = findViewById(R.id.selFecha2)
        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendario[Calendar.YEAR] = year
            calendario[Calendar.MONTH] = monthOfYear
            calendario[Calendar.DAY_OF_MONTH] = dayOfMonth
            actualizarInput()
        }
        //selFecha2.setOnClickListener(View.OnClickListener { DatePickerDialog(this@RegistroCitaDoc, date, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH]).show() })
    }

    private fun actualizarInput() {
        val formatoDeFecha = "MM/dd/yy" //
        val sdf = SimpleDateFormat(formatoDeFecha, Locale.US)
        SelFecha2!!.setText(sdf.format(calendario.time))
    }
}