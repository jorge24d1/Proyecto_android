package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Crear array con los datos
        val dataArray = arrayOf("null","MA1", "MA2", "S3", "52")

        // Obtener referencia al Spinner
        val spinner = findViewById<Spinner>(R.id.spinnerCargo)

        // Crear ArrayAdapter usando el array y un layout por defecto para los items
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dataArray
        )

        // Especificar el layout a usar cuando aparezca la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Aplicar el adapter al Spinner
        spinner.adapter = adapter
    }

}