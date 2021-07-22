package com.example.fitstep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_mycard.*

class mycard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mycard)

        val intent = intent.extras
        textView5.text=intent!!.getString("name")
        txt_id.text=intent!!.getString("id")

        btn_plan.setOnClickListener {
            val intent = Intent(this, payment::class.java)
            startActivity(intent)

        }
    }



}