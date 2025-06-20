package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class Planta_Frigorifica_form : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planta_frigorifica_form)

        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener {
            showDatePickerDialog()
        }

        val spinnerRonda = findViewById<Spinner>(R.id.spinnerRonda)
        val rondas = arrayOf("Ronda 1", "Ronda 2", "Ronda 3", "Ronda 4")
        val adapterRonda = ArrayAdapter(this, android.R.layout.simple_spinner_item, rondas)
        adapterRonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRonda.adapter = adapterRonda

        val spinnerSGI = findViewById<Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI

        val seekBarFrecuencia = findViewById<SeekBar>(R.id.seekBarFrecuencia)
        val etFrecuencia = findViewById<EditText>(R.id.etFrecuencia)

        seekBarFrecuencia.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    etFrecuencia.setText(progress.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        etFrecuencia.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val value = s.toString().toInt()
                    if (value in seekBarFrecuencia.min..seekBarFrecuencia.max) {
                        if (etFrecuencia.isFocused) {
                            seekBarFrecuencia.progress = value
                        }
                    }
                } catch (e: NumberFormatException) {

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Configurar RadioGroup para Compresores
        val radioGroupCompresor = findViewById<RadioGroup>(R.id.radioGroupCompresor)
        radioGroupCompresor.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioCompresor1 -> {
                    // Lógica cuando se selecciona Compresor 1
                }
                R.id.radioCompresor2 -> {
                    // Lógica cuando se selecciona Compresor 2
                }
            }
        }

        // Configurar botones de acción
        findViewById<Button>(R.id.btnEnviar).setOnClickListener {
            // Lógica para enviar el formulario
        }

        findViewById<Button>(R.id.btnBorrador).setOnClickListener {
            // Lógica para guardar como borrador
        }

        findViewById<Button>(R.id.btnDescartar).setOnClickListener {
            // Lógica para descartar el formulario
            finish()
        }
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
                btnFecha.text = "$selectedDay-${selectedMonth + 1}-$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}