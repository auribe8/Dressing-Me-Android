package com.example.dressingmevol1

import Entidades.Usuario
import Modelo.UsuarioDataSource
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.NonNull
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lista_usuarios.view.*

class MainActivityUsuarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LlenarInformacion()

    }


    fun LlenarInformacion(){
        val datasource = UsuariosDataSource(this)

        val registros = ArrayList<Usuario>()
        val cursor = datasource.consultarEventos()

        while (cursor.moveToNext())
        {
            val columnas = Usuario(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            )
            registros.add(columnas)
        }

        val adaptador = AdaptadorEventos(this, registros)
        listausuarios.adapter = adaptador

        listausuarios.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Usuario
            val intent = Intent(this@MainActivity, DetalleUsuarios:: class.java)

            intent.putExtra("id", item.iD_EVENTO)
            intent.putExtra("dia", item.diA_EVENTO)
            intent.putExtra("descripcion", item.descripcioN_EVENTO)

            startActivity(intent)
        }
    }

    fun AgregarEventos(view:View){
        val intent = Intent(this@MainActivity, DetalleUsuarios:: class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        this.LlenarInformacion()
    }

    override fun onResume() {
        super.onResume()
        this.LlenarInformacion()
    }

    internal class AdaptadorEventos(context: Context, datos:List<Usuario>):
        ArrayAdapter<Usuario>(context, R.layout.lista_usuarios, datos) {
        var _datos: List<Usuario>

        init {
            _datos = datos
        }

        @NonNull
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflater = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.lista_usuarios, parent, false)
            val currentEntity = getItem(position)

            if (currentEntity != null) {
                inflater.LblTitulo.text = currentEntity.descripcioN_EVENTO
            }

            return inflater
        }

    }

}