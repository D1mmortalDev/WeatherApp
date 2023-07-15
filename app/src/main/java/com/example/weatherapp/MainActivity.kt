package com.example.weatherapp





import android.content.DialogInterface
import com.example.weatherapp.databinding.ActivityMainBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.view.WindowId
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedValue:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //for toggleButton
        binding.toggleButton.setOnClickListener {
            if(binding.toggleButton.isChecked){
                selectedValue = removeLastAddedValue(selectedValue, "Temperature: Fahrenheit\n")
                selectedValue+="Temperature: Celcius\n"

            }
            else{
                selectedValue = removeLastAddedValue(selectedValue, "Temperature: Celcius\n")
                selectedValue+="Temperature: Fahrenheit\n"
            }
        }

        //for radio buttons -__-
    binding.radioGroup2.setOnCheckedChangeListener{_,checkedId->
    when(checkedId){
        R.id.radioButton->{
            Toast.makeText(applicationContext,"Option 1",Toast.LENGTH_SHORT).show()
            selectedValue+= "Picked: Option 1\n"
        }
        R.id.radioButton2->{
            Toast.makeText(applicationContext,"Option 2",Toast.LENGTH_SHORT).show()
            selectedValue = removeLastAddedValue(selectedValue, "Picked: Option 1\n")
            selectedValue+= "Picked: Option 2\n"
        }
    }
}

        //for checkbox Cloudy
        binding.checkBoxCloudy.setOnClickListener {
            if(binding.checkBoxCloudy.isChecked){
                Toast.makeText(applicationContext,"Cloudy",Toast.LENGTH_SHORT).show()
                selectedValue+= "Weather: Cloudy\n"

            }
            else{

                Toast.makeText(applicationContext,"Unchecked Cloudy",Toast.LENGTH_SHORT).show()
                selectedValue = removeLastAddedValue(selectedValue, "Weather: Cloudy\n")
            }
        }
        //for checkbox Cloudy
        binding.checkBoxRainy.setOnClickListener {
            if(binding.checkBoxRainy.isChecked){
                Toast.makeText(applicationContext,"Rainy",Toast.LENGTH_SHORT).show()
                selectedValue+= "Weather: Rainy\n"
            }
            else{
                Toast.makeText(applicationContext,"Unchecked Rainy",Toast.LENGTH_SHORT).show()
                selectedValue = removeLastAddedValue(selectedValue, "Weather: Rainy\n")
            }
        }
        //for checkbox Sunny
        binding.checkBoxSunny.setOnClickListener {
            if(binding.checkBoxSunny.isChecked){
                Toast.makeText(applicationContext,"Sunny",Toast.LENGTH_SHORT).show()
                selectedValue+= "Weather: Sunny\n"
            }
            else{
                Toast.makeText(applicationContext,"Unchecked Sunny",Toast.LENGTH_SHORT).show()
                selectedValue = removeLastAddedValue(selectedValue, "Weather: Sunny\n")
            }
        }

        //spinner
        val spinnerItems = arrayOf("Default","New York", "London","Tokyo")
        val adapter= ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spinner.adapter= adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = spinnerItems[position]
                Toast.makeText(applicationContext,selectedItem,Toast.LENGTH_SHORT).show()

                if(selectedItem=="Default"){
                    Toast.makeText(applicationContext,"Default is selected",Toast.LENGTH_SHORT).show()
                }
                else{
                    selectedValue+="Location: $selectedItem\n"
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //only will leave blank for now
            }

        }
        //for frame layout
        binding.frameLayout.setOnClickListener{
            showDialog()
        }
    }

    private fun showDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Weather Update")
        alertDialogBuilder.setMessage(selectedValue.trim())


        alertDialogBuilder.setPositiveButton("OK"){ _: DialogInterface, _:Int->
            Toast.makeText(applicationContext,"OK is clicked",Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("CANCEL"){ _: DialogInterface, _:Int->
            Toast.makeText(applicationContext,"Cancel is clicked",Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.create().show()
    }

    private fun removeLastAddedValue(originalValue: String, valueToRemove: String): String {
        if (originalValue.endsWith(valueToRemove)) {
            return originalValue.substring(0, originalValue.length - valueToRemove.length)
        }
        return originalValue
    }

}
