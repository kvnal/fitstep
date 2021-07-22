package com.example.fitstep

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_dial.*
import java.nio.file.attribute.AclEntry

class dial : AppCompatActivity() {

    val call = Intent(Intent.ACTION_DIAL)
    var phone :String = "Null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dial)


        developer.text = "</ksn>"



    }

    fun instructor(view: View) {
        phone = "007"
        dial(phone)
    }

    fun physician(view: View) {
        phone = "811"
        dial(phone)
    }

    fun home(view: View) {
        phone = "1234"
        dial(phone)
    }


    fun dial(phone:String){
        call.data = Uri.parse("tel:$phone")
        startActivity(call) }


}