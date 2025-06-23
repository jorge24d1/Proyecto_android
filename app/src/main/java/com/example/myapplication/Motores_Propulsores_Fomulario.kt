package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Motores_Propulsores_Fomulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motores_propulsores_fomulario)

        // Configuración del selector de fecha
        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener {
            showDatePickerDialog()
        }

        // Configuración del Spinner de Ronda
        val spinnerRonda = findViewById<Spinner>(R.id.spinnerRonda)
        val rondas = arrayOf("Ronda 1", "Ronda 2", "Ronda 3", "Ronda 4")
        val adapterRonda = ArrayAdapter(this, android.R.layout.simple_spinner_item, rondas)
        adapterRonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRonda.adapter = adapterRonda

        // Configuración del selector de hora
        val npHour = findViewById<NumberPicker>(R.id.npHour)
        val npMinute = findViewById<NumberPicker>(R.id.npMinute)
        val btnAmPm = findViewById<Button>(R.id.btnAmPm)
        val btnHoraActual = findViewById<Button>(R.id.btnHoraActual)

        // Configurar NumberPicker para horas (formato 12 horas)
        npHour.minValue = 1
        npHour.maxValue = 12
        npHour.value = Calendar.getInstance().get(Calendar.HOUR).let {
            if (it == 0) 12 else it
        }

        // Configurar NumberPicker para minutos
        npMinute.minValue = 0
        npMinute.maxValue = 59
        npMinute.value = Calendar.getInstance().get(Calendar.MINUTE)

        // Configurar AM/PM basado en la hora actual
        val isPm = Calendar.getInstance().get(Calendar.AM_PM) == Calendar.PM
        btnAmPm.text = if (isPm) "PM" else "AM"

        // Alternar AM/PM al hacer clic
        btnAmPm.setOnClickListener {
            btnAmPm.text = if (btnAmPm.text == "AM") "PM" else "AM"
        }

        // Botón para establecer hora actual
        btnHoraActual.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            npHour.value = if (hour == 0) 12 else hour
            npMinute.value = calendar.get(Calendar.MINUTE)
            btnAmPm.text = if (calendar.get(Calendar.AM_PM) == Calendar.PM) "PM" else "AM"
        }

        // Configuración del Spinner SGI
        val spinnerSGI = findViewById<Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI
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