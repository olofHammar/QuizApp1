package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import kotlin.properties.Delegates

lateinit var chart: PieChart
lateinit var tvRight: TextView
lateinit var tvWrong: TextView
var correctAnswersFloat: Float = 0F
var wrongAnswersFloat: Float = 0F

//Denna aktivitet visar resultatet av quizet.
class ResultActivity : AppCompatActivity() {

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

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
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)

        correctAnswersFloat = correctAnswers.toFloat()
        wrongAnswersFloat = (totalNrOfQuestions.toFloat() - correctAnswersFloat)
        setData()


        /*
        Om spelarens poäng är högre än någon av tidigar highscore så sparas denna poäng och flyttar ner
        det tidigare highscoren ett snäpp ner i listan.
         */
        when {
            correctAnswers > highScoreOne.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", highScoreOne.playerName).apply()
                pref.edit().putInt("highScoreTwoPoints", highScoreOne.playerPoints).apply()
                pref.edit().putString("highScoreOneName", username).apply()
                pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()
            }
            correctAnswers > highScoreTwo.playerPoints -> {
                pref.edit().putString("highScoreThreeName", highScoreTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", username).apply()
                pref.edit().putInt("highScoreTwoPoints", correctAnswers).apply()
            }
            correctAnswers > highScoreThree.playerPoints -> {
                pref.edit().putString("highScoreThreeName", username).apply()
                pref.edit().putInt("highScoreThreePoints", correctAnswers).apply()
            }
        }
        tv_score.text = resources.getString(
            R.string.result_message_sv,
            correctAnswers.toString(),
            totalQuestions.toString()
        )

        btn_finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setData() {

        var percentageRight = (correctAnswersFloat.toDouble() / totalNrOfQuestions) * 100
        var percentageWrong = (wrongAnswersFloat.toDouble() / totalNrOfQuestions) * 100
        // Set the percentage of language used
        tvRight.setText("$percentageRight % Rätt")
        tvWrong.setText("$percentageWrong % Fel")

        // Set the data and color to the pie chart
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