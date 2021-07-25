package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ListaMedicosActivity : AppCompatActivity() {
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_medicos)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnSiguiente = findViewById<View>(R.id.btnRegresar) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@ListaMedicosActivity, RegistroPacCita3::class.java)
            startActivity(inicio)
        }
    }
}