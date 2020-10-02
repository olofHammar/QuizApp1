package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*
import java.util.prefs.Preferences

class ResultActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username = intent.getStringExtra("Constants.USER_NAME")
        tv_name.text = "Grattis ${username?.capitalize()}"
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)
        pref.edit().putString("highScoreOneName", username).apply()
        pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()


        tv_score.text = "Din poäng är $correctAnswers av $totalQuestions."

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}