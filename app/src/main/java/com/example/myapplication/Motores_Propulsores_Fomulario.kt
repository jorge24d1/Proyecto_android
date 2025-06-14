package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class Motores_Propulsores_Fomulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motores_propulsores_fomulario) // Asegúrate que este sea el nombre correcto de tu layout

        // Configuración del botón de fecha (igual que en tu código funcional)
        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener {
            showDatePickerDialog()
        }

        // Configuración del spinner de Ronda (igual que en tu código)
        val spinnerRonda = findViewById<android.widget.Spinner>(R.id.spinnerRonda)
        val rondas = arrayOf("Ronda 1", "Ronda 2", "Ronda 3", "Ronda 4")
        val adapterRonda = ArrayAdapter(this, android.R.layout.simple_spinner_item, rondas)
        adapterRonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRonda.adapter = adapterRonda

        // Configuración del spinner de SGI (igual que en tu código)
        val spinnerSGI = findViewById<android.widget.Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI

        // No añadimos lógica adicional como pediste
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val btnFecha = findViewById<Button>(R.id.btnFecha)
                btnFecha.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}