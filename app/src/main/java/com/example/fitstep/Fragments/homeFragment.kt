package com.example.fitstep.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitstep.R
import com.example.fitstep.calculator
import com.example.fitstep.mycard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class homeFragment : Fragment() {

    val db = FirebaseDatabase.getInstance()
    val refer = db.getReference("Quotes")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view= inflater.inflate(R.layout.fragment_home, container, false)
        val bundle = arguments
        if (bundle != null) {
            view.txt_memberid.text = bundle.getString("id")
        }



        //view.txt_memberid.text=""
        view.btn_bmi.setOnClickListener {
            val intent=Intent(activity, calculator::class.java)
            startActivity(intent)
        }

        view.imageView4.setOnClickListener {
            val Intent =Intent(activity, mycard::class.java)
            Intent.putExtra("id",bundle?.getString("id"))
            Intent.putExtra("name",bundle?.getString("name"))
            startActivity(Intent)

        }

        view.btn_nextQuote.setOnClickListener {
            nextQuote()

        }

        view.dial.setOnClickListener {
            val intent = Intent(activity, com.example.fitstep.dial::class.java)
            startActivity(intent)
        }

        return view

    }

    private fun nextQuote() {
        val range :IntRange = 1..30
        val quote = refer.child(range.random().toString()).child("quote")

        txt_quote.visibility=View.INVISIBLE
        ani_load_quotes.visibility=View.VISIBLE

        Handler().postDelayed({
            ani_load_quotes.visibility = View.INVISIBLE

            quote.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)!!
                    txt_quote.text = value
                    txt_quote.visibility = View.VISIBLE

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value

                }
            })

        }, (1000..1500).random().toLong())



    }


}


