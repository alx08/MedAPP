package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


class RegistroCitaDoc : AppCompatActivity() {
    var txtFecha: EditText? = null
    var calendario = Calendar.getInstance()
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_citadoc)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnSiguiente = findViewById(R.id.btnRegistroDoc)
        txtFecha = findViewById(R.id.txtFecha)
        btnSiguiente?.setOnClickListener {
            val inicio = Intent(this@RegistroCitaDoc, RegistroPacCitaDoc::class.java)
            startActivity(inicio)
        }

        //selFecha2.setOnClickListener(View.OnClickListener { DatePickerDialog(this@RegistroCitaDoc, date, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH]).show() })

        txtFecha!!.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            //Escondes el teclado y muestras el datePicker
            val builder = MaterialDatePicker.Builder.datePicker()
                .also {
                    title = "Pick Date"
                }
            val datePicker = builder.build()


            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(it)
                txtFecha!!.setText("${calendar.get(Calendar.DAY_OF_MONTH)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.YEAR)}")
            }
            datePicker.show(supportFragmentManager, "MyTAG")
        })


    }


}