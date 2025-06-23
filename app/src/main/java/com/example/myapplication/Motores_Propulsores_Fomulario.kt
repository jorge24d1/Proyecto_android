package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
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

        // Configuración de los formularios desplegables
        setupMotorForms()
    }

    private fun setupMotorForms() {
        // Referencias a los botones y formularios
        val btnBabor = findViewById<Button>(R.id.btnBabor)
        val btnEstribor = findViewById<Button>(R.id.btnEstribor)
        val btnCentro = findViewById<Button>(R.id.btnCentro)

        val formBabor = findViewById<LinearLayout>(R.id.formBabor)
        val formEstribor = findViewById<LinearLayout>(R.id.formEstribor)
        val formCentro = findViewById<LinearLayout>(R.id.formCentro)

        // Configurar listeners para los botones de los motores
        btnBabor.setOnClickListener {
            toggleForm(formBabor)
            collapseOtherForms(formBabor, formEstribor, formCentro)
        }

        btnEstribor.setOnClickListener {
            toggleForm(formEstribor)
            collapseOtherForms(formEstribor, formBabor, formCentro)
        }

        btnCentro.setOnClickListener {
            toggleForm(formCentro)
            collapseOtherForms(formCentro, formBabor, formEstribor)
        }

        // Configurar botones de acción
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        val btnBorrador = findViewById<Button>(R.id.btnBorrador)
        val btnDescartar = findViewById<Button>(R.id.btnDescartar)

        btnEnviar.setOnClickListener {
            // Lógica para enviar los datos
            Toast.makeText(this, "Datos enviados", Toast.LENGTH_SHORT).show()
        }

        btnBorrador.setOnClickListener {
            // Lógica para guardar como borrador
            Toast.makeText(this, "Guardado como borrador", Toast.LENGTH_SHORT).show()
        }

        btnDescartar.setOnClickListener {
            // Lógica para descartar los cambios
            Toast.makeText(this, "Cambios descartados", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun toggleForm(form: LinearLayout) {
        if (form.visibility == View.VISIBLE) {
            form.visibility = View.GONE
        } else {
            form.visibility = View.VISIBLE
        }
    }

    private fun collapseOtherForms(formToShow: LinearLayout, vararg otherForms: LinearLayout) {
        for (form in otherForms) {
            if (form != formToShow) {
                form.visibility = View.GONE
            }
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
                btnFecha.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    // Función para obtener los datos de un formulario de motor
    private fun getMotorData(formPrefix: String): Map<String, String> {
        val resources = resources
        val packageName = packageName

        return mapOf(
            "trim" to findViewById<EditText>(resources.getIdentifier("etTrim$formPrefix", "id", packageName)).text.toString(),
            "consumo" to findViewById<EditText>(resources.getIdentifier("etConsumo$formPrefix", "id", packageName)).text.toString(),
            "rpm" to findViewById<EditText>(resources.getIdentifier("etRPM$formPrefix", "id", packageName)).text.toString(),
            // Agrega aquí todos los demás campos del formulario
            "observaciones" to findViewById<EditText>(resources.getIdentifier("etObservaciones$formPrefix", "id", packageName)).text.toString()
        )
    }

    // Función para validar los datos de un motor
    private fun validateMotorData(data: Map<String, String>): Boolean {
        // Implementa la lógica de validación según tus requisitos
        return data.all { (_, value) -> value.isNotBlank() }
    }
}