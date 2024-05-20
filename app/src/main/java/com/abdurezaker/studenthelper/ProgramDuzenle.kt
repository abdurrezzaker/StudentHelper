@file:Suppress("UnusedImport")

package com.abdurezaker.studenthelper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdurezaker.studenthelper.databinding.ActivityProgramDuzenleBinding
import com.abdurezaker.studenthelper.fragments.CalenderFragment
import java.lang.Exception
import java.sql.Array
import java.util.Calendar

class ProgramDuzenle : AppCompatActivity() {

    private lateinit var binding: ActivityProgramDuzenleBinding
    private var gun: String = ""
    private var ders: String = ""
    private var dersBas: String = ""
    private var dersbit : String =""
    //var degerler: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProgramDuzenleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //val kbutton=findViewById<Button>(R.id.buttonkaydet)//kaydet butonu
        val xbutton=findViewById<Button>(R.id.buttonx)// iptal butonu


        xbutton.setOnClickListener {
            val intent = Intent(this,AnaSayfa::class.java)
            val cal = Calendar.getInstance()
            println(cal.get(Calendar.DAY_OF_WEEK))
            println(cal.get(Calendar.HOUR)+12)
            println(cal.get(Calendar.MINUTE))

            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun save(view: View){
        try {

            val myDataBase = this.openOrCreateDatabase("Programs",Context.MODE_PRIVATE,null)

            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS programs (id INTEGER PRIMARY KEY, gun VARCHAR," +
                    "dersAdi VARCHAR,dersBaslangic VARCHAR,dersBitis VARCHAR)")


            val eklemesorgusu = "INSERT INTO programs (gun,dersAdi,dersBaslangic,dersBitis) VALUES(?,?,?,?)"

            gun = binding.pzttext.text.toString()
            ders = binding.pztdersText.text.toString()
            dersBas = binding.pztbassaatText.text.toString()
            dersbit = binding.pztbitsaatText.text.toString()
            var degerler = arrayOf(gun,ders,dersBas,dersbit)
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.salitext.text.toString()
            ders = binding.salidersText.text.toString()
            dersBas = binding.salibassaatText.text.toString()
            dersbit = binding.salibitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.cartext.text.toString()
            ders = binding.cardersText.text.toString()
            dersBas = binding.carbassaatText.text.toString()
            dersbit = binding.carbitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.pertext.text.toString()
            ders = binding.perdersText.text.toString()
            dersBas = binding.perbassaatText.text.toString()
            dersbit = binding.perbitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.cumtext.text.toString()
            ders = binding.cumdersText.text.toString()
            dersBas = binding.cumbassaatText.text.toString()
            dersbit = binding.cumbitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.cmtstext.text.toString()
            ders = binding.cmtsdersText.text.toString()
            dersBas = binding.cmtsbassaatText.text.toString()
            dersbit = binding.cmtsbitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }


            gun = binding.pzrtext.text.toString()
            ders = binding.pzrdersText.text.toString()
            dersBas = binding.pzrbassaatText.text.toString()
            dersbit = binding.pzrbitsaatText.text.toString()
            if(gun != "" && ders != "" && dersBas != "" && dersbit != ""){
                degerler = arrayOf(gun,ders,dersBas,dersbit)
                myDataBase.execSQL(eklemesorgusu,degerler)
            }



        }catch (e: Exception){
            e.printStackTrace()
        }
        val intent = Intent(this,AnaSayfa::class.java)
        startActivity(intent)

    }
}