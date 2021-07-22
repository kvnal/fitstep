package com.example.fitstep

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_signin.*
import java.sql.Time
import java.text.DateFormat
import java.util.*


class signin : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }


    fun signup_btn(view: View) {
        if(checkEdit()) {
            checkBeta()
        }

    }

    private fun checkBeta() {
        val ref = FirebaseDatabase.getInstance().getReference("BETA").child(edit_email.text.toString())
       // val query = ref.orderByChild("email").equalTo(edit_email.text.toString())


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    val status = dataSnapshot.child("status").getValue(String::class.java)

                    if (status == "OK") {
                        ref.child("status").setValue("IN")
                        storeEdit()
                        Toast.makeText(applicationContext, "User registered", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@signin, login::class.java)
                        startActivity(intent)
                        finish()
                    } else
                        Toast.makeText(
                            applicationContext,
                            "This email is already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "You are not eligible for BETA testing",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })

    }

    private fun checkEdit():Boolean {
        val edit_list = listOf<EditText>(
            edit_firstname,
            edit_lastname,
            edit_email,
            edit_password,
            edit_c_password
        )

        for( i in edit_list){
            if (!check_(i))
                return false
        }

        if(edit_password.text.toString() != edit_c_password.text.toString())
        {edit_c_password.error="Password not matched"
            return false}


    return true
    }

    private fun check_(i: EditText):Boolean {

        if(i.text.isEmpty()) {
            i.error = "This Field can't be blank"
            return false
        }
    return true
    }

    private fun storeEdit() {
        val id = generate_memberId()
        val text = findViewById<TextView>(R.id.id)
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(edit_email.text.toString())
        val data_name = listOf<String>("email","first name","last name","password")
        val e_list= listOf<EditText>(edit_email,edit_firstname,edit_lastname,edit_password)

        var j =0
        for(i in e_list){
            ref.child(data_name[j]).setValue(i.text.toString())
            j++
        }

        ref.child("member ID").setValue(id)

        //mobile data
        //ref.child("Time").setValue(DateFormat.getDateInstance().format("dd-MM hh:mm:ss").toString())
        ref.child("MOBILE").child("brand").setValue(Build.BRAND.toString())
        ref.child("MOBILE").child("model").setValue(Build.MODEL.toString())
        ref.child("MOBILE").child("board").setValue(Build.BOARD.toString())
        ref.child("MOBILE").child("display").setValue(Build.DISPLAY.toString())
        ref.child("MOBILE").child("version name").setValue(BuildConfig.VERSION_NAME)

    }

    private fun generate_memberId(): String {
        val range =1000..9999
        var id_=""

        for(i in 0..3){
            id_+=range.random().toString()+" "
        }
        return id_.trim()
    }


}