package com.example.medapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.venMainPrincipalPacActivity

class venMainPrincipalPacActivity : AppCompatActivity() {
    var btnRealizarCita: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ven_main_principal_pac)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnRealizarCita = findViewById<View>(R.id.btnRealizarCita) as Button
        btnRealizarCita!!.setOnClickListener {
            val inicio = Intent(this@venMainPrincipalPacActivity, EncontrarMedicoActivity::class.java)
            startActivity(inicio)
        }
    }
}