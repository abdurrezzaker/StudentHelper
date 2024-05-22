@file:Suppress("UnusedImport")

package com.abdurezaker.studenthelper.fragments

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.abdurezaker.studenthelper.R
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import com.abdurezaker.studenthelper.AnaSayfa
import com.abdurezaker.studenthelper.MainKayitOl
import com.abdurezaker.studenthelper.PlanDuzenle
import com.abdurezaker.studenthelper.ProgramDuzenle
import com.abdurezaker.studenthelper.databinding.FragmentCalenderBinding
import com.google.android.gms.common.api.internal.LifecycleActivity
import java.lang.Exception


class CalenderFragment: DialogFragment() {



    private var _binding: FragmentCalenderBinding? = null

    private var myPLansDataBase: SQLiteDatabase? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding =  FragmentCalenderBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programDuzenleClick = view.findViewById<Button>(R.id.programduzenle)
        programDuzenleClick.setOnClickListener {

            val intent = Intent(requireContext(), ProgramDuzenle::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val planEkleClick = view.findViewById<Button>(R.id.planekle)
        planEkleClick.setOnClickListener {
            val intent = Intent(requireContext(), PlanDuzenle::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        try {
            context?.let {
                myPLansDataBase = it.openOrCreateDatabase("Plans", Context.MODE_PRIVATE,null)

                myPLansDataBase?.execSQL("CREATE TABLE IF NOT EXISTS plans (id INTEGER PRIMARY KEY, name VARCHAR," +
                        "date VARCHAR,time VARCHAR,explantation VARCHAR)")
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
        programsListView()
        plansListView()


        binding.listView.setOnItemLongClickListener { parent, view, position, id ->

            val slectedItem = binding.listView.getItemAtPosition(position).toString()
            val list = slectedItem.split(" ")
            val array  = list.toTypedArray()
           
            val builder : AlertDialog.Builder = AlertDialog.Builder(requireContext())
                .setTitle("Silme Onayı")
                .setMessage("${slectedItem} programını silmek istediğinizden emin misiniz?")
                .setPositiveButton("Evet"){dialog,id ->
                    try {
                        val context = requireContext()
                        val myDataBase = context.openOrCreateDatabase("Programs", Context.MODE_PRIVATE, null)
                        myDataBase.execSQL(
                            "CREATE TABLE IF NOT EXISTS programs (id INTEGER PRIMARY KEY, gun VARCHAR," +
                                    "dersAdi VARCHAR,dersBaslangic VARCHAR,dersBitis VARCHAR)"
                        )
                        myDataBase.execSQL("DELETE FROM programs where id=${array[0]}")

                        programsListView()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .setNegativeButton("Hayır"){dialog,id ->

                }
            builder.show()
            Toast.makeText(requireContext(), "Uzun tıklandı: ${slectedItem}", Toast.LENGTH_SHORT).show()
            true
        }

        //***************************************************
        binding.listView2.setOnItemLongClickListener { parent, view, position, id ->

            val slectedItem = binding.listView2.getItemAtPosition(position).toString()

            val list = slectedItem.split(" ")
            val array = list.toTypedArray()

            val builder : AlertDialog.Builder = AlertDialog.Builder(requireContext())
                .setTitle("Silme Onayı")
                .setMessage("${slectedItem} planı silmek istediğinizden emin misiniz?")
                .setPositiveButton("Evet"){dialog,id ->
                    myPLansDataBase?.let {
                        it.execSQL("DELETE FROM plans where id = ${array[0]}")
                        plansListView()
                    }
                }
                .setNegativeButton("Hayır"){dialog,id ->

                }
            builder.show()

            true
        }

    }
    fun plansListView(){
        var adapter: ArrayAdapter<String>? = null
        myPLansDataBase?.let {
            val  cursor = it.rawQuery("SELECT * FROM plans", null)
            var planNames = ArrayList<String>()
            while (cursor.moveToNext()){
                val planNamesID = cursor.getColumnIndex("name")
                val planName = cursor.getString(planNamesID).toString()
                val ID = cursor.getColumnIndex("id")
                val id = cursor.getString(ID).toString()
                planNames.add(id + " "+ planName )
            }
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,planNames)
            binding.listView2.adapter = adapter
            cursor.close()
        }

    }
    fun programsListView(){
        var adapter:ArrayAdapter<String>? = null
        try {
            val context = requireContext()
            val myDataBase = context.openOrCreateDatabase("Programs", Context.MODE_PRIVATE, null)
            myDataBase.execSQL(
                "CREATE TABLE IF NOT EXISTS programs (id INTEGER PRIMARY KEY, gun VARCHAR," +
                        "dersAdi VARCHAR,dersBaslangic VARCHAR,dersBitis VARCHAR)"
            )

            val cursor = myDataBase.rawQuery("SELECT * FROM programs", null)

            //myDataBase.execSQL("DROP TABLE IF EXISTS programs")

            var dersIsimler: ArrayList<String> = ArrayList<String>()

            while (cursor.moveToNext()) {
                val dersAdiID = cursor.getColumnIndex("dersAdi")
                val dersAdi = cursor.getString(dersAdiID).toString()
                val ID = cursor.getColumnIndex("id")
                val id = cursor.getString(ID)
                dersIsimler.add(id + " " +dersAdi)
            }
            adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, dersIsimler)
            binding.listView.adapter = adapter
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


