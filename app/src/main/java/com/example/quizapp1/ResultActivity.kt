package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.properties.Delegates

//Denna aktivitet visar resultatet av quizet.
class ResultActivity : AppCompatActivity() {

    lateinit var chart: PieChart
    lateinit var tvRight: TextView
    lateinit var tvWrong: TextView
    lateinit var tvHighScoreMessage: TextView
    var correctAnswers by Delegates.notNull<Int>()
    var correctAnswersFloat: Float = 0F
    var wrongAnswersFloat: Float = 0F
    @ExperimentalStdlibApi
    val highScoreMessageTimer = object: CountDownTimer (2000,1000){
        override fun onFinish() {
            displayResultMessage()
        }

        override fun onTick(millisUntilFinished: Long) {
        }
    }
    @ExperimentalStdlibApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvHighScoreMessage = findViewById<TextView>(R.id.tv_highscore_message)
        chart = findViewById(R.id.piechart)
        tvRight = findViewById(R.id.tv_correct_answers)
        tvWrong = findViewById(R.id.tv_wrong_answers)

        /*
        Här hämtar jag information från tidigare aktiviteter som sätts till nya variabler i denna aktivitet.
        Sedan skapar jag en variabel för sharedPreferences.
         */
        val username = intent.getStringExtra("Constants.USER_NAME")
        // tv_name.text = resources.getString(R.string.congratulationz_message_sv,
        //   username?.capitalize(Locale.ROOT))
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)

        correctAnswersFloat = correctAnswers.toFloat()
        wrongAnswersFloat = (totalQuestions.toFloat() - correctAnswersFloat)
        setData()
        /*
        Om spelarens poäng är högre än någon av tidigar highscore så sparas denna poäng och flyttar ner
        det tidigare highscoren ett snäpp ner i listan.
         */
        when {
            correctAnswers > Singletons.highScoreOne.playerPoints -> {
                pref.edit().putString("highScoreThreeName", Singletons.highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", Singletons.highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", Singletons.highScoreOne.playerName).apply()
                pref.edit().putInt("highScoreTwoPoints", Singletons.highScoreOne.playerPoints).apply()
                pref.edit().putString("highScoreOneName", username).apply()
                pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > Singletons.highScoreTwo.playerPoints -> {
                pref.edit().putString("highScoreThreeName", Singletons.highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", Singletons.highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", username).apply()
                pref.edit().putInt("highScoreTwoPoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > Singletons.highScoreThree.playerPoints -> {
                pref.edit().putString("highScoreThreeName", username).apply()
                pref.edit().putInt("highScoreThreePoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            else -> {
                highScoreMessageTimer.start()
            }
        }
        tv_score.text = resources.getString(
            R.string.result_message_sv,
            correctAnswers.toString(),
            totalQuestions.toString()
        )

        btn_finish.setOnClickListener {
            //pref.edit().clear().apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    @ExperimentalStdlibApi
    private fun displayResultMessage () {
        when {
            correctAnswers > Singletons.highScoreOne.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_one_sv,
                    Singletons.userName.capitalize(Locale.ROOT))
            }
            correctAnswers > Singletons.highScoreTwo.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_two_sv, Singletons.userName.capitalize(
                    Locale.ROOT))
            }
            correctAnswers > Singletons.highScoreThree.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_three_sv, Singletons.userName.capitalize(
                    Locale.ROOT))
            }
            else -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_no_entry_sv)
            }
        }
    }
    private fun setData() {

        val percentageRight = (correctAnswersFloat.toDouble() / Singletons.totalNrOfQuestions) * 100
        val percentageWrong = (wrongAnswersFloat.toDouble() / Singletons.totalNrOfQuestions) * 100

        tvRight.setText("$percentageRight % Rätt")
        tvWrong.setText("$percentageWrong % Fel")

        chart.addPieSlice(
            PieModel(
                "Rätt",
                correctAnswersFloat,
                Color.parseColor("#0ff517")
            )
        )
        chart.addPieSlice(
            PieModel(
                "Fel",
                wrongAnswersFloat,
                Color.parseColor("#FF0000")
            )
        )
        chart.startAnimation()
    }
}