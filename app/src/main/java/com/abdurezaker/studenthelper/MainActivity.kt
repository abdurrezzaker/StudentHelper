package com.abdurezaker.studenthelper

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val kbutton=findViewById<Button>(R.id.button3)//kaydol butonu
        val gbutton=findViewById<Button>(R.id.button2)//giris butonu

        kbutton.setOnClickListener{
            val intent= Intent(this,MainKayitOl::class.java)

            startActivity(intent)
        }

        gbutton.setOnClickListener {
            val intent= Intent(this,AnaSayfa::class.java)

            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}