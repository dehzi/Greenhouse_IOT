package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import com.example.greenhouse_iot.databinding.ActivityConfigureBinding
import com.example.greenhouse_iot.databinding.ActivityControlsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Controls : AppCompatActivity() {
    private lateinit var binding : ActivityControlsBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //back button
        var button = findViewById<Button>(R.id.btn_bck_C)
        button.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
        val control1: String = "Windows"
        val control2: String = "Water Pump"
        val control3: String = "Blinds"
        val control4: String = "Plots"

        senddata1(control1)
        senddata2(control2)
        senddata3(control3)
        senddata4(control4)

    }


    private fun senddata1(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonWS)
        val myToggleButton2 = findViewById<ToggleButton>(R.id.tb_manWindow)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        database.child("Windows").get().addOnSuccessListener {
            if (it.exists()) {
                val manual = it.child("manual").value
                val manual2 = manual.toString()
                val On_Off = it.child("on_Off").value



                    myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            val On_Off: Boolean = true
                            // ToggleButton is in the ON state
                            val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/on_Off")
                            databaseReference.setValue(On_Off)
                            myToggleButton.isEnabled = false
                            Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                            val handler = Handler()

                            handler.postDelayed({
                                // Your action to be executed after the delay
                                // This code block will run after the specified delayMillis
                                myToggleButton.isEnabled = true
                            }, 10000)



                        } else {
                            val On_Off: Boolean = false
                            // ToggleButton is in the ON state
                            val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/on_Off")
                            databaseReference.setValue(On_Off)

                            myToggleButton.isEnabled = false
                            Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                            val handler = Handler()

                            handler.postDelayed({
                                // Your action to be executed after the delay
                                // This code block will run after the specified delayMillis
                                myToggleButton.isEnabled = true
                            }, 10000)


                        }

                    }



                myToggleButton2.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val manual: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = true
                        Toast.makeText(this,"Manual activated",Toast.LENGTH_SHORT).show()

                    } else {
                        val manual: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Manual deactivated",Toast.LENGTH_SHORT).show()

                    }
                    }
                }
            }

    }



    private fun senddata2(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonW)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        val myToggleButton2 = findViewById<ToggleButton>(R.id.tb_manWater)



                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)


                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)

                    }

                }


                myToggleButton2.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val manual: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = true
                        Toast.makeText(this,"Manual activated",Toast.LENGTH_SHORT).show()


                    } else {
                        val manual: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Manual deactivated",Toast.LENGTH_SHORT).show()

                    }
                }


    }


    private fun senddata3(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonSS)
        val myToggleButton2 = findViewById<ToggleButton>(R.id.tb_manShade)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")



                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)


                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)

                    }

                }


                myToggleButton2.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val manual: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = true
                        Toast.makeText(this,"Manual activated",Toast.LENGTH_SHORT).show()


                    } else {
                        val manual: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Manual deactivated",Toast.LENGTH_SHORT).show()

                    }
                }


    }

    private fun senddata4(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonPS)
        val myToggleButton2 = findViewById<ToggleButton>(R.id.tb_manPlot)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")



                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)


                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Wait 10 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 10000)

                    }

                }


                myToggleButton2.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val manual: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = true
                        Toast.makeText(this,"Manual activated",Toast.LENGTH_SHORT).show()


                    } else {
                        val manual: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/manual")
                        databaseReference.setValue(manual)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Manual deactivated",Toast.LENGTH_SHORT).show()

                    }
                }


    }
}

