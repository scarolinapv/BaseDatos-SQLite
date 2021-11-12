package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tbl_conductor.*

import java.util.ArrayList

class tblConductor : AppCompatActivity() {

    var idConductor = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tbl_conductor)

        Regreso2.setOnClickListener {
            val intent3: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent3)
        }
        MostrarConductores()

        btnInsertarConductor.setOnClickListener {
            val conductor = CONDUCTOR(this)

            conductor.nombre = CampoNombre.text.toString()
            conductor.domicilio = CampoDomicilio.text.toString()
            conductor.nolicencia = CampoNoLicenciaa.text.toString()
            conductor.vence = CampoVence.text.toString()

            val resultado = conductor.insertar()

            if (resultado){
                Toast.makeText(this,"EXITO SE INSERTO", Toast.LENGTH_LONG).show()
                CampoNombre.text.clear()
                CampoDomicilio.text.clear()
                CampoNoLicenciaa.text.clear()
                CampoVence.text.clear()
                MostrarConductores()
            } else {
                Toast.makeText(this,"ERROR NO SE INSERTO", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun MostrarConductores(){
        val arregloConductor = CONDUCTOR(this).consultar()

        listaC.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloConductor)
        idConductor.clear()
        idConductor = CONDUCTOR(this).obtenerIDs()
        activarEvento(listaC)
    }




    fun activarEvento(ListaConductor: ListView){
        ListaConductor.setOnItemClickListener { adapterView, view, i, l ->

            val idSeleccionado = idConductor[i]
            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("QUE DESEA HACER CON EL CONDUCTOR")
                .setPositiveButton("EDITAR"){d,i->}
                .setNegativeButton("ELIMINAR"){d,i-> eliminar(idSeleccionado)}
                .setNeutralButton("CANCELAR"){d,i->
                    d.cancel()
                }
                .show()
        }

    }
    fun eliminar(idSeleccionado:Int){
        AlertDialog.Builder(this)
            .setTitle("IMPORTANTE")
            .setMessage("SEGURO QUE DESEAS ELIMINAR ID ${idSeleccionado}")
            .setPositiveButton("SI"){d,i->
                val resultado = CONDUCTOR(this).eliminar(idSeleccionado)

                if (resultado){
                    Toast.makeText(this,"SE ELIMINO CON EXITO",Toast.LENGTH_LONG).show()
                    MostrarConductores()
                } else {
                    Toast.makeText(this,"NO SE LOGRO ELIMINAR",Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton("NO"){d,i->
                d.cancel()
            }
            .show()
    }


}