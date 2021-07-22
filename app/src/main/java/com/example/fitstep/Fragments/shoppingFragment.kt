package com.example.fitstep.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.fitstep.R
import kotlinx.android.synthetic.main.fragment_shopping.view.*


class shoppingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_shopping, container, false)

        view.web_shop.webViewClient= WebViewClient()
        view.web_shop.settings.javaScriptEnabled= true
        view.web_shop.loadUrl("https://www.amazon.in/Gym-Equipment/s?k=Gym+Equipment")


        return view
    }


}