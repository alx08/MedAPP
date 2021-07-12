package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.EncontrarMedicoActivity

class EncontrarMedicoActivity : AppCompatActivity() {
    var btnBuscar: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encontrar_medico)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnBuscar = findViewById<View>(R.id.btnBuscar) as Button
        btnBuscar!!.setOnClickListener {
            val inicio = Intent(this@EncontrarMedicoActivity, ListaMedicosActivity::class.java)
            startActivity(inicio)
        }
    }
}