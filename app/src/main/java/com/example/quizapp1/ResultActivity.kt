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

    private lateinit var chart: PieChart
    private lateinit var tvRight: TextView
    private lateinit var tvWrong: TextView
    private lateinit var tvHighScoreMessage: TextView
    private lateinit var username: String
    private var highScoreNrOne: PlayerHighScore = PlayerHighScore()
    private var highScoreNrTwo: PlayerHighScore = PlayerHighScore()
    private var highScoreNrThree: PlayerHighScore = PlayerHighScore()
    private var totalQuestions = 0
    private var correctAnswers = 0
    private var correctAnswersFloat: Float = 0F
    private var wrongAnswersFloat: Float = 0F
    @ExperimentalStdlibApi
    private val highScoreMessageTimer = object: CountDownTimer (2000,1000){
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
        username = intent.getStringExtra(Constants.USER_NAME).toString()
        totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)

        highScoreNrOne.playerName = pref.getString("highScoreOneName", "---").toString()
        highScoreNrOne.playerPoints = pref.getInt("highScoreOnePoints", 0)

        highScoreNrTwo.playerName = pref.getString("highScoreTwoName","---").toString()
        highScoreNrTwo.playerPoints = pref.getInt("highScoreTwoPoints",0)

        highScoreNrThree.playerName = pref.getString("highScoreThreeName","---").toString()
        highScoreNrThree.playerPoints = pref.getInt("highScoreThreePoints",0)

        correctAnswersFloat = correctAnswers.toFloat()
        wrongAnswersFloat = (totalQuestions.toFloat() - correctAnswersFloat)
        setData()
        /*
        Om spelarens poäng är högre än någon av tidigar highscore så sparas denna poäng och flyttar ner
        det tidigare highscoren ett snäpp ner i listan.
         */
        when {
            correctAnswers > highScoreNrOne.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreNrTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreNrTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", highScoreNrOne.playerName).apply()
                pref.edit().putInt("highScoreTwoPoints", highScoreNrOne.playerPoints).apply()
                pref.edit().putString("highScoreOneName", username).apply()
                pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > highScoreNrTwo.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreNrTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreNrTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", username).apply()
                pref.edit().putInt("highScoreTwoPoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > highScoreNrThree.playerPoints -> {
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
            correctAnswers > highScoreNrOne.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_one_sv,
                    username.capitalize(Locale.ROOT))
            }
            correctAnswers > highScoreNrTwo.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_two_sv, username.capitalize(
                    Locale.ROOT))
            }
            correctAnswers > highScoreNrThree.playerPoints -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_three_sv, username.capitalize(
                    Locale.ROOT))
            }
            else -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_no_entry_sv)
            }
        }
    }
    private fun setData() {

        val percentageRight = (correctAnswersFloat.toDouble() / totalQuestions) * 100
        val percentageWrong = (wrongAnswersFloat.toDouble() / totalQuestions) * 100

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