package com.example.greenhouse_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Spinner
import android.widget.Button
import com.example.greenhouse_iot.databinding.ActivityConfigureBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Configure : AppCompatActivity() {

    private lateinit var binding : ActivityConfigureBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // access the spinner
        val parameter = resources.getStringArray(R.array.parameter)

        val spinner = findViewById<Spinner>(R.id.spnr_parameter)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, parameter)

            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@Configure,
                        getString(R.string.selected_item) + " " +
                                "" + parameter[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        //back button
        var button = findViewById<Button>(R.id.btn_bck_P)
        button.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        //sending data to database
        binding.saveBtn.setOnClickListener{

            val minValue = binding.minValue.text.toString()
            val maxValue = binding.maxValue.text.toString()

            //val Parameter = binding.Parameters.text.toString()
            val spinner: Spinner = findViewById(R.id.spnr_parameter)
            val Parameter = spinner.selectedItem.toString()

            val minVal = minValue.toInt()
            val maxVal = maxValue.toInt()

            if (minVal<maxVal) {

                database = FirebaseDatabase.getInstance().getReference("Parameters")
                val Param = Param( minValue,maxValue)
                database.child(Parameter).setValue(Param).addOnSuccessListener {
                    binding.minValue.text.clear()
                    binding.maxValue.text.clear()


                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this, "minValue must be lower than maxValue", Toast.LENGTH_SHORT).show()
            }

        }




        //sending data to database
        binding.resetBtn.setOnClickListener{
            database = FirebaseDatabase.getInstance().getReference("Parameters")
            //temperature
            val minValue: String = "15"
            val maxValue : String = "30"
            //Humidity
            val minValue2: String = "40"
            val maxValue2 : String = "60"
            //Soil Moisture
            val minValue3: String = "50"
            val maxValue3 : String = "75"
            //Water Level
            val minValue4: String = "0"
            val maxValue4 : String = "5"
            //wind speed
            val minValue5: String = "0"
            val maxValue5 : String = "30"

                val Param = Param( minValue,maxValue)
                database.child("Temperature").setValue(Param).addOnSuccessListener {
                    val Param1 = Param( minValue2,maxValue2)
                    database.child("Humidity").setValue(Param1)
                    val Param2 = Param( minValue3,maxValue3)
                    database.child("Soil Moisture").setValue(Param2)
                    val Param3 = Param( minValue4,maxValue4)
                    database.child("Water Level").setValue(Param3)
                    val Param4 = Param( minValue5,maxValue5)
                    database.child("Wind Speed").setValue(Param4)

                    binding.minValue.text.clear()
                    binding.maxValue.text.clear()


                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {

                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }


        }
        }
    }
