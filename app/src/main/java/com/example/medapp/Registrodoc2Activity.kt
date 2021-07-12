package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Registrodoc2Activity : AppCompatActivity() {
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide() ///esconde la barra de accion
        setContentView(R.layout.activity_registrodoc2)
        val spinner = findViewById<View>(R.id.spEspecialidades) as Spinner
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.valores_especialiades, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@Registrodoc2Activity, registroInfoActivity::class.java)
            startActivity(inicio)
        }
    }
}