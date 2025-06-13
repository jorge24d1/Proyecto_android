package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class form1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form1)

        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener {
            showDatePickerDialog()
        }

        val spinnerRonda = findViewById<android.widget.Spinner>(R.id.spinnerRonda)
        val rondas = arrayOf("Ronda 1", "Ronda 2", "Ronda 3", "Ronda 4")
        val adapterRonda = ArrayAdapter(this, android.R.layout.simple_spinner_item, rondas)
        adapterRonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRonda.adapter = adapterRonda

        val spinnerSGI = findViewById<android.widget.Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI

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
            val tvCounter = findViewById<android.widget.TextView>(counterId)
            val currentValue = tvCounter.text.toString().toIntOrNull() ?: 0
            tvCounter.text = (currentValue + 1).toString()
        }
    }
}