package com.example.dressingmevol1

import Modelo.UsuariosDataSource
import android.os.Bundle
import android.view.View
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_detalle_usuarios.*

class DetalleUsuarios : AppCompatActivity() {

    private lateinit var datasource: UsuariosDataSource

    private var id = 0
    private var nombre = ""
    private var telefono = 0
    private var correo = ""
    private var contraseña = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_usuarios)

        datasource = UsuariosDataSource(this)

        val b = this.intent.extras
        if (b != null){
            id = b.getInt("id")
            nombre = b.getString("nombre").toString()
            telefono = b.getInt("telefono")
            correo = b.getString("correo").toString()
            contraseña = b.getString("contraseña").toString()

            txtNombre.setText(nombre)
            txtTelefono.setText(telefono)
            txtCorreo.setText(correo)
            txtContraseña.setText(contraseña)
        }
    }

    fun eliminar(view: View){
        if (datasource.eliminarUsuario(id)){
            val toast = Toast.makeText(applicationContext, "Se realizo correctamente", Toast.LENGTH_SHORT)
            toast.show()
            txtNombre.setText("")
            txtTelefono.setText("")
            txtCorreo.setText("")
            txtContraseña.setText("")
        }
    }

    fun GuardarUsuario(view: View){
        if (id !== 0)
        {
            //editar
            datasource.modificarEvento(txtNombre.text.toString(),txtTelefono.text.toString(),txtCorreo.text.toString(),txtContraseña.text.toString(), id)
            val toast = Toast.makeText(applicationContext, "Se editó correctamente", Toast.LENGTH_SHORT)
            toast.show()
        }
        else
        {
            datasource.guardarEvento(txtNombre.text.toString(),txtTelefono.text.toString(),txtCorreo.text.toString(),txtContraseña.text.toString())
            val toast = Toast.makeText(applicationContext, "Se guardó correctamente", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}