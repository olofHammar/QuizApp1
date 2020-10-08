package com.example.quizapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_count_down.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class CountDownActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val highScoreMessageTimer = object: CountDownTimer (2300,1000){
            override fun onFinish() {
                val userName = intent.getStringExtra(Constants.USER_NAME)
                val totalNrOfQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
                val intent = Intent(this@CountDownActivity, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalNrOfQuestions)
                startActivity(intent)
            }
            override fun onTick(millisUntilFinished: Long) {
                tv_quiz_countdown.text = "${(millisUntilFinished+1000)/1000}"
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)

        highScoreMessageTimer.start()

    }
}