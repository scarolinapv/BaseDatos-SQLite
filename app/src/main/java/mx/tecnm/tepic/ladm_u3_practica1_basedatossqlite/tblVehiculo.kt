package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tbl_vehiculo.*
import java.util.ArrayList

class tblVehiculo : AppCompatActivity() {
    var idVehiculo = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tbl_vehiculo)
        Regreso.setOnClickListener {
            val intent2: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }


        MostrarVehiculos()

        btnInsertarVehiculo.setOnClickListener {
            val vehiculo = VEHICULO(this)

            vehiculo.placa = CampoPlaca.text.toString()
            vehiculo.marca = CampoMarca.text.toString()
            vehiculo.modelo = CampoModelo.text.toString()
            vehiculo.año = CampoAño.text.toString()
            vehiculo.idconductor = CampoIDConductor.text.toString()

            val resultado = vehiculo.insertar()

            if (resultado){
                Toast.makeText(this,"EXITO SE INSERTO", Toast.LENGTH_LONG).show()
                CampoPlaca.text.clear()
                CampoMarca.text.clear()
                CampoModelo.text.clear()
                CampoAño.text.clear()
                CampoIDConductor.text.clear()
                MostrarVehiculos()
            } else {
                Toast.makeText(this,"ERROR NO SE INSERTO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun MostrarVehiculos(){
            val arregloVehiculo = VEHICULO(this).consultar()

        listaR.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arregloVehiculo)

        idVehiculo.clear()
        idVehiculo = VEHICULO(this).obtenerIDs()
        activarEvento(listaR)
    }
    fun activarEvento(ListaConductor: ListView){
        ListaConductor.setOnItemClickListener { adapterView, view, i, l ->

            val idSeleccionado = idVehiculo[i]
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
                val resultado = VEHICULO(this).eliminar(idSeleccionado)

                if (resultado){
                    Toast.makeText(this,"SE ELIMINO CON EXITO",Toast.LENGTH_LONG).show()
                    MostrarVehiculos()
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