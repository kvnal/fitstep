package com.example.fitstep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_stopwatch.*

class stopwatch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        val chrono : Chronometer= findViewById(R.id.chrono_text)
        val reset: Button = findViewById(R.id.btn_reset)

        var init : Boolean = true
        var timee :Long =0


        var toggle_btn : ToggleButton = findViewById(R.id.toggle_btn)
        toggle_btn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
            {    if (init==true)
            {
                chrono.start()
                init =false
            }
                else{
                chrono.setBase(SystemClock.elapsedRealtime()-timee)
                chrono.start()

            }}
            else{
                timee = SystemClock.elapsedRealtime()- chrono.getBase()
                chrono.stop()

            }



        }

        reset.setOnClickListener{

            timee=0
            chrono.setBase(SystemClock.elapsedRealtime())
            chrono.stop()

            toggle_btn.isChecked = false

        }

    }


    }

