package com.example.fitstep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitstep.Fragments.*
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment = homeFragment()
        val musicFragment =musicFragment()
        val playFragment = playFragment()
        val shoppingFragment= shoppingFragment()
        val stopwatchFragment =stopwatchFragment()

        current(homeFragment)

        val intent =intent.extras




        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home -> current(homeFragment)
                R.id.music -> current(musicFragment)
                R.id.stopwatch -> current(stopwatchFragment)
                R.id.play -> current(playFragment)
                R.id.shopping -> current(shoppingFragment)

            }
            true
        }


    }
    private fun current(fragment: Fragment)
    {   val bundle = Bundle()
        val intent = intent.extras
        bundle.putString("id", intent?.getString("id"))
        bundle.putString("name", intent?.getString("name"))
        fragment.arguments=bundle

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wrap, fragment)
            commit()
        }

    }


}


