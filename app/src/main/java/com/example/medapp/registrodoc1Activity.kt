package com.example.medapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class registrodoc1Activity : AppCompatActivity() {
    var btnSiguiente: Button? = null
    var btnRegistroDoc: Button? = null
    var txtCI: EditText? = null
    var txtNombres: EditText? = null
    var txtApellidos: EditText? = null
    var txtCorreo: EditText? = null

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrodoc)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.registrocolor)
        }

        supportActionBar!!.hide() ///esconde la barra de accion

        txtCI = findViewById(R.id.txtCI)
        txtNombres = findViewById(R.id.txtNombres)
        txtApellidos = findViewById(R.id.txtApellidos)
        txtCorreo = findViewById(R.id.txtCorreo)
        btnRegistroDoc = findViewById(R.id.btnRegistroDoc)


        btnSiguiente = findViewById<View>(R.id.btnRegistroDoc) as Button
        btnSiguiente!!.setOnClickListener {
            val inicio = Intent(this@registrodoc1Activity, MainActivityVenPrincipalDoc::class.java)
            startActivity(inicio)
        }

        //obtener datos de crear sesion
        val user = FirebaseAuth.getInstance().uid
        val bundle: Bundle? = intent.extras;
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        val tipo = bundle?.getString("tipo")

        val cedula = txtCI?.text.toString()
        val nombres = txtNombres?.text.toString()
        val apellidos = txtApellidos?.text.toString()


        btnRegistroDoc?.setOnClickListener {
            db.collection("usuariosmed").document(user.toString()).set(
                hashMapOf(
                    "cedula" to txtCI?.text.toString(),
                    "nombres" to txtNombres?.text.toString(),
                    "apellido" to txtApellidos?.text.toString(),
                    "correo" to email.toString(),
                    "tipo" to tipo)
            )
            if(tipo=="Paciente")
            {
                showHomePaciente()
            }
            if(tipo=="Doctor")
            {
                showHome()
            }
        }

        val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs?.putString("email", email)
        prefs?.putString("uid", user.toString())
        prefs?.apply()


    }

    private fun showHome(){
        val homeIntent = Intent(this, MainActivityVenPrincipalDoc::class.java)
        startActivity(homeIntent)
    }
    private fun showHomePaciente(){
        val homeIntent = Intent(this, venMainPrincipalPacActivity::class.java)
        startActivity(homeIntent)
    }


}