package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegistroPacActivity1 : AppCompatActivity() {
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_pac1)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@RegistroPacActivity1, ContraActivity::class.java)
            startActivity(inicio)
        }
    }
}