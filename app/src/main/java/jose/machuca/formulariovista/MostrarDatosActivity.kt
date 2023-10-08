import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import jose.machuca.formulariovista.MyDatabaseHelper
import jose.machuca.formulariovista.R

class MostrarDatosActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper
    private lateinit var cursor: Cursor
    private lateinit var gridView: GridView
    private lateinit var btnVisualizar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_datos)

        dbHelper = MyDatabaseHelper(this)
        gridView = findViewById(R.id.gridViewDatos)
        btnVisualizar = findViewById(R.id.btnVisualizar)

        btnVisualizar.setOnClickListener { cargarDatos() }
    }

    private fun cargarDatos() {
        try {
            val db = dbHelper.readableDatabase

            cursor = db.rawQuery("SELECT * FROM ${MyDatabaseHelper.TABLE_NAME}", null)

            val fromColumns = arrayOf(
                MyDatabaseHelper.COLUMN_NAME,
                MyDatabaseHelper.COLUMN_APELLIDO,
                MyDatabaseHelper.COLUMN_DIR,
                MyDatabaseHelper.COLUMN_EDAD,
                MyDatabaseHelper.COLUMN_CEL,
                MyDatabaseHelper.COLUMN_DATE,
                MyDatabaseHelper.COLUMN_BOOLEAN
            )

            val toViews = intArrayOf(
                R.id.textNombre,
                R.id.textApellido,
                R.id.textDireccion,
                R.id.textEdad,
                R.id.textCelular,
                R.id.textFecha,
                R.id.textActivo
            )

            val adapter = object : SimpleCursorAdapter(
                this,
                R.layout.item_persona,
                cursor,
                fromColumns,
                toViews,
                0
            ) {
                override fun getView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup?
                ): View {

                    return super.getView(position, convertView, parent)
                }
            }

            gridView.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}









