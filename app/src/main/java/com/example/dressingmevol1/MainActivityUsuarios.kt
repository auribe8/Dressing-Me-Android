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
        val cursor = datasource.consultarUsuarios()

        while (cursor.moveToNext())
        {
            val columnas = Usuario(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getString(4)
            )
            registros.add(columnas)
        }

        val adaptador = AdaptadorUsuarios(this, registros)
        listausuarios.adapter = adaptador

        listausuarios.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Usuario
            val intent = Intent(this@MainActivityUsuarios, DetalleUsuarios:: class.java)

            intent.putExtra("id", item.iD_USUARIO)
            intent.putExtra("nombre", item.nombrE_USUARIO)
            intent.putExtra("telefono", item.telefonO_USUARIO)
            intent.putExtra("correo", item.correO_USUARIO)
            intent.putExtra("contraseña", item.contraseñA_USUARIO)

            startActivity(intent)
        }
    }

    fun AgregarUsuarios(view:View){
        val intent = Intent(this@MainActivityUsuarios, DetalleUsuarios:: class.java)
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

    internal class AdaptadorUsuarios(context: Context, datos:List<Usuario>):
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
                inflater.lblTitulo.text = currentEntity.descripcioN_USUARIO
            }

            return inflater
        }

    }

}