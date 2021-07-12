package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class registroInfoActivity : AppCompatActivity() {
    var btnSiguiente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_info)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@registroInfoActivity, Registrodoc3Activity::class.java)
            startActivity(inicio)
        }
    }
}