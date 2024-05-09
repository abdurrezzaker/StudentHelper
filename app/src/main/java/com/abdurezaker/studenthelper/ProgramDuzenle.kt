@file:Suppress("UnusedImport")

package com.abdurezaker.studenthelper

import android.content.Intent
import android.view.LayoutInflater
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdurezaker.studenthelper.fragments.CalenderFragment

class ProgramDuzenle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_program_duzenle)
        val kbutton=findViewById<Button>(R.id.buttonkaydet)//giris butonu

        kbutton.setOnClickListener{
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