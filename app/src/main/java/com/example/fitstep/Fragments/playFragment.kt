package com.example.fitstep.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fitstep.R
import com.example.fitstep.play_blogs
import kotlinx.android.synthetic.main.fragment_play.view.*


class playFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_play, container, false)

        val activity = Intent(context,play_blogs::class.java)



        view.txt_blog.setOnClickListener {
            activity.putExtra("title","Blog")
            activity.putExtra("link","https://www.bornfitness.com/blog/")
        startActivity(activity)
        }

        view.txt_fitness.setOnClickListener {
            activity.putExtra("title","Fitness")
            activity.putExtra("link","https://www.self.com/package/workout-finder")
            startActivity(activity)
        }

        view.txt_diet.setOnClickListener {
            activity.putExtra("title","Diet")
            activity.putExtra("link","https://www.health.com/diets")
            startActivity(activity)
        }




        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }


}