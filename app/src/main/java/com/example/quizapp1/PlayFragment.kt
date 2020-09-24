package com.example.quizapp1

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_play.*
import kotlinx.android.synthetic.main.fragment_play.view.*

class PlayFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_play,
        container, false)
        val btnStart: Button = view.findViewById(R.id.btn_start)
        btnStart.setOnClickListener {
            Log.d("!!!","click")
        }
        //then return in on create view.
        return view;

    }

    }
