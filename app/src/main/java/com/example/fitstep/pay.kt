package com.example.fitstep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.coroutines.delay

class pay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        ani_done_tick.visibility = View.INVISIBLE
        ani_congra.visibility=View.INVISIBLE

        Handler().postDelayed({
            txt_please_wait.visibility= View.INVISIBLE
            ani_loading_dots.visibility=View.INVISIBLE
            ani_done_tick.playAnimation()
            ani_done_tick.visibility = View.VISIBLE

        },2000)


        Handler().postDelayed({
        ani_congra.visibility=View.VISIBLE
        ani_congra.playAnimation()

        },4000)

        ani_congra.visibility=View.INVISIBLE

        Handler().postDelayed({

            finish()
        },6000)

    }
}