package com.example.quizapp1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count_down.*
import java.util.*


class CountDownActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)

        //Jag hade från början sound.clickDjungle i quiz-timerns onTick men detta synkade dåligt så därför gjorde jag en egen timer till den.
        val clickTimer = Timer()
        val sound = Sound(this)
        val quizCountDownTimer = object: CountDownTimer (2500,1000){
            override fun onFinish() {
                clickTimer.cancel()
                sound.tickMarimba()
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

        tv_quiz_countdown.visibility = View.GONE

        /*
       Denna timer skapar ett delay på två sekunder innan nedräkningen börjar.
       När den är klar visas en textView med nedräkning och clickTimern startar.
        */
        val waitTimer = object: CountDownTimer (2000,1000) {
            override fun onFinish() {
                tv_quiz_countdown.visibility = View.VISIBLE
                quizCountDownTimer.start()

                clickTimer.schedule(object : TimerTask() {
                    override fun run() {
                        sound.clickDjungle()
                    }
                }, 0,1000)

            }
            override fun onTick(millisUntilFinished: Long) {}
        }
        waitTimer.start()
    }
}