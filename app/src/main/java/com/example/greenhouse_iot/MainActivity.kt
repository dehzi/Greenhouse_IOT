package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.greenhouse_iot.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)


        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        auth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Do database operations here
            } else {
                // Handle any errors
                Log.e("FirebaseAuth", "signInAnonymously:failure", task.exception)
            }
        }
        //readings
        // binding.btnUpdate.setOnClickListener {
        val reading: String = binding.textReadings.text.toString()
        //     readData(reading)
        // }

        //update reading every 1 minute
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                // Call your readData(reading) function here
                readData(reading)
            }
        }
        // Schedule the task to run every 1 minute
        timer.scheduleAtFixedRate(task, 0, 500) // 0 milliseconds delay, 5-minute interval



        //navigation button

        var button = findViewById<Button>(R.id.btn_history)
        button.setOnClickListener {
            val intent1 = Intent(this, History::class.java)
            startActivity(intent1)
        }
        var button2 = findViewById<Button>(R.id.btn_parameters)
        button2.setOnClickListener {
            val intent2 = Intent(this, Configure::class.java)
            startActivity(intent2)


        }
        var button3 = findViewById<Button>(R.id.btn_controls)
        button3.setOnClickListener {
            val intent3 = Intent(this, Controls::class.java)
            startActivity(intent3)


        }
    }
    //Read Data function
    private fun readData(reading: String) {
        database = FirebaseDatabase.getInstance().getReference("Readings")
        database.child(reading).get().addOnSuccessListener {
        if (it.exists()){
            val Temperature = it.child("Temperature").value
            val Humidity = it.child("Humidity").value
            val SoilMoisture = it.child("Moisture").value
            val Motion = it.child("Water Level").value
            val WindSpeed = it.child("Wind Speed").value


            binding.textTemp.text = Temperature.toString()
            binding.textHumid.text = Humidity.toString()
            binding.textSoil.text = SoilMoisture.toString()
            binding.textMot.text = Motion.toString()
            binding.textWind.text = WindSpeed.toString()




        }else{

            Toast.makeText(this,"Does not Exist",Toast.LENGTH_SHORT).show()

        }

    }.addOnFailureListener {

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

        }
    }

}