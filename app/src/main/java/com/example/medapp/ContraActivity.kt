package com.example.medapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.w3c.dom.Text

class ContraActivity : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100
    lateinit var radioButton : RadioButton
    var radioGroup: RadioGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contra)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.registrocolor)
        }

        /**Validar no sesiones activas**/
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        FirebaseAuth.getInstance().signOut()

        supportActionBar!!.hide() ///esconde la barra de accion
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val iniciarGoo : Button = findViewById(R.id.btnGoogle)
        val correo : EditText = findViewById(R.id.txtEmail)
        val pass : EditText = findViewById(R.id.txtPassword)
        val btnRegistrar  : Button = findViewById(R.id.btnRegistrar)
        radioGroup = findViewById(R.id.radioGroup)



        btnRegistrar.setOnClickListener {
            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(intSelectButton)
            if (correo.text.isNotEmpty() &&  pass.text.isNotEmpty() && radioButton.text == "Paciente"){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.text.toString(), pass.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                           showHome(it.result?.user?.email ?:"", ProviderType.BASIC, "Paciente")

                    } else{
                        ShowAlert()
                    }
                }
            }

            if (correo.text.isNotEmpty() &&  pass.text.isNotEmpty() && radioButton.text != "Paciente"){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo.text.toString(), pass.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"", ProviderType.BASIC, "Medico")

                    } else{
                        ShowAlert()
                    }
                }
            }
        }

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

    //Integracion boton google
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(intSelectButton)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)

                if (account != null && radioButton?.text == "Paciente") {
                    val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if(it.isSuccessful){
                            showHome(account.email ?: "", ProviderType.GOOGLE, "Paciente")
                        } else
                        {
                            ShowAlert()
                        }
                    }
                }

                if (account != null && radioButton?.text != "Paciente") {
                    val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if(it.isSuccessful){
                            showHome(account.email ?: "", ProviderType.GOOGLE, "Medico")
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

    private fun ShowAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El correo ya se encuentra registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType, tipo: String){
        val homeIntent = Intent(this, registrodoc1Activity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
            putExtra("tipo", tipo)
        }
        startActivity(homeIntent)
    }


}