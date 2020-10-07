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
//Detta är fragment-klassen som visar spelets highscore-lista
class HighScoreFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_high_score, container, false)

        /*
        Här sätter jag två variabler för varje highscore, namn och poäng. Dessa kopplas till rätt textView
        och sedan sätter jag rätt text till respektive textView.
         */
        val tvHsOneName: TextView = view.findViewById(R.id.tv_hs_one_name)
        val tvHsOnePoints: TextView = view.findViewById(R.id.tv_hs_one_points)
        val tvHsTwoName: TextView = view.findViewById(R.id.tv_hs_two_name)
        val tvHsTwoPoints: TextView = view.findViewById(R.id.tv_hs_two_points)
        val tvHsThreeName: TextView = view.findViewById(R.id.tv_hs_three_name)
        val tvHsThreePoints: TextView = view.findViewById(R.id.tv_hs_three_points)


        tvHsOneName.text = Singletons.highScoreOne.playerName.capitalize()
        tvHsOnePoints.text = "${Singletons.highScoreOne.playerPoints} p"

        tvHsTwoName.text = Singletons.highScoreTwo.playerName.capitalize()
        tvHsTwoPoints.text = "${Singletons.highScoreTwo.playerPoints} p"

        tvHsThreeName.text = Singletons.highScoreThree.playerName.capitalize()
        tvHsThreePoints.text = "${Singletons.highScoreThree.playerPoints} p"

        return view
    }
}