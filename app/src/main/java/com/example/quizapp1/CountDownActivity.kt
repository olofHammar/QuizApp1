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

    private var soundPool = SoundPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)

        soundPool.load(this, R.raw.tick_finished_marimba)
        soundPool.load(this, R.raw.click_djungle)
        val quizCountDownTimer = object: CountDownTimer (2500,1000){
            override fun onFinish() {
                soundPool.play(R.raw.tick_finished_marimba)
                val userName = intent.getStringExtra(Constants.USER_NAME)
                val totalNrOfQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
                val intent = Intent(this@CountDownActivity, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalNrOfQuestions)
                startActivity(intent)
            }
            override fun onTick(millisUntilFinished: Long) {
                soundPool.play(R.raw.click_djungle)
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
            }
            override fun onTick(millisUntilFinished: Long) {}
        }
        waitTimer.start()
    }

    override fun onDestroy() {
        soundPool.unload(R.raw.tick_finished_marimba)
        soundPool.unload(R.raw.click_djungle)
        super.onDestroy()
    }
}