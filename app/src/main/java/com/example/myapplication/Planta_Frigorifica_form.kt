package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class Planta_Frigorifica_form : AppCompatActivity() {

    private var contadorCompresor1 = 0
    private var contadorCompresor2 = 0
    private var compresorSeleccionado: Int = 0 // 0 = ninguno, 1 = compresor1, 2 = compresor2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planta_frigorifica_form)

        // Configuración de fecha
        val btnFecha = findViewById<Button>(R.id.btnFecha)
        btnFecha.setOnClickListener {
            showDatePickerDialog()
        }

        // Configuración de spinner de ronda
        val spinnerRonda = findViewById<Spinner>(R.id.spinnerRonda)
        val rondas = arrayOf("Ronda 1", "Ronda 2", "Ronda 3", "Ronda 4")
        val adapterRonda = ArrayAdapter(this, android.R.layout.simple_spinner_item, rondas)
        adapterRonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRonda.adapter = adapterRonda

        // Configuración de spinner SGI
        val spinnerSGI = findViewById<Spinner>(R.id.spinnerSGI)
        val sgiOpciones = arrayOf("SGI 1", "SGI 2", "SGI 3")
        val adapterSGI = ArrayAdapter(this, android.R.layout.simple_spinner_item, sgiOpciones)
        adapterSGI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSGI.adapter = adapterSGI

        // Configuración de seekbar de frecuencia
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
                    // Ignorar errores de conversión
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Configuración del contador de compresores
        val radioGroupCompresor = findViewById<RadioGroup>(R.id.radioGroupCompresor)
        val layoutContador = findViewById<LinearLayout>(R.id.layoutContadorCompresor)
        val tvTituloContador = findViewById<TextView>(R.id.tvTituloContador)
        val tvContador = findViewById<TextView>(R.id.tvContadorCompresor)
        val btnIncrementar = findViewById<Button>(R.id.btnIncrementarCompresor)

        // Elementos dinámicos
        val tvPresionSuccionTitulo = findViewById<TextView>(R.id.tvPresionSuccionTitulo)
        val etPresionSuccion = findViewById<EditText>(R.id.etPresionSuccion)
        val tvPresionDescargaTitulo = findViewById<TextView>(R.id.tvPresionDescargaTitulo)
        val etPresionDescarga = findViewById<EditText>(R.id.etPresionDescarga)

        radioGroupCompresor.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioCompresor1 -> {
                    compresorSeleccionado = 1
                    layoutContador.visibility = View.VISIBLE
                    tvTituloContador.text = "Contador Compresor 1 *"
                    tvContador.text = contadorCompresor1.toString()

                    // Mostrar y actualizar campos dinámicos
                    tvPresionSuccionTitulo.visibility = View.VISIBLE
                    tvPresionSuccionTitulo.text = "P. Succión Compresor 1"
                    etPresionSuccion.visibility = View.VISIBLE

                    tvPresionDescargaTitulo.visibility = View.VISIBLE
                    tvPresionDescargaTitulo.text = "P. Descarga Compresor 1"
                    etPresionDescarga.visibility = View.VISIBLE
                }
                R.id.radioCompresor2 -> {
                    compresorSeleccionado = 2
                    layoutContador.visibility = View.VISIBLE
                    tvTituloContador.text = "Contador Compresor 2 *"
                    tvContador.text = contadorCompresor2.toString()

                    // Mostrar y actualizar campos dinámicos
                    tvPresionSuccionTitulo.visibility = View.VISIBLE
                    tvPresionSuccionTitulo.text = "P. Succión Compresor 2"
                    etPresionSuccion.visibility = View.VISIBLE

                    tvPresionDescargaTitulo.visibility = View.VISIBLE
                    tvPresionDescargaTitulo.text = "P. Descarga Compresor 2"
                    etPresionDescarga.visibility = View.VISIBLE
                }
                else -> {
                    compresorSeleccionado = 0
                    layoutContador.visibility = View.GONE

                    // Ocultar campos dinámicos
                    tvPresionSuccionTitulo.visibility = View.GONE
                    etPresionSuccion.visibility = View.GONE
                    tvPresionDescargaTitulo.visibility = View.GONE
                    etPresionDescarga.visibility = View.GONE
                }
            }
        }

        btnIncrementar.setOnClickListener {
            when (compresorSeleccionado) {
                1 -> {
                    contadorCompresor1++
                    tvContador.text = contadorCompresor1.toString()
                }
                2 -> {
                    contadorCompresor2++
                    tvContador.text = contadorCompresor2.toString()
                }
            }
        }

        // Configuración de botones de acción
        findViewById<Button>(R.id.btnEnviar).setOnClickListener {
            // Lógica para enviar el formulario
        }

        findViewById<Button>(R.id.btnBorrador).setOnClickListener {
            // Lógica para guardar como borrador
        }

        findViewById<Button>(R.id.btnDescartar).setOnClickListener {
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