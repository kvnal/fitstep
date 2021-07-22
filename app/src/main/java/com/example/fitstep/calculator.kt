package com.example.fitstep

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*


class calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        var spinner_gender :Spinner = findViewById(R.id.spinner_gender)
        var gender : String="Gender"

        val edit_age : EditText = findViewById(R.id.edit_age)
        val arr = arrayOf("Gender","Male","Female")

        textView.visibility = View.INVISIBLE
        textView3.visibility = View.INVISIBLE

   spinner_gender.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr)

        spinner_gender.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                gender="Gender"
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gender= arr[position]


            }

        }



        btn_check.setOnClickListener { it: View? ->
            //keyboard close
            try{
            val imm: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(btn_check.getWindowToken(), 0)}
            catch (ex: Exception){
            }


            try {

                val calorie_cal=calorie(gender,edit_age.text.toString().toInt(),edit_height.text.toString().toInt(),edit_weight.text.toString().toInt())
                val bmi_cal:Array<String> = bmi(edit_height.text.toString().toInt(),edit_weight.text.toString().toInt())

                textView.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE
                txt_bmi.text=bmi_cal[0]
                txt_bmi_status.text=bmi_cal[1]
                txt_calorie.text= calorie_cal.toString()
                txt_required.text="Required"

            }
            catch (ex: Exception ){
                Toast.makeText(this, "Invalid Information", Toast.LENGTH_LONG).show()

            }



        }
    }

    fun bmi(height : Int ,weight : Int ): Array<String> {
        var final = arrayOf("bmi","bmi_status")

        if (height<=0 || weight<=0){ return final }

            val bmi: Float = ((weight*100*100 )/(height*height)).toFloat()
            final[0] =String.format("%.1f",bmi)
            //check status
            if ( bmi<=18.5){
                final[1]="Underweight"
                txt_bmi_status.setTextColor(Color.parseColor("#F8A203")) //yellow
            }
            else if(18.5<bmi && bmi<=24.5){
                final[1]="Healthy"
                txt_bmi_status.setTextColor(Color.parseColor("#038342")) //green
            }
            else if(bmi>=25){
                final[1]="Overweight"
               txt_bmi_status.setTextColor(Color.parseColor("#D02C1F")) //red
            }
        Log.d("TAG", "bmi: final : ${final} ")


            return final
        }


    fun calorie(gender:String, age:Int, height: Int, weight:Int) : Int {

        if(gender=="Gender"){
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
        }

        Log.d("TAG", "gender $gender age $age height $height weight $weight ")
        if (age <=0 || age>=80 || height<=0||weight<=0 )
        {            return 0}

        var calorie : Int=0

        if (gender=="Male") {

            calorie = (13.3*weight+4.79*height/100-5.6*age+88.36).toInt()

        }
        else if(gender=="Female")
        {
            calorie= (9.24*weight+3.09*height/100-4.33*age+447.5).toInt()
        }
        Log.d("TAG", "calorie: $calorie $gender  ")

        //1.55 for moderate workout 5_6 days
        return (calorie*1.55).toInt() //change 1.55 by the rank&bagde system later refer downloaded pic


    }


}

