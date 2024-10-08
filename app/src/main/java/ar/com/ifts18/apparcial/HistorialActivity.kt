package ar.com.ifts18.apparcial

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        // aqui va el codigo
        val LIMITE_LISTA = 5
        var listaDatos: ArrayList<String>
        var adapter: ArrayAdapter<String>


        val misPreferencias = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val hNombre = misPreferencias.getString("username", null)
        val hUsername = misPreferencias.getString("userlastname", null)
        val hPerfil = misPreferencias.getString("perfil", "moderado")
        val hMonto = misPreferencias.getString("monto", "0")
        val fullinfo = "$hNombre $hUsername $hPerfil $hMonto"


        listaDatos = arrayListOf()
        //ID DE COLUMNAS RELACIONADAS CON CADA VIEW
        val columnName = findViewById<ListView>(R.id.columnNombre)
        val columnLastName = findViewById<ListView>(R.id.columnApellido)
        val columnInversor = findViewById<ListView>(R.id.columnInversor)
        val columnMonto = findViewById<ListView>(R.id.columnMonto)

        // Recuperamos las listas almacenadas en las SharedPreferences
        val historialPreferences = getSharedPreferences("HistorialPreferences", MODE_PRIVATE)

        val listaMontos = historialPreferences.getStringSet("montos", mutableSetOf())?.toList() ?: listOf()
        val listaPlazos = historialPreferences.getStringSet("plazos", mutableSetOf())?.toList() ?: listOf()
        val listaBancos = historialPreferences.getStringSet("bancos", mutableSetOf())?.toList() ?: listOf()
        val listaIntereses = historialPreferences.getStringSet("intereses", mutableSetOf())?.toList() ?: listOf()
        val listaROIs = historialPreferences.getStringSet("rois", mutableSetOf())?.toList() ?: listOf()

        // HASTA AQUI LISTADO DE DATOS CDE NUEVO SHARED PREFERENCES

        //LISTAS
        val listaName = arrayListOf<String>()
        val listaLastName = arrayListOf<String>()
        val listColumnInversor = arrayListOf<String>()
        val listMonto = arrayListOf<String>()

        //AGREGAR VALORES A LA LISTA
        listaName.add(hNombre ?: "N/A")
        listaLastName.add(hUsername ?: "N/A")
        listColumnInversor.add(hPerfil ?: "N/A")
        listMonto.add(hMonto ?: "N/A")

        //ADAPTAR CAMBIOS A LAS COLUMNAS
        val adapterName = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaName)
        val adapterLastName = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaLastName)
        val adapterInversor = ArrayAdapter(this, android.R.layout.simple_list_item_1, listColumnInversor)
        val adapterMonto = ArrayAdapter(this, android.R.layout.simple_list_item_1, listMonto)
        columnName.adapter = adapterName
        columnLastName.adapter = adapterLastName
        columnInversor.adapter = adapterInversor
        columnMonto.adapter = adapterMonto


        //agregarElemento(listaName, "NuevoNombre", adapterName)
        //agregarElemento(listaLastName, "NuevoApellido", adapterLastName)
        //agregarElemento(listColumnInversor, "NuevoPerfil", adapterInversor)
        //agregarElemento(listMonto, "NuevoMonto", adapterMonto)

        // Agregamos el nuevo elemento a la lista

        val buttonRegresar = findViewById<Button>(R.id.buttonRegresar)
        buttonRegresar.setOnClickListener {
            irAlMain()
            // Regresa a MainActivity
            //finish() // regresar al main para que ingrese otro usuario sus datos
        }

        fun agregarElemento(lista: ArrayList<String>, nuevoValor: String, adapter: ArrayAdapter<String>) {
            if (lista.size >= 5) {
                // Elimina el primer elemento para hacer espacio al nuevo
                lista.removeAt(0)
            }
            // Añade el nuevo valor al final de la lista
            lista.add(nuevoValor)

            // Notifica al adaptador que los datos han cambiado
            adapter.notifyDataSetChanged()
        }
    }
    private fun irAlMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
