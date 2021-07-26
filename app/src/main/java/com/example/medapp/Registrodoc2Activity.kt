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

        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@Registrodoc2Activity, Registrodoc3Activity::class.java)
            startActivity(inicio)
        }
    }
}