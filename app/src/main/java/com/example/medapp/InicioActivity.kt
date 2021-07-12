package com.example.medapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.InicioActivity

class InicioActivity : AppCompatActivity() {
    var btnDoctor: Button? = null
    var btnPaciente: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnDoctor = findViewById<View>(R.id.btnDoctor) as Button
        btnPaciente = findViewById<View>(R.id.btnCalendario) as Button
        btnDoctor!!.setOnClickListener {
            val inicio = Intent(this@InicioActivity, AuthActivityDoc::class.java)
            startActivity(inicio)
        }
        btnPaciente!!.setOnClickListener {
            val inicio = Intent(this@InicioActivity, AuthActivityPac::class.java)
            startActivity(inicio)
        }
        session()
    }


    ///Recordar Credenciales
    //Clase - ingresar
    private fun session(){
        val authlayout: View? = findViewById(R.id.AuthDoc)
        val prefs: SharedPreferences = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email: String? = prefs.getString("email", null)
        val provider: String? = prefs.getString("provider", null)

        if (email != null && provider !=null ){
            authlayout?.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }

    }

    //Clase - ingresar
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, MainActivityVenPrincipalDoc::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }

}