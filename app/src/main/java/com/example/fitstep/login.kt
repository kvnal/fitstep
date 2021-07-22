package com.example.fitstep

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import java.text.DateFormat
import java.text.SimpleDateFormat


class login : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //imageView.setOnClickListener {  imageView.drawable.setTint(R.color.secureblue)}



    }

    fun singup(view: View) {

        val intent = Intent(this@login,signin::class.java)
        startActivity(intent)

    }



    private fun check_status() {
        val status = FirebaseDatabase.getInstance().getReference("BETA").child(edit_email.text.toString())
        status.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    if (snapshot.child("status").getValue(String::class.java) == "IN") {
                        check_model()
                    } else if (snapshot.child("status").getValue(String::class.java) == "SUSPENDED") {
                            suspended_()

                    }
                } else
                    Toast.makeText(applicationContext, "Not eligible for BETA testing", Toast.LENGTH_SHORT).show()


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun check_model() {
        val beta = FirebaseDatabase.getInstance().getReference("BETA").child(edit_email.text.toString())
        val ref = FirebaseDatabase.getInstance().getReference("Users").child(edit_email.text.toString())
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {

                    if (dataSnapshot.child("password")
                            .getValue(String::class.java) == edit_password.text.toString()
                    ) {

                        if (dataSnapshot.child("MOBILE").child("model")
                                .getValue(String::class.java) == Build.MODEL.toString()
                        ) {



                            val intent = Intent(this@login, home::class.java)

                            val name = dataSnapshot.child("first name").getValue(String::class.java)+" "+dataSnapshot.child("last name").getValue(String::class.java)
                            val m_id = dataSnapshot.child("member ID").getValue(String::class.java)

                            intent.putExtra("name",name)
                            intent.putExtra("id",m_id)


                            startActivity(intent)
                            finish()

                        } else {
                            beta.child("status").setValue("SUSPENDED")
                            beta.child("model").setValue(Build.MODEL.toString())
                            suspended_()
                        }


                    } else
                        edit_password.error="wrong password"


                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })


    }

    private fun suspended_() {
        val intent = Intent(this@login,suspended::class.java)
        startActivity(intent)

    }

    fun login(view: View) {check_status()}




}


