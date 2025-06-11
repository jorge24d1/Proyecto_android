package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btn = findViewById<ImageButton>(R.id.imageButton)


        btn.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }

        val btn2 = findViewById<ImageButton>(R.id.imageButton2)


        btn2.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }
        val btn3 = findViewById<ImageButton>(R.id.imageButton3)


        btn3.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }
        val btn4 = findViewById<ImageButton>(R.id.imageButton4)


        btn4.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }
        val btn5 = findViewById<ImageButton>(R.id.imageButton5)


        btn5.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }
        val btn6 = findViewById<ImageButton>(R.id.imageButton6)


        btn6.setOnClickListener {
            val intent = Intent(this, Pantalla2::class.java)
            startActivity(intent)
        }
    }
}