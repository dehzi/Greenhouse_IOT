package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.greenhouse_iot.databinding.ActivityConfigureBinding
import com.example.greenhouse_iot.databinding.ActivityControlsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        database.child(control).get().addOnSuccessListener {
            if (it.exists()) {
                val manual = it.child("manual").value
                val manual2 = manual.toString()

                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // ToggleButton is in the ON state
                        val On_Off: String = "true"
                        //sending data to database
                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // ToggleButton is in the OFF state
                        val On_Off: String = "false"
                        //sending data to database
                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun senddata2(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonW)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        database.child(control).get().addOnSuccessListener {
            if (it.exists()) {
                val manual = it.child("manual").value
                val manual2 = manual.toString()
        myToggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // ToggleButton is in the ON state
                val On_Off: String = "true"
                //sending data to database
                val Cont = Cont(On_Off,manual2)
                database.child(control).setValue(Cont).addOnSuccessListener {
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                // ToggleButton is in the OFF state
                val On_Off: String = "false"
                //sending data to database

                val Cont = Cont(On_Off,manual2)
                database.child(control).setValue(Cont).addOnSuccessListener {
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
            }
            }
        }
    }

    private fun senddata3(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonSS)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        database.child(control).get().addOnSuccessListener {
            if (it.exists()) {
                val manual = it.child("manual").value
                val manual2 = manual.toString()
                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // ToggleButton is in the ON state
                        val On_Off: String = "true"
                        //sending data to database

                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // ToggleButton is in the OFF state
                        val On_Off: String = "false"
                        //sending data to database
                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun senddata4(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonPS)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        database.child(control).get().addOnSuccessListener {
            if (it.exists()) {
                val manual = it.child("manual").value
                val manual2 = manual.toString()
                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // ToggleButton is in the ON state
                        val On_Off: String = "true"
                        //sending data to database

                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // ToggleButton is in the OFF state
                        val On_Off: String = "false"
                        //sending data to database
                        val Cont = Cont(On_Off, manual2)
                        database.child(control).setValue(Cont).addOnSuccessListener {
                            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}