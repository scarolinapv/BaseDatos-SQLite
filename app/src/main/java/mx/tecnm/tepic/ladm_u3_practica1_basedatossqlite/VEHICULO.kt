package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite

import android.content.ContentValues

class VEHICULO (p:tblVehiculo) {
    var placa = ""
    var marca = ""
    var modelo = ""
    var año = ""
    var idconductor = ""
    val pnt = p

    fun insertar() : Boolean {

        val tablaConductor = BaseDatos(pnt,"Sabritas",null,1).writableDatabase

        val datos = ContentValues()

        datos.put("placa",placa)
        datos.put("marca",marca)
        datos.put("modelo",modelo)
        datos.put("yearr",año)
        datos.put("idconductor",idconductor)
        val resultado = tablaConductor.insert("Vehiculo",null,datos)
        //el metodo insert regresa un ID unico de renglon de tabla, regresa -1 si no se pudo

        if (resultado == -1L){
            return false
        }

        return true
    }//insertar

    fun consultar() : ArrayList<String>{
        //SELECT * FROM ARTISTA
        val tablaVehiculo = BaseDatos(pnt,"Sabritas",null,1).readableDatabase
        val resultado = ArrayList<String>()

        val cursor = tablaVehiculo.query("Vehiculo", arrayOf("*"),null,null,null,null,null)

        if (cursor.moveToFirst()){
            do {
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)+"\n"+cursor.getString(5)
                resultado.add(dato)
            }while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
        }

        return resultado
    }//consultar

    fun obtenerIDs() : ArrayList<Int>{
        val tablaVehiculo= BaseDatos(pnt,"Sabritas",null,1).readableDatabase
        val resultado = ArrayList<Int>()

        val cursor = tablaVehiculo.query("Vehiculo", arrayOf("*"),null,null,null,null,null)

        if (cursor.moveToFirst()){
            do {
                resultado.add(cursor.getInt(0))
            }while (cursor.moveToNext())
        }

        return resultado
    }//consultar

    fun eliminar(idEliminar:Int):Boolean{
        val tablaVehiculo = BaseDatos(pnt,"Sabritas",null,1).writableDatabase

        val resultado = tablaVehiculo.delete("Vehiculo","ID=?", arrayOf(idEliminar.toString()))

        if (resultado == 0){
            return false
        }
        return true
    }
}