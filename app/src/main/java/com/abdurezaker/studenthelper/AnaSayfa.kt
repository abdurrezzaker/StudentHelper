package com.abdurezaker.studenthelper

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.abdurezaker.studenthelper.fragments.CalenderFragment
import com.abdurezaker.studenthelper.fragments.ChatFragment
import com.abdurezaker.studenthelper.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class AnaSayfa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ana_sayfa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val HomeFragment = HomeFragment()
        val CalenderFragment = CalenderFragment()
        val ChatFragment = ChatFragment()

        makeCurrentFragment(HomeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navbar)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.ic_home -> makeCurrentFragment(HomeFragment)
                R.id.ic_calender -> makeCurrentFragment(CalenderFragment)
                R.id.ic_chat -> makeCurrentFragment(ChatFragment)
            }
            true

        }

    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}