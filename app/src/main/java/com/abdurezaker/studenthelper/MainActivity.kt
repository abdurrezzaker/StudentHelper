package com.abdurezaker.studenthelper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdurezaker.studenthelper.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        enableEdgeToEdge()
        setContentView(view)
        auth =Firebase.auth
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val intent=Intent(this@MainActivity,AnaSayfa::class.java)
            startActivity(intent)
            finish()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }


    fun signInCliked(view:View){
        val email = binding.editTextText.text.toString()
        val password=binding.editTextText1.text.toString()

        if(email.equals("")|| password.equals("")){

            Toast.makeText(this,"Email veya şifre boş bırakılamaz!",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@MainActivity,AnaSayfa::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
    fun signUpCliked(view : View){
        val email =binding.editTextText.text.toString()
        val password=binding.editTextText1.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Email veya şifre boş bırakılamaz!",Toast.LENGTH_LONG).show()
        } else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this@MainActivity,AnaSayfa::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }

        }
    }
}