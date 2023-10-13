package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Spinner
import android.widget.Button
import com.example.greenhouse_iot.databinding.ActivityHistoryBinding
import com.example.greenhouse_iot.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class History : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //back button
        var button = findViewById<Button>(R.id.btn_bck_H)
        button.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        //readings
         binding.btnUpdate.setOnClickListener {

             //val Parameter = binding.Parameters.text.toString()
             val spinner: Spinner = findViewById(R.id.spnr_year)
             val year = spinner.selectedItem.toString()
             val spinner2: Spinner = findViewById(R.id.spnr_month)
             val month = spinner2.selectedItem.toString()
             val spinner3: Spinner = findViewById(R.id.spnr_day)
             val day = spinner3.selectedItem.toString()
             val reading: String = "$year-$month-$day"
             readDataT(reading)
             readDataH(reading)
             readDataSM(reading)
             readDataMS(reading)
             readDataWS(reading)
         }


        // access the items of the list
        val day = resources.getStringArray(R.array.day)
        val month = resources.getStringArray(R.array.month)
        val year = resources.getStringArray(R.array.year)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spnr_day)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, day)

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@History,
                        getString(R.string.selected_item) + " " +
                                "" + day[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val spinner2 = findViewById<Spinner>(R.id.spnr_month)
        if (spinner2 != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, month)
            spinner2.adapter = adapter

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@History,
                        getString(R.string.selected_item) + " " +
                                "" + month[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val spinner3 = findViewById<Spinner>(R.id.spnr_year)
        if (spinner3 != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, year)
            spinner3.adapter = adapter

            spinner3.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@History,
                        getString(R.string.selected_item) + " " +
                                "" + year[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    //Read Data function Temperature
    private fun readDataT(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("History/DHT11/Humidity")
        database.child(reading).get().addOnSuccessListener {
            if (it.exists()){
                val Temp0 = it.child("0").value
                val Temp4 = it.child("4").value
                val Temp8 = it.child("8").value
                val Temp12 = it.child("12").value
                val Temp16 = it.child("16").value
                val Temp20 = it.child("20").value
                val Temp24 = it.child("24").value

                Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show()

                binding.temp0.text = Temp0.toString()
                binding.temp4.text = Temp4.toString()
                binding.temp8.text = Temp8.toString()
                binding.temp12.text = Temp12.toString()
                binding.temp16.text = Temp16.toString()
                binding.temp20.text = Temp20.toString()
                binding.temp24.text = Temp24.toString()




            }else{

                Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }
    //Read Data function Humidity
    private fun readDataH(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("History/DHT11/Temperature")
        database.child(reading).get().addOnSuccessListener {
            if (it.exists()){
                val hum0 = it.child("0").value
                val hum4 = it.child("4").value
                val hum8 = it.child("8").value
                val hum12 = it.child("12").value
                val hum16 = it.child("16").value
                val hum20 = it.child("20").value
                val hum24 = it.child("24").value

                Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show()

                binding.hum0.text = hum0.toString()
                binding.hum4.text = hum4.toString()
                binding.hum8.text = hum8.toString()
                binding.hum12.text = hum12.toString()
                binding.hum16.text = hum16.toString()
                binding.hum20.text = hum20.toString()
                binding.hum24.text = hum24.toString()




            }else{

                Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }
    //Read Data function Soil Moisture
    private fun readDataSM(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("History/Soil Moisture Sensor/Moisture")
        database.child(reading).get().addOnSuccessListener {
            if (it.exists()){
                val soil0 = it.child("0").value
                val soil4 = it.child("4").value
                val soil8 = it.child("8").value
                val soil12 = it.child("12").value
                val soil16 = it.child("16").value
                val soil20 = it.child("20").value
                val soil24 = it.child("24").value

                Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show()

                binding.soil0.text = soil0.toString()
                binding.soil4.text = soil4.toString()
                binding.soil8.text = soil8.toString()
                binding.soil12.text = soil12.toString()
                binding.soil16.text = soil16.toString()
                binding.soil20.text = soil20.toString()
                binding.soil24.text = soil24.toString()




            }else{

                Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }
    //Read Data function Motion Sensor
    private fun readDataMS(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("History/HC-SR04/Water Level")
        database.child(reading).get().addOnSuccessListener {
            if (it.exists()){
                val mot0 = it.child("0").value
                val mot4 = it.child("4").value
                val mot8 = it.child("8").value
                val mot12 = it.child("12").value
                val mot16 = it.child("16").value
                val mot20 = it.child("20").value
                val mot24 = it.child("24").value

                Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show()

                binding.mot0.text = mot0.toString()
                binding.mot4.text = mot4.toString()
                binding.mot8.text = mot8.toString()
                binding.mot12.text = mot12.toString()
                binding.mot16.text = mot16.toString()
                binding.mot20.text = mot20.toString()
                binding.mot24.text = mot24.toString()




            }else{

                Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }
    //Read Data function Wind Sensor
    private fun readDataWS(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("History/Wind Speed Sensor/Wind Speed")
        database.child(reading).get().addOnSuccessListener {
            if (it.exists()){
                val wind0 = it.child("0").value
                val wind4 = it.child("4").value
                val wind8 = it.child("8").value
                val wind12 = it.child("12").value
                val wind16 = it.child("16").value
                val wind20 = it.child("20").value
                val wind24 = it.child("24").value

                Toast.makeText(this,"Update Success",Toast.LENGTH_SHORT).show()

                binding.wind0.text = wind0.toString()
                binding.wind4.text = wind4.toString()
                binding.wind8.text = wind8.toString()
                binding.wind12.text = wind12.toString()
                binding.wind16.text = wind16.toString()
                binding.wind20.text = wind20.toString()
                binding.wind24.text = wind24.toString()




            }else{

                Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

            }

        }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }
}