package com.abdurezaker.studenthelper

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.abdurezaker.studenthelper.fragments.CalenderFragment
import com.abdurezaker.studenthelper.fragments.ChatFragment
import com.abdurezaker.studenthelper.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AnaSayfa : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ana_sayfa)
        auth= Firebase.auth



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
    fun signOut(view : View){
        auth.signOut()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    //menu işlemleri
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.helper_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.sign_out){

        }
        return super.onOptionsItemSelected(item)
    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }




}