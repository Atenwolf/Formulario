package jose.machuca.formulariovista

import MostrarDatosActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFormulario = findViewById<Button>(R.id.BtnFormulario)
        btnFormulario.setOnClickListener {
            redirigir(MainActivity2::class.java)
        }

        val btnBBDD = findViewById<Button>(R.id.btnbbdd)
        btnBBDD.setOnClickListener {
            redirigir(MostrarDatosActivity::class.java)
        }
    }

    private fun redirigir(destinationActivity: Class<*>) {
        val intent = Intent(this, destinationActivity)
        startActivity(intent)
    }
}
