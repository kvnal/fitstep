package com.example.fitstep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mycard.*
import kotlinx.android.synthetic.main.activity_payment.*

class payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        btn_pay.setOnClickListener {

            //check the credential input

            val Intent = Intent(this, pay::class.java)
            startActivity(Intent)

        }
    }
}