package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class form1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form1)

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
        setupTimePicker()

        // Configuración del Spinner SGI
        val spinnerSGI = findViewById<Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI

        // Configuración de los formularios desplegables
        findViewById<View>(R.id.btnManejadora1).setOnClickListener {
            toggleForm(R.id.formManejadora1)
        }

        findViewById<View>(R.id.btnManejadora2).setOnClickListener {
            toggleForm(R.id.formManejadora2)
        }

        findViewById<View>(R.id.btnManejadora3).setOnClickListener {
            toggleForm(R.id.formManejadora3)
        }

        findViewById<View>(R.id.btnManejadora4).setOnClickListener {
            toggleForm(R.id.formManejadora4)
        }

        findViewById<View>(R.id.btnCasette).setOnClickListener {
            toggleForm(R.id.formCasette)
        }

        setupCounterButtons()
    }

    private fun setupTimePicker() {
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
    }

    private fun toggleForm(formId: Int) {
        val form = findViewById<View>(formId)
        form.visibility = if (form.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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

    private fun setupCounterButtons() {
        setupSingleCounter(R.id.btnIncrementar1, R.id.tvContador1)
        setupSingleCounter(R.id.btnIncrementar2, R.id.tvContador2)
        setupSingleCounter(R.id.btnIncrementar3, R.id.tvContador3)
        setupSingleCounter(R.id.btnIncrementar4, R.id.tvContador4)
        setupSingleCounter(R.id.btnIncrementarCasette, R.id.tvContadorCasette)
    }

    private fun setupSingleCounter(buttonId: Int, counterId: Int) {
        findViewById<Button>(buttonId).setOnClickListener {
            val tvCounter = findViewById<TextView>(counterId)
            val currentValue = tvCounter.text.toString().toIntOrNull() ?: 0
            tvCounter.text = (currentValue + 1).toString()
        }
    }
}