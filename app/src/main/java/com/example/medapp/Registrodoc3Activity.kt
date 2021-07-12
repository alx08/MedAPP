package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.Registrodoc3Activity

class Registrodoc3Activity : AppCompatActivity() {
    var btnRegistrar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrodoc3)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnRegistrar = findViewById<View>(R.id.btnRegistrar) as Button
        btnRegistrar!!.setOnClickListener {
            val inicio = Intent(this@Registrodoc3Activity, MainActivityVenPrincipalDoc::class.java)
            startActivity(inicio)
        }
    }
}