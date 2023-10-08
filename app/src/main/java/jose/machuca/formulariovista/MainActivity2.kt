package jose.machuca.formulariovista

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity2 : AppCompatActivity() {
    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var direccionEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var celularEditText: EditText
    private lateinit var fechaEditText: EditText
    private lateinit var hombreCheckBox: CheckBox
    private lateinit var mujerCheckBox: CheckBox
    private lateinit var guardarButton: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnVolver: Button

    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        nombreEditText = findViewById(R.id.Nombre)
        apellidoEditText = findViewById(R.id.Apellido)
        direccionEditText = findViewById(R.id.Direccion)
        edadEditText = findViewById(R.id.Edad)
        celularEditText = findViewById(R.id.Celular)
        fechaEditText = findViewById(R.id.Fecha)
        hombreCheckBox = findViewById(R.id.Hombre)
        mujerCheckBox = findViewById(R.id.Mujer)
        guardarButton = findViewById(R.id.Guardar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnVolver = findViewById(R.id.btnVolver)

        dbHelper = MyDatabaseHelper(this)

        guardarButton.setOnClickListener {
            val nombreStr = nombreEditText.text.toString()
            val apellidoStr = apellidoEditText.text.toString()
            val direccionStr = direccionEditText.text.toString()
            val edadStr = edadEditText.text.toString().toIntOrNull()
            val celularStr = celularEditText.text.toString().toIntOrNull()
            val fechaStr = fechaEditText.text.toString()
            val hombreChecked = hombreCheckBox.isChecked
            val mujerChecked = mujerCheckBox.isChecked

            val fecha = convertirStringADate(fechaStr)

            if (fecha != null) {
                val intent = Intent(this, VguardarActivity::class.java)

                intent.putExtra("nombre", nombreStr)
                intent.putExtra("apellido", apellidoStr)
                intent.putExtra("direccion", direccionStr)
                intent.putExtra("edad", edadStr)
                intent.putExtra("celular", celularStr)
                intent.putExtra("fecha", fechaStr)
                intent.putExtra("hombreChecked", hombreChecked)
                intent.putExtra("mujerChecked", mujerChecked)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Error al convertir la fecha", Toast.LENGTH_SHORT).show()
            }
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        btnVolver.setOnClickListener {
            volverAActividadAnterior()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertirStringADate(fechaStr: String): Date? {
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
        return try {
            formatoFecha.parse(fechaStr)
        } catch (e: Exception) {
            null
        }
    }

    private fun limpiarCampos() {
        nombreEditText.text.clear()
        apellidoEditText.text.clear()
        direccionEditText.text.clear()
        edadEditText.text.clear()
        celularEditText.text.clear()
        fechaEditText.text.clear()
        hombreCheckBox.isChecked = false
        mujerCheckBox.isChecked = false
    }

    private fun volverAActividadAnterior() {
        finish()
    }
}











