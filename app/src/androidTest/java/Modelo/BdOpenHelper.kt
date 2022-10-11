package Modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "DressingMe.db"
val DATABASE_VERSION = 1

class BdOpenHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(PrendasDataSource.CREATE_EVENTOS_SCRIPT)
        db.execSQL(PrendasDataSource.INSERT_EVENTOS_SCRIPT)

        db!!.execSQL(OutfitsDataSource.CREATE_EVENTOS_SCRIPT)
        db.execSQL(OutfitsDataSource.INSERT_EVENTOS_SCRIPT)

        db!!.execSQL(EstadisticasDataSource.CREATE_EVENTOS_SCRIPT)
        db.execSQL(EstadisticasDataSource.INSERT_EVENTOS_SCRIPT)

        db!!.execSQL(UsuariosDataSource.CREATE_USUARIOS_SCRIPT)
        db.execSQL(UsuariosDataSource.INSERT_USUARIOS_SCRIPT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented")
    }

}