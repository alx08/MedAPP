package com.example.medapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.venMainPrincipalPacActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class venMainPrincipalPacActivity : AppCompatActivity() {
    var btnRealizarCita: Button? = null
    var btnCerrarSesion: Button? =null
    var txtNombrePaciente: TextView? = null

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ven_main_principal_pac)
        supportActionBar!!.hide() ///esconde la barra de accion
        txtNombrePaciente = findViewById(R.id.txtNombrePaciente)
        btnRealizarCita = findViewById<View>(R.id.btnRealizarCita) as Button
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)
        btnRealizarCita!!.setOnClickListener {
            val inicio = Intent(this@venMainPrincipalPacActivity, EncontrarMedicoActivity::class.java)
            startActivity(inicio)
        }

        val user = FirebaseAuth.getInstance().uid
        datosPersonales(user.toString())

        val bundle: Bundle? = intent.extras;
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        btnCerrarSesion!!.setOnClickListener {
            val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            showHome()
        }

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider.toString())
        prefs.apply()

       }

    private fun datosPersonales(uid: String){

        db.collection("usuarios").document(uid).get().addOnSuccessListener {
            txtNombrePaciente?.setText(it.get("nombres") as String?)
        }
    }
    private fun showHome(){
        val homeIntent = Intent(this, AuthActivityDoc::class.java)
        startActivity(homeIntent)
    }

    override fun onBackPressed() {}

}