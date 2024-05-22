package com.abdurezaker.studenthelper

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abdurezaker.studenthelper.databinding.ActivityPlanDuzenleBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PlanDuzenle : AppCompatActivity() {

    private lateinit var binding: ActivityPlanDuzenleBinding;
    private var planName: String? = null
    private var planDate: String? = null
    private var planTime: String? = null
    private var formattedDate: String? = null
    private var explantation: String? = null
    private var myPLansDataBase: SQLiteDatabase? = null

    private val calender = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPlanDuzenleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            myPLansDataBase = this.openOrCreateDatabase("Plans", Context.MODE_PRIVATE,null)

            myPLansDataBase?.execSQL("CREATE TABLE IF NOT EXISTS plans (id INTEGER PRIMARY KEY, name VARCHAR," +
                    "date VARCHAR,time VARCHAR,explantation VARCHAR)")

        }catch (e:Exception){
            e.printStackTrace()
        }




    }
    fun date(view: View){
        val datePickerDialog = DatePickerDialog(this,{DatePicker, year: Int,mothOfYear: Int, dayOfMonth: Int ->
            val selectedDate: Calendar = Calendar.getInstance()
            selectedDate.set(year,mothOfYear,dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            formattedDate = dateFormat.format(selectedDate.time)

        },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
            )
        datePickerDialog.show()
    }
    fun anaSayfa(view : View){
        val intent = Intent(this,AnaSayfa::class.java)
        startActivity(intent)
        finish()
    }
    fun ekle(view : View){

        val eklemeSorgusu = "INSERT INTO plans (name, date,time,explantation) VALUES(?,?,?,?)"

        planName = binding.NameText.text.toString()
        planDate = formattedDate
        planTime = binding.timeText.text.toString()
        explantation = binding.statementText.toString()
        if(planName != null && planDate != null && planTime != null && explantation != null && myPLansDataBase != null){
            myPLansDataBase?.let {db ->
                val statement = db.compileStatement(eklemeSorgusu)
                statement.bindString(1, planName)
                statement.bindString(2, planDate)
                statement.bindString(3, planTime)
                statement.bindString(4, explantation)
                statement.execute()
                val intent = Intent(this,AnaSayfa::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}
