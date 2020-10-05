package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*
import java.util.prefs.Preferences
//Denna aktivitet visar resultatet av quizet.
class ResultActivity : AppCompatActivity() {

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        /*
        Här hämtar jag information från tidigare aktiviteter som sätts till nya variabler i denna aktivitet.
        Sedan skapar jag en variabel för sharedPreferences.
         */
        val username = intent.getStringExtra("Constants.USER_NAME")
        tv_name.text = resources.getString(R.string.congratulationz_message_sv,
            username?.capitalize(Locale.ROOT))
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)
        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)

        /*
        Om spelarens poäng är högre än någon av tidigar highscore så sparas denna poäng och flyttar ner
        det tidigare highscoren ett snäpp ner i listan.
         */
        when  {
            correctAnswers>highScoreOne.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", highScoreOne.playerName).apply()
                pref.edit().putInt("highScoreTwoPoints", highScoreOne.playerPoints).apply()
                pref.edit().putString("highScoreOneName", username).apply()
                pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()
            }
            correctAnswers>highScoreTwo.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", username).apply()
                pref.edit().putInt("highScoreTwoPoints", correctAnswers).apply()
            }
            correctAnswers> highScoreThree.playerPoints -> {
                pref.edit().putString("highScoreThreeName", username).apply()
                pref.edit().putInt("highScoreThreePoints", correctAnswers).apply()
            }
        }
        tv_score.text = resources.getString(R.string.result_message_sv, correctAnswers.toString(), totalQuestions.toString())

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}