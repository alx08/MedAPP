package com.example.medapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.medapp.MainActivityVenPrincipalDoc
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType{
    BASIC,
    GOOGLE
}

class MainActivityVenPrincipalDoc : AppCompatActivity() {
    var btnPersonalizar: Button? = null
    var btnCerrarSesion: Button? =null
    var CrearCita: Button? = null
    var txtNombreDoctor: TextView? = null
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ven_principal)
        supportActionBar!!.hide() ///esconde la barra de accion
        btnPersonalizar = findViewById<View>(R.id.btnPersonalizar) as Button
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)
        txtNombreDoctor = findViewById(R.id.txtNombreDoctor)

        val user = FirebaseAuth.getInstance().uid
        datosPersonales(user.toString())

        btnPersonalizar!!.setOnClickListener {
            val inicio = Intent(this@MainActivityVenPrincipalDoc, Registrodoc2Activity::class.java)
            startActivity(inicio)
        }
        CrearCita = findViewById<View>(R.id.CrearCita) as Button
        CrearCita!!.setOnClickListener {
            val inicio = Intent(this@MainActivityVenPrincipalDoc, RegistroCitaDoc::class.java)
            startActivity(inicio)
        }

        //Cerrar sesion
        btnCerrarSesion!!.setOnClickListener {
            val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            showHome()
        }

        val bundle: Bundle? = intent.extras;
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?:"", provider ?: "")

        /**Almacena UID**/

       /* val prefs: SharedPreferences.Editor? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs?.putString("email", email)
        prefs?.putString("provider", user.toString())
        prefs?.apply()*/

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        /*readData() {
            Log.d("tipo", it.size.toString())
        }*/

    }


    private  fun setup(email: String, provider: String){
    title = "Inicio"

        //Guardar valores de sesion
        txtNombreDoctor?.text = email
        val provider = provider
    }

    private fun datosPersonales(uid: String){

    db.collection("usuarios").document(uid).get().addOnSuccessListener {
        /*txtNombreDoctor?.setText(it.get("nombres") as String?)*/
    }
        val user = FirebaseAuth.getInstance().uid
        db.collection("usuarios").document(uid).get().addOnSuccessListener {
            txtNombreDoctor?.setText(it.get("tipo") as String?)
        }
       //txtNombreDoctor?.setText(tipo) as String?

    }

    private fun showHome(){
        val homeIntent = Intent(this, AuthActivityDoc::class.java)
        startActivity(homeIntent)
    }

    /*
    fun readData(myCallback: (List<String>) -> Unit) {
        val user = FirebaseAuth.getInstance().uid
        db.collection("usuarios").document(user.toString()).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val list = ArrayList<String>()
                for (document in task.result) {
                    val name = document.data["name"].toString()
                    list.add(name)
                }
                myCallback(list)
            }
        }
    }*/

    /**Bloqueo de boton para regresar**/
    override fun onBackPressed() {}


}