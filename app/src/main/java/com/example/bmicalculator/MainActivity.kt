package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageBoy = findViewById<ImageView>(R.id.iv_male)
        val imageGirl = findViewById<ImageView>(R.id.iv_female)
        val weight = findViewById<EditText>(R.id.et_weight)
        val height = findViewById<EditText>(R.id.et_height)
        val calculateButton = findViewById<Button>(R.id.btn_calculate_bmi)
        val bmi = findViewById<TextView>(R.id.tv_bmi)
        val bmiStatus = findViewById<TextView>(R.id.tv_bmi_status)
        val bmiView = findViewById<LinearLayout>(R.id.ll_bmi_view)
        val calculateAgain = findViewById<TextView>(R.id.tv_calculate_again)

        imageBoy.setOnClickListener {
            imageBoy.setImageResource(R.drawable.ic_male)
            imageGirl.setImageResource(R.drawable.ic_female_blur)
        }
        imageGirl.setOnClickListener {
            imageGirl.setImageResource(R.drawable.ic_female)
            imageBoy.setImageResource(R.drawable.ic_male_blur)
        }

        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0

            if (weight.text.toString().isNotEmpty()){
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()){
                heightValue = (height.text.toString().toDouble() / 100)
            }

            if (weightValue > 0.0 && heightValue > 0.0){
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))

                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))

                bmiView.visibility = View.VISIBLE
                calculateButton.visibility = View.GONE
            } else {
                Toast.makeText(this, "Please enter weight and height values greater than zero", Toast.LENGTH_SHORT).show()
            }
        }

        calculateAgain.setOnClickListener {
            bmiView.visibility = View.GONE
            calculateButton.visibility = View.VISIBLE
            weight.text.clear()
            height.text.clear()
            weight.requestFocus()
        }
    }
    private fun bmiStatusValue(bmi: Double): String {
       lateinit var bmiStatus: String
       if (bmi < 18.5)
           bmiStatus = "Under Weight"
       else if (bmi >= 18.5 && bmi < 25)
           bmiStatus = "Normal"
       else if (bmi >= 25 && bmi < 30)
            bmiStatus = "Over Weight"
       else if (bmi > 30)
            bmiStatus = "Obese"

        return bmiStatus
    }
}