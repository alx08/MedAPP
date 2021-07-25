package com.example.medapp

import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
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
        btnSiguiente = findViewById(R.id.btnRegistroDoc)

        btnSiguiente?.setOnClickListener {
            val inicio = Intent(this@RegistroCitaDoc, RegistroPacCitaDoc::class.java)
            startActivity(inicio)
        }

        //selFecha2.setOnClickListener(View.OnClickListener { DatePickerDialog(this@RegistroCitaDoc, date, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH]).show() })
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        datePicker.show(supportFragmentManager, datePicker.toString())

    }


}