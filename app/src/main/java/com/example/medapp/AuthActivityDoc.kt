package com.example.medapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.security.Provider

class AuthActivityDoc : AppCompatActivity() {
   // val IniciarSes : Button = findViewById(R.id.btnIniciarSesion)
    private val GOOGLE_SIGN_IN = 100
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_doc)
        supportActionBar!!.hide()


        setup()
        session()
    }

    //Autenticacion usuario/contra
    private fun setup() {
        val iniciarSes : Button = findViewById(R.id.btnIniciarSesion)
        val iniciarGoo : Button = findViewById(R.id.btnGoogle)
        val registro  : Button = findViewById(R.id.btnRegistrar)
        val correo: EditText = findViewById(R.id.TxtEmail)
        val pass: EditText = findViewById(R.id.TxtPassword)
        title = "Autenticacion"

        iniciarSes.setOnClickListener {
            if (correo.text.isNotEmpty() &&  pass.text.isNotEmpty() ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo.text.toString(), pass.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC)
                    } else{
                        ShowAlert()
                    }
                }
            }
        }

        registro.setOnClickListener {
            val regIntent = Intent(this, ContraActivity::class.java)
            startActivity(regIntent);
        }

        //Google boton configuracion
        iniciarGoo.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }


    }

    //Integracion boton google - activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == GOOGLE_SIGN_IN) {
        val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
        val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)

        if (account != null) {
            val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                if(it.isSuccessful){
                    showHome(account.email ?: "", ProviderType.GOOGLE)
                } else
                {
                    ShowAlert()
                }
            }
        }
        } catch (e: ApiException){
            ShowAlert()
        }
        }
    }

    fun ShowAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, MainActivityVenPrincipalDoc::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }

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
}