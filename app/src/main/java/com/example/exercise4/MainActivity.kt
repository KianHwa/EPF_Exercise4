package com.example.exercise4

import android.app.DatePickerDialog
import android.icu.util.LocaleData
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*







class MainActivity : AppCompatActivity() {

    var dateBtn : Button? = null
    var cal = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateBtn = findViewById(R.id.btnDate)
        val message = findViewById<TextView>(R.id.tvMessage)
        var allowableInvestment : Double = 0.0

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
        }

        dateBtn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })


        btnCalculate.setOnClickListener {
            val myFormat = "dd-MM-yyyy"
            val sdf = SimpleDateFormat(myFormat)

            val currentDate = sdf.format(Date())
            val dob = sdf.format(cal.time)

            val now = sdf.parse(currentDate.toString())
            val before = sdf.parse(dob.toString())


            var ages = (now.time  - before.time)/1000/60/60/24/30/12
            var valid = true

                if (ages >= 16 && ages <= 20)
                    allowableInvestment = 5000 * 0.3
                else if(ages >= 21 && ages <=25)
                    allowableInvestment = 14000 * 0.3
                else if(ages >= 26 && ages <=30 )
                    allowableInvestment = 29000 * 0.3
                else if(ages >= 31 && ages <=35 )
                    allowableInvestment = 50000 * 0.3
                else if(ages >= 36 && ages <=40 )
                    allowableInvestment = 78000 * 0.3
                else if(ages >= 41 && ages <=45 )
                    allowableInvestment = 116000 * 0.3
                else if(ages >= 46 && ages <=50 )
                    allowableInvestment = 165000 * 0.3
                else if(ages>= 51 && ages <= 55 )
                    allowableInvestment = 228000 * 0.3
                else
                    valid = false


            if(valid == true)
                message!!.text = "Your age : " + ages.toString()  + "\n" + "Your allowable investment is RM " + allowableInvestment
            else
                message!!.text = "Your age : " + ages.toString()  + "\n" + "Sorry you are not applicable for EPF"

        }
    }


}
