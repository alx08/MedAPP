package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.AuthActivityPac

class AuthActivityPac : AppCompatActivity() {
    var btnRegistrarPac: Button? = null
    var btnIniciarSesionPac: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_pac)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnRegistrarPac = findViewById<View>(R.id.btnRegistrarPac) as Button
        btnIniciarSesionPac = findViewById<View>(R.id.btnIniciarSesionPac) as Button
        btnRegistrarPac!!.setOnClickListener {
            val inicio = Intent(this@AuthActivityPac, RegistroPacActivity1::class.java)
            startActivity(inicio)
        }
        btnIniciarSesionPac!!.setOnClickListener {
            val inicio = Intent(this@AuthActivityPac, venMainPrincipalPacActivity::class.java)
            startActivity(inicio)
        }
    }
}