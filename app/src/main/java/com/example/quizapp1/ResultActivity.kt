package com.example.quizapp1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.plattysoft.leonids.ParticleSystem
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.coroutines.CoroutineContext

//Denna aktivitet visar resultatet av quizet.
class ResultActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var db  : HighscoreDatabase

    private lateinit var pieChart: PieChart
    private lateinit var tvRight: TextView
    private lateinit var tvWrong: TextView
    private lateinit var tvHighScoreMessage: TextView
    private lateinit var username: String
    private var soundPool = SoundPool()
    private var highScoreNrOne: PlayerHighScore = PlayerHighScore()
    private var highScoreNrTwo: PlayerHighScore = PlayerHighScore()
    private var highScoreNrThree: PlayerHighScore = PlayerHighScore()
    private var totalQuestions = 0
    private var correctAnswers = 0
    private var wrongAnswers = 0
    //Dessa variabler används för att kunna rita upp pieChart
    private var correctAnswersFloat: Float = 0F
    private var wrongAnswersFloat: Float = 0F
    @ExperimentalStdlibApi
    //Här har jag satt en timer som väntar tre sekunder med att meddela spelaren om denna tog sig in på highscore eller inte.
    private val highScoreMessageTimer = object: CountDownTimer (3000,1000){
        override fun onFinish() {
            displayResultMessage()
        }
        override fun onTick(millisUntilFinished: Long) {}
    }
    @ExperimentalStdlibApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        job = Job()
        db = HighscoreDatabase.getInstance(this)

        soundPool.load(this, R.raw.click_mouth_pop)
        soundPool.load(this, R.raw.fanfare_highscore)

        tvHighScoreMessage = findViewById(R.id.tv_highscore_message)
        pieChart = findViewById(R.id.piechart)
        tvRight = findViewById(R.id.tv_correct_answers)
        tvWrong = findViewById(R.id.tv_wrong_answers)

        //Här hämtar jag allt som skickats från tidigare aktivitet.
        username = intent.getStringExtra(Constants.USER_NAME).toString()
        totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        wrongAnswers = (totalQuestions - correctAnswers)


        //Här hämtar jag nuvarande highscore-lista så att jag kan jämföra det nya resultatet med den.
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
        //Detta ljud var också en soundPool från början men hann inte ladda i tid. Därför ändrade jag denna till MediaPlayer
        loadPieChart(this)
        /*
        Om spelarens poäng är högre än någon av tidigare highscore så sparas denna poäng och flyttar ner
        det tidigare highscoren ett snäpp ner i listan.
         */
        when {
            correctAnswers > highScoreNrOne.playerPoints -> {
                updateHighscore(highScoreNrTwo.playerName, highScoreNrTwo.playerPoints, 3)
                updateHighscore(highScoreNrOne.playerName, highScoreNrOne.playerPoints, 2)
                updateHighscore(username, correctAnswers, 1)
                pref.edit().putString("highScoreThreeName", highScoreNrTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreNrTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", highScoreNrOne.playerName).apply()
                pref.edit().putInt("highScoreTwoPoints", highScoreNrOne.playerPoints).apply()
                pref.edit().putString("highScoreOneName", username).apply()
                pref.edit().putInt("highScoreOnePoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > highScoreNrTwo.playerPoints -> {
                updateHighscore(highScoreNrTwo.playerName, highScoreNrTwo.playerPoints, 3)
                updateHighscore(username, correctAnswers, 2)
                pref.edit().putString("highScoreThreeName", highScoreNrTwo.playerName).apply()
                pref.edit().putInt("highScoreThreePoints", highScoreNrTwo.playerPoints).apply()
                pref.edit().putString("highScoreTwoName", username).apply()
                pref.edit().putInt("highScoreTwoPoints", correctAnswers).apply()
                highScoreMessageTimer.start()
            }
            correctAnswers > highScoreNrThree.playerPoints -> {
                updateHighscore(username, correctAnswers, 3)
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
            //Kommentaren under kan avkommenteras om man vill nollställa highscore-listan
            //pref.edit().clear().apply()
            soundPool.play(R.raw.click_mouth_pop)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    fun addHighscore(highscore: Highscore) {

        launch(Dispatchers.IO) {
            db.highscoreDao.insert(highscore)
        }
    }
    fun updateHighscore(playername: String, playerscore: Int, position: Int) {

        launch(Dispatchers.IO) {
            db.highscoreDao.update(playername, playerscore, position)
        }
    }
    /*
    Denna funktion skiver ut ett meddelande som berättar om användaren tog sig in på highscore-listan eller inte.
    Ifall användaren gjorde det så spelas en fanfar.
    */
    @ExperimentalStdlibApi
    private fun displayResultMessage () {
        when {
            correctAnswers > highScoreNrOne.playerPoints -> {
                soundPool.play(R.raw.fanfare_highscore)
                confetti()
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_one_sv,
                    username.capitalize(Locale.ROOT))
            }
            correctAnswers > highScoreNrTwo.playerPoints -> {
                soundPool.play(R.raw.fanfare_highscore)
                confetti()
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_two_sv, username.capitalize(
                    Locale.ROOT))
            }
            correctAnswers > highScoreNrThree.playerPoints -> {
                soundPool.play(R.raw.fanfare_highscore)
                confetti()
                tvHighScoreMessage.text = resources.getString(R.string.highscore_message_nr_three_sv, username.capitalize(
                    Locale.ROOT))
            }
            else -> {
                tvHighScoreMessage.text = resources.getString(R.string.highscore_no_entry_sv)
            }
        }
    }
    //Denna funktion skickar in resultatet i pieChart och visar det.
    private fun setData() {

        val percentageRight = (correctAnswers.toDouble() / totalQuestions) * 100
        val percentageWrong = (wrongAnswers.toDouble() / totalQuestions) * 100

        //Jag lyckas av någon anledning inte ändra dessa två till en value/string utan att appen crashar.
        tvRight.setText("$percentageRight % Rätt")
        tvWrong.setText("$percentageWrong % Fel")

        pieChart.addPieSlice(
            PieModel(
                "Rätt",
                correctAnswersFloat,
                Color.parseColor("#00E135")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Fel",
                wrongAnswersFloat,
                Color.parseColor("#FB1900")
            )
        )
        pieChart.startAnimation()
    }
    /*
    Denna funktion fyller skärmen med konfetti om användaren tog sig in på highscore-listan.
    Konfettin är importerad i implementations och skapas sedan här.
     */

    private fun confetti() {
        ParticleSystem(this, 80, R.drawable.confeti3, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 0)
            .setRotationSpeed(144f)
            .setAcceleration(0.00005f, 90)
            .emit(findViewById(R.id.emiter_top_right), 8)

        ParticleSystem(this, 80, R.drawable.confeti2, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
            .setRotationSpeed(144f)
            .setAcceleration(0.00005f, 90)
            .emit(findViewById(R.id.emiter_top_left), 8)
    }
    fun loadPieChart (context: Context) {
        var loadPieChart = MediaPlayer.create(context, R.raw.load_piechart)
        loadPieChart.setOnCompletionListener { mp ->
            mp.reset()
            mp.release()
            loadPieChart = null
        }
        loadPieChart.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

}