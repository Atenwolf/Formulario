package jose.machuca.formulariovista

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VguardarActivity : AppCompatActivity() {
    private lateinit var txtDatos: TextView
    private lateinit var btnGuardar: Button
    private lateinit var btnVolver: Button

    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vguardar)

        txtDatos = findViewById(R.id.txtDatos)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnVolver = findViewById(R.id.btnVolver)

        dbHelper = MyDatabaseHelper(this)

        val intent = intent
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val direccion = intent.getStringExtra("direccion")
        val edad = intent.getIntExtra("edad", -1)
        val celular = intent.getIntExtra("celular", -1)
        val fecha = intent.getStringExtra("fecha")
        val hombreChecked = intent.getBooleanExtra("hombreChecked", false)
        val mujerChecked = intent.getBooleanExtra("mujerChecked", false)

        // Mostrar los datos en el TextView
        val datosStr = "Nombre: $nombre\nApellido: $apellido\nDirección: $direccion\n" +
                "Edad: $edad\nCelular: $celular\nFecha: $fecha\nHombre: $hombreChecked\nMujer: $mujerChecked"
        txtDatos.text = datosStr


        btnGuardar.setOnClickListener {

            val newRowId = guardarEnBaseDeDatos(
                nombre, apellido, direccion, edad, celular,
                fecha, hombreChecked, mujerChecked
            )

            if (newRowId != -1L) {

                Toast.makeText(this, "Datos insertados en la base de datos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    this,
                    "Error al insertar datos en la base de datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun guardarEnBaseDeDatos(
        nombre: String?, apellido: String?, direccion: String?, edad: Int?,
        celular: Int?, fecha: String?, activoHombre: Boolean, activoMujer: Boolean
    ): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues()
        values.put(MyDatabaseHelper.COLUMN_NAME, nombre)
        values.put(MyDatabaseHelper.COLUMN_APELLIDO, apellido)
        values.put(MyDatabaseHelper.COLUMN_EMAIL, "")
        values.put(MyDatabaseHelper.COLUMN_DIR, direccion)
        values.put(MyDatabaseHelper.COLUMN_EDAD, edad)
        values.put(MyDatabaseHelper.COLUMN_CEL, celular)
        values.put(MyDatabaseHelper.COLUMN_DATE, fecha)
        values.put(
            MyDatabaseHelper.COLUMN_BOOLEAN,
            if (activoHombre) 1 else if (activoMujer) 1 else 0
        )

        val newRowId = db.insert(MyDatabaseHelper.TABLE_NAME, null, values)
        db.close()
        return newRowId
    }
}
