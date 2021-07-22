package com.example.fitstep.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.activity.addCallback
import com.airbnb.lottie.LottieAnimationView
import com.example.fitstep.R
import com.example.fitstep.home
import com.example.fitstep.mycard
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import kotlinx.android.synthetic.main.fragment_stopwatch.view.*
import android.view.animation.Animation.AnimationListener as AnimationListener1


class stopwatchFragment : Fragment() {

//818181 grey main selector

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val homeFragment = homeFragment()
        val toast:Toast = Toast.makeText(activity, "Tap when you are ready!!",Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL,0,600)
        toast.show()

        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_stopwatch, container, false)


        view.btn.setOnClickListener {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_wrap,homeFragment)
            commit()
        }}

        view.breathe_bubble.setOnClickListener {
            if(breathe_bubble.isAnimating){
                breathe_bubble.pauseAnimation()
                toast.setText("Break Time!")
                toast.show()
            }
            else if(!breathe_bubble.isAnimating){
                breathe_bubble.playAnimation()
                toast.setText("Meditation Time!")
                toast.show()}
        }

       return view
    }

    override fun onResume() {
        super.onResume()
        breathe_bubble.pauseAnimation()


    }


}

