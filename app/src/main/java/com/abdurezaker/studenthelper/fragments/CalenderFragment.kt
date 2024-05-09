@file:Suppress("UnusedImport")

package com.abdurezaker.studenthelper.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.abdurezaker.studenthelper.R
import androidx.appcompat.app.AppCompatActivity
import com.abdurezaker.studenthelper.AnaSayfa
import com.abdurezaker.studenthelper.MainKayitOl
import com.abdurezaker.studenthelper.ProgramDuzenle


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalenderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalenderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        val listView = view.findViewById<ListView>(R.id.listView)

        val items = arrayOf("Nesne Tabanlı","Yaz.Müh","Bilgisayar Ağları","İşletim Sistemleri")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        val button = view.findViewById<Button>(R.id.button7)
        button.setOnClickListener {
            // Yeni aktiviteye geçmek için Intent oluştur
            val intent = Intent(requireContext(), ProgramDuzenle::class.java)
            startActivity(intent)
        }

        return view





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalenderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalenderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


