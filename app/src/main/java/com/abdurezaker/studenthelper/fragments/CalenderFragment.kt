@file:Suppress("UnusedImport")

package com.abdurezaker.studenthelper.fragments

import android.content.Context
import android.content.Intent
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
import com.abdurezaker.studenthelper.AnaSayfa
import com.abdurezaker.studenthelper.MainKayitOl
import com.abdurezaker.studenthelper.PlanDuzenle
import com.abdurezaker.studenthelper.ProgramDuzenle
import com.abdurezaker.studenthelper.databinding.FragmentCalenderBinding
import java.lang.Exception


class CalenderFragment: DialogFragment() {



    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }*/

    private var _binding: FragmentCalenderBinding? = null
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

        viewListView()

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

        binding.listView.setOnItemLongClickListener { parent, view, position, id ->

            val slectedItem = binding.listView.getItemAtPosition(position).toString()
           
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
                        myDataBase.execSQL("DELETE FROM programs where dersAdi='$slectedItem'")

                        viewListView()


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

    }
    fun viewListView(){
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
                dersIsimler.add(dersAdi)
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


