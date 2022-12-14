package Modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.lang.Exception

class UsuariosDataSource(context: Context) {
    private val openHelper:BdOpenHelper = BdOpenHelper(context)
    private val database:SQLiteDatabase
    object ColumnUsuarios {
        var ID_USUARIO = BaseColumns._ID
        var NOMBRE_USUARIO = "nombre"
        var TELEFONO_USUARIO = "telefono"
        var CORREO_USUARIO = "correo"
        var CONTRASEÑA_USUARIO = "contraseña"
    }
    init {
        database = openHelper.writableDatabase
    }

    fun consultarUsuarios(): Cursor {
        return database.rawQuery("select _id,nombre,telefono,correo,contraseña from $USUARIOS_TABLE_NAME", null)
    }

    fun consultarUsuarios(idUsuario:Int):Cursor{
        return database.rawQuery("select _id,nombre,telefono,correo,contraseña from $USUARIOS_TABLE_NAME where _id = $idUsuario", null)
    }

    fun guardarEvento(nombre:String, telefono:Int,correo:String,contraseña:String){
        val values = ContentValues()
        values.put(ColumnUsuarios.NOMBRE_USUARIO,nombre)
        values.put(ColumnUsuarios.TELEFONO_USUARIO,telefono)
        values.put(ColumnUsuarios.CORREO_USUARIO,correo)
        values.put(ColumnUsuarios.CONTRASEÑA_USUARIO,contraseña)
        database.insert(USUARIOS_TABLE_NAME, null, values)
    }

    fun modificarEvento(nombre:String, telefono:Int,correo:String,contraseña:String,IdUsuario: Int){
        val values = ContentValues()
        values.put(ColumnUsuarios.NOMBRE_USUARIO,nombre)
        values.put(ColumnUsuarios.TELEFONO_USUARIO,telefono)
        values.put(ColumnUsuarios.CORREO_USUARIO,correo)
        values.put(ColumnUsuarios.CONTRASEÑA_USUARIO,contraseña)
        val a = arrayOf("" + IdUsuario)
        database.update(USUARIOS_TABLE_NAME, values, "_id=?", a)
    }

    fun eliminarEvento(IdUsuario:Int):Boolean{
        val whereArgs = arrayOf(""+IdUsuario)
        try {
            database.delete(USUARIOS_TABLE_NAME, "_id=?", whereArgs)
            return true
        }
        catch (ex:Exception){
            return false
        }
        finally {
            database.close()
        }
    }

    companion object{
        val USUARIOS_TABLE_NAME ="Usuarios"
        val STRING_TYPE = "text"
        val INT_TYPE = "integer"
        val CREATE_USUARIOS_SCRIPT = (
                "create table " + USUARIOS_TABLE_NAME + "(" +
                        ColumnUsuarios.ID_USUARIO + " " + INT_TYPE + " primary key autoincrement," +
                        ColumnUsuarios.NOMBRE_USUARIO + " " + STRING_TYPE + "not null," +
                        ColumnUsuarios.TELEFONO_USUARIO + " " + INT_TYPE + "not null," +
                        ColumnUsuarios.CORREO_USUARIO + " " + STRING_TYPE + "not null," +
                        ColumnUsuarios.CONTRASEÑA_USUARIO + " " + STRING_TYPE + "not null)")
        val INSERT_USUARIOS_SCRIPT = (
                "insert into " + USUARIOS_TABLE_NAME + " values " +
                        "(null, 'Alessia Uribe Colunga', 477572912, 'auc74142@udelasalle.edu.mx', 'akdjdhhs547'), "+
                        "(null, 'Pedro Osnaya Guzman', 47728192712, 'pog71339@udelasalle.edu.mx', 'jdj861h'), "+
                        "(null, 'Martha Alessandra Izquierdo Almanza', 4770823712, 'mia79383@udelasalle.edu.mx', 'k927efif8')")
    }
}