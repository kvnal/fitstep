package com.example.fitstep

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent =  Intent(this@MainActivity,login::class.java)
            startActivity(intent)
            finish()



        },1000) // change to 2000ms




    }
}