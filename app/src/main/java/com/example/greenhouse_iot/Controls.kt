package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.*
import com.example.greenhouse_iot.databinding.ActivityConfigureBinding
import com.example.greenhouse_iot.databinding.ActivityControlsBinding
import com.google.firebase.database.*
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
        val manual1 = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/manual")
        val onoff = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/on_Off")
        manual1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton2.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

        onoff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

                    myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            val On_Off: Boolean = true
                            // ToggleButton is in the ON state
                            val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/on_Off")
                            databaseReference.setValue(On_Off)
                            myToggleButton.isEnabled = false
                            Toast.makeText(this,"Activated. Wait 11 seconds",Toast.LENGTH_SHORT).show()
                            val handler = Handler()

                            handler.postDelayed({
                                // Your action to be executed after the delay
                                // This code block will run after the specified delayMillis
                                myToggleButton.isEnabled = true
                            }, 11000)



                        } else {
                            val On_Off: Boolean = false
                            // ToggleButton is in the ON state
                            val databaseReference = FirebaseDatabase.getInstance().getReference("Manual Control/Windows/on_Off")
                            databaseReference.setValue(On_Off)

                            myToggleButton.isEnabled = false
                            Toast.makeText(this,"Deactivated. Wait 11 seconds",Toast.LENGTH_SHORT).show()
                            val handler = Handler()

                            handler.postDelayed({
                                // Your action to be executed after the delay
                                // This code block will run after the specified delayMillis
                                myToggleButton.isEnabled = true
                            }, 11000)


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






    private fun senddata2(control: String) {
        // call toggle button
        val myToggleButton = findViewById<ToggleButton>(R.id.toggleButtonW)
        database = FirebaseDatabase.getInstance().getReference("Manual Control")
        val myToggleButton2 = findViewById<ToggleButton>(R.id.tb_manWater)
        val manual1 = FirebaseDatabase.getInstance().getReference("Manual Control/Water Pump/manual")
        val onoff = FirebaseDatabase.getInstance().getReference("Manual Control/Water Pump/on_Off")
        manual1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton2.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

        onoff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/on_Off")
                        databaseReference.setValue(On_Off)
                        Toast.makeText(this,"Activated.",Toast.LENGTH_SHORT).show()



                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Water Pump/on_Off")
                        databaseReference.setValue(On_Off)

                        Toast.makeText(this,"Deactivated.",Toast.LENGTH_SHORT).show()


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
        val manual1 = FirebaseDatabase.getInstance().getReference("Manual Control/Blinds/manual")
        val onoff = FirebaseDatabase.getInstance().getReference("Manual Control/Blinds/on_Off")
        manual1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton2.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

        onoff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })


                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Activated. Wait 3 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 3000)


                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Blinds/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Deactivated. Wait 3 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 3000)

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
        val manual1 = FirebaseDatabase.getInstance().getReference("Manual Control/Plots/manual")
        val onoff = FirebaseDatabase.getInstance().getReference("Manual Control/Plots/on_Off")
        manual1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton2.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })

        onoff.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called when data changes in the specified path.
                // You can retrieve the value using dataSnapshot.getValue()

                val value = dataSnapshot.getValue(Boolean::class.java) // Assuming it's a boolean value

                // Now, 'value' contains the retrieved value from the database
                // Update the ToggleButton state accordingly
                myToggleButton.isChecked = value ?: false // If value is null, default to false
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that may occur while retrieving data
            }
        })


                myToggleButton.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        val On_Off: Boolean = true
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Activated. Wait 15 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 15000)


                    } else {
                        val On_Off: Boolean = false
                        // ToggleButton is in the ON state
                        val databaseReference = FirebaseDatabase.getInstance()
                            .getReference("Manual Control/Plots/on_Off")
                        databaseReference.setValue(On_Off)
                        myToggleButton.isEnabled = false
                        Toast.makeText(this,"Deactivated. Wait 15 seconds",Toast.LENGTH_SHORT).show()
                        val handler = Handler()

                        handler.postDelayed({
                            // Your action to be executed after the delay
                            // This code block will run after the specified delayMillis
                            myToggleButton.isEnabled = true
                            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                        }, 15000)

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

