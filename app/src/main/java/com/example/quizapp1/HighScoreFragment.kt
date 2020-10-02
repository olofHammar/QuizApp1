package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.fragment_play.*
import java.util.ArrayList

class HighScoreFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_high_score, container, false)
        val tvHsOneName: TextView = view.findViewById(R.id.tv_hs_one_name)
        val tvHsOnePoints: TextView = view.findViewById((R.id.tv_hs_one_points))

        tvHsOneName.text = highScoreOne.playerName.capitalize()
        tvHsOnePoints.text = "${highScoreOne.playerPoints} p"


        return view
    }
}