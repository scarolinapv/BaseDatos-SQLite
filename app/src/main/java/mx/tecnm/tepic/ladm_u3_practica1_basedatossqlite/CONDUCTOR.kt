package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite

import android.content.ContentValues

class CONDUCTOR(p: tblConductor) {
    var nombre = ""
    var domicilio = ""
    var nolicencia = ""
    var vence = ""
    val pnt = p

    fun insertar() : Boolean {

        val tablaConductor = BaseDatos(pnt,"Sabritas",null,1).writableDatabase

        val datos = ContentValues()

        datos.put("nombre",nombre)
        datos.put("domicilio",domicilio)
        datos.put("nolicencia",nolicencia)
        datos.put("vence",vence)

        val resultado = tablaConductor.insert("Conductor",null,datos)
        //el metodo insert regresa un ID unico de renglon de tabla, regresa -1 si no se pudo

        if (resultado == -1L){
            return false
        }

        return true
    }//insertar

    fun consultar() : ArrayList<String>{
        //SELECT * FROM ARTISTA
        val tablaConductor = BaseDatos(pnt,"Sabritas",null,1).readableDatabase
        val resultado = ArrayList<String>()

        val cursor = tablaConductor.query("Conductor", arrayOf("*"),null,null,null,null,null)

        if (cursor.moveToFirst()){
            do {
                var dato = cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3)+"\n"+cursor.getString(4)
                resultado.add(dato)
            }while (cursor.moveToNext())
        } else {
            resultado.add("NO SE ENCONTRO DATA A MOSTRAR")
        }

        return resultado
    }//consultar



    fun obtenerIDs() : ArrayList<Int>{
        val tablaConductor = BaseDatos(pnt,"Sabritas",null,1).readableDatabase
        val resultado = ArrayList<Int>()

        val cursor = tablaConductor.query("Conductor", arrayOf("*"),null,null,null,null,null)

        if (cursor.moveToFirst()){
            do {
                resultado.add(cursor.getInt(0))
            }while (cursor.moveToNext())
        }

        return resultado
    }//consultar

    fun eliminar(idEliminar:Int):Boolean{
        val tablaConductor = BaseDatos(pnt,"Sabritas",null,1).writableDatabase

        val resultado = tablaConductor.delete("Conductor","ID=?", arrayOf(idEliminar.toString()))

        if (resultado == 0){
            return false
        }
        return true

    }
}