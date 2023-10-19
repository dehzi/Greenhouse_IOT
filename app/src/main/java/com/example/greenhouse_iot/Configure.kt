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
        binding.saveBtn.setOnClickListener {

            val minValue = binding.minValue.text.toString()
            val maxValue = binding.maxValue.text.toString()

            //val Parameter = binding.Parameters.text.toString()
            val spinner: Spinner = findViewById(R.id.spnr_parameter)
            val Parameter = spinner.selectedItem.toString()



            updateData(Parameter, minValue, maxValue)


        }


        //sending data to database
        binding.resetBtn.setOnClickListener{

            //parameter
            //val Parameter = binding.Parameters.text.toString()
            val spinner: Spinner = findViewById(R.id.spnr_parameter)
            val parameter = spinner.selectedItem.toString()

            updateData2(parameter)


                    binding.minValue.text.clear()
                    binding.maxValue.text.clear()



        }
        }




    private fun updateData(parameter: String, minValue: String, maxValue: String) {
        val minVal = minValue.toInt()
        val maxVal = maxValue.toInt()

        if (minVal<maxVal) {

                val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
                databaseReference.setValue(minValue)
                val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
                databaseReference2.setValue(maxValue)
                binding.minValue.text.clear()
                binding.maxValue.text.clear()
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()


        }else{
            Toast.makeText(this, "minValue must be lower than maxValue", Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateData2(parameter: String) {
        if (parameter=="Temperature"){
        //temperature
        val minValue: String = "15"
        val maxValue : String = "30"
            val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
            databaseReference.setValue(minValue)
            val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
            databaseReference2.setValue(maxValue)
        }else if (parameter=="Humidity") {
            //Humidity
            val minValue: String = "40"
            val maxValue: String = "60"
            val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
            databaseReference.setValue(minValue)
            val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
            databaseReference2.setValue(maxValue)
        }else if(parameter=="Soil Moisture") {
            //Soil Moisture
            val minValue: String = "50"
            val maxValue: String = "75"
            val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
            databaseReference.setValue(minValue)
            val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
            databaseReference2.setValue(maxValue)
        }else if(parameter=="Water Level") {
            //Water Level
            val minValue: String = "0"
            val maxValue: String = "5"
            val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
            databaseReference.setValue(minValue)
            val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
            databaseReference2.setValue(maxValue)
        }else if(parameter=="Wind Speed"){
        //wind speed
        val minValue: String = "0"
        val maxValue : String = "30"
            val databaseReference = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/minValue")
            databaseReference.setValue(minValue)
            val databaseReference2 = FirebaseDatabase.getInstance().getReference("Parameters/"+parameter+"/maxValue")
            databaseReference2.setValue(maxValue)
        }



            binding.minValue.text.clear()
            binding.maxValue.text.clear()
            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()


        }
    }

