package com.example.medapp

import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RegistroPacCitaDoc : AppCompatActivity() {
    var titulo: EditText? = null
    var ubicacion: EditText? = null
    var SelFecha: EditText? = null
    var descripcion: EditText? = null
    var agregarEvento: Button? = null
    var calendario = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_citadoc)
        supportActionBar!!.hide() ///esconde la barra de accion
        titulo = findViewById(R.id.txtTitulo)
        ubicacion = findViewById(R.id.txtUbicacion)
        descripcion = findViewById(R.id.txtDescripcion)
        agregarEvento = findViewById(R.id.btnRegistroDoc)
        SelFecha = findViewById<View>(R.id.selFecha) as EditText
        SelFecha = findViewById(R.id.selFecha)
        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendario[Calendar.YEAR] = year
            calendario[Calendar.MONTH] = monthOfYear
            calendario[Calendar.DAY_OF_MONTH] = dayOfMonth
            actualizarInput()
        }
        /*SelFecha.setOnClickListener(View.OnClickListener { DatePickerDialog(this@RegistroPacCitaDoc, date, calendario[Calendar.YEAR], calendario[Calendar.MONTH], calendario[Calendar.DAY_OF_MONTH]).show() })
        agregarEvento.setOnClickListener(View.OnClickListener {
            if (!titulo.getText().toString().isEmpty() && !ubicacion.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty()) {
                val intent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, titulo.getText().toString())
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, ubicacion.getText().toString())
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this@RegistroPacCitaDoc, "No hay app que pueda soportar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@RegistroPacCitaDoc, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        })*/
    }

    private fun actualizarInput() {
        val formatoDeFecha = "MM/dd/yy" //
        val sdf = SimpleDateFormat(formatoDeFecha, Locale.US)
        SelFecha!!.setText(sdf.format(calendario.time))
    }
}