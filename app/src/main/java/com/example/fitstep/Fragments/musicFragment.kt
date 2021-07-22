package com.example.fitstep.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitstep.R
import kotlinx.android.synthetic.main.fragment_music.view.*


class musicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_music, container, false)


    view.txt_spotify.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com"))
        startActivity(intent)
        }



        return view
    }

}