package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tbl_conductor.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var idConductor1 = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonConductor.setOnClickListener {
            val intent:Intent = Intent(this,tblConductor::class.java)
            startActivity(intent)
        }

        buttonVehiculo.setOnClickListener {
            val intent4:Intent = Intent(this,tblVehiculo::class.java)
            startActivity(intent4)
        }
    }

}
