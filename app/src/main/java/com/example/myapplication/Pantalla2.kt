package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Pantalla2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla2)

        val btnBack = findViewById<Button>(R.id.button2)
        btnBack.setOnClickListener {
            finish()
        }

        val btnEnviar = findViewById<Button>(R.id.button)
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)

        btnEnviar.setOnClickListener {
            val nombre = editNombre.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                guardarEnExcel(nombre, email, password)
                Toast.makeText(this, "Datos guardados en archivo Excel", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarEnExcel(nombre: String, email: String, password: String) {
        try {
            val fileName = "datos_usuarios.xlsx"
            val file = File(getExternalFilesDir(null), fileName)

            val workbook = if (file.exists()) {
                // Si el archivo existe, lo abrimos
                FileInputStream(file).use { fis ->
                    WorkbookFactory.create(fis)
                }
            } else {
                // Si no existe, creamos uno nuevo
                XSSFWorkbook()
            }

            // Obtenemos o creamos la hoja "Usuarios"
            val sheet = workbook.getSheet("Usuarios") ?: workbook.createSheet("Usuarios")

            // Si es un nuevo archivo, añadimos los encabezados
            if (sheet.lastRowNum == -1) {
                val headerRow = sheet.createRow(0)
                headerRow.createCell(0).setCellValue("Nombre")
                headerRow.createCell(1).setCellValue("Email")
                headerRow.createCell(2).setCellValue("Contraseña")
            }

            // Creamos una nueva fila con los datos
            val newRow = sheet.createRow(sheet.lastRowNum + 1)
            newRow.createCell(0).setCellValue(nombre)
            newRow.createCell(1).setCellValue(email)
            newRow.createCell(2).setCellValue(password)

            // Guardamos el archivo
            FileOutputStream(file).use { fos ->
                workbook.write(fos)
            }

            workbook.close()

            Toast.makeText(this, "Datos añadidos al archivo Excel", Toast.LENGTH_SHORT).show()
            println("Ruta del archivo: ${file.absolutePath}")

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}