package com.example.fitstep

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.fitstep.Fragments.playFragment
import kotlinx.android.synthetic.main.activity_play_blogs.*

class play_blogs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_blogs)

        val title :TextView = findViewById(R.id.title)

        val  activity = intent.extras

        title.text = activity!!.get("title").toString()


        //title.text

        webview.webViewClient= WebViewClient()
        //webview.settings.loadWithOverviewMode = true
        webview.loadUrl(activity.getString("link").toString())
        webview.settings.javaScriptEnabled=true


    }
}