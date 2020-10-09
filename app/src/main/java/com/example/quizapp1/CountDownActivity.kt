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

        val myTimer = Timer()
        val tick: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.click_djungle)
        val finished: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.tick_finished_marimba)
        val quizCountDownTimer = object: CountDownTimer (2500,1000){
            override fun onFinish() {
                myTimer.cancel()
                finished.start()
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


        tv_quiz_countdown.visibility = View.GONE
        val waitTimer = object: CountDownTimer (2000,1000) {
            override fun onFinish() {
                tv_quiz_countdown.visibility = View.VISIBLE
                quizCountDownTimer.start()

                myTimer.schedule(object : TimerTask() {
                    override fun run() {
                        tick.start()
                    }
                }, 0,1000)

            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }
        waitTimer.start()




    }
}