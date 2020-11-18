package com.example.quizapp1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

/*
Jag har satt en onClickListener här som fungerar som lyssnare till alla knappar som ska klickas.
Jag har sedan skapat en override funktion som hanterar vad som ska hända vid respektive klick.
 */
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener{

    private val mQuestionsList = MainActivity.questionList

    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mTotalNrOfQuestions: Int = 0
    private var currentQuestion : QuestionTemplate? = null
    private var mUserName: String? = null
    private var questionSubmitted: Boolean = false
    private var soundPool = SoundPool()
    /*Denna timer räknar ner från tio när en ny fråga startar. När tiden är ute så kallas funktionen submitAnswer.
    mSelectedOption... är då satt till 0 vilket är fel svar.
     */
    private val countDownTimer = object : CountDownTimer(10000, 1000) {
        override fun onFinish() {
            submitAnswer()
        }
        override fun onTick(millisUntilFinished: Long) {
            tv_timer.text = "${(millisUntilFinished+1000)/1000}"
        }
    }
    /*
    Dessa två timers fungerar som delay när användaren valt ett svar. Utan detta delay startades nästa fråga
    innan användaren hann se svaret. Jag använde först Handler men från vad jag har läst så är det ganska dålig praxis.
    Sen kollade jag på coroutines men hade svårt att få det att fungera som jag ville. Så jag valde till slut att använda
    dessa timers.
     */
    private val setQuestionTimer = object : CountDownTimer (1500,1000){
        override fun onFinish() {
            setQuestion()
        }
        override fun onTick(millisUntilFinished: Long) {}
    }
    private val loadResultTimer = object : CountDownTimer (1500,1000){
        override fun onFinish() {
            loadResult()
        }
        override fun onTick(millisUntilFinished: Long) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        soundPool.load(this, R.raw.alert_right_answer)
        soundPool.load(this, R.raw.alert_wrong_answer)

        //Här hämtar jag in variablerna jag skickade från PlayFragment.
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mTotalNrOfQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)

        //Här hämtar jag frågorna till mQuestionsList från funktion getQuestions i Constants sedan blandar jag frågorna med shuffle.
        //Collections.shuffle(mQuestionsList)

        //Här kallar jag på funktionen som skapar en fråga
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    //Denna funktion återställer alla svarsalternativ till default-design
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.setBackgroundResource(R.drawable.default_option_border_bg)
        }
    }

    //Denna funktion byter design på det valda svarsalternativet.
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {tv_option_one.background = ContextCompat.getDrawable(this, drawableView)}
            2 -> {tv_option_two.background = ContextCompat.getDrawable(this, drawableView)}
            3 -> {tv_option_three.background = ContextCompat.getDrawable(this, drawableView)}
            4 -> {tv_option_four.background = ContextCompat.getDrawable(this, drawableView)}
        }
    }

/*
Denna funktion skapar en fråga. Jag gör svarsalternativen klickbara, och sätter dom till deras default-design.
Jag skapar sedan variabeln question som hämtar en fråga från listan med hjälp av mCurrentPosition
Sedan ger jag varje text/imageView rätt variabel från question och startar countDownTimer.
Min timer består av fyra delar, en textView för nedräkning av siffror, en circularProgressBar som är satt till tio sekunder,
en imageView som visar rött eller grönt vid fel/rätt svar samt en textView som skriver ut om det var rätt eller fel svar.
Jag sätter svaret till fel som default och väljer vilka delar av timern som ska synas från början.
 */
    private fun setQuestion() {

        tv_option_one.isClickable = true
        tv_option_two.isClickable = true
        tv_option_three.isClickable = true
        tv_option_four.isClickable = true
        questionSubmitted = false
        mSelectedOptionPosition = 0

        currentQuestion = mQuestionsList!!.getNewQuestion()

        defaultOptionsView()

        progress_bar.setMax(mTotalNrOfQuestions)

        progress_bar.progress = mCurrentPosition
        tv_progress.text = resources.getString(R.string.tv_progress_bar_text_sv, mCurrentPosition.toString(), progress_bar.max.toString())

        tv_question.text = currentQuestion!!.question
        iv_image.setImageResource(currentQuestion!!.image)
        tv_option_one.text = currentQuestion!!.optionOne
        tv_option_two.text = currentQuestion!!.optionTwo
        tv_option_three.text = currentQuestion!!.optionThree
        tv_option_four.text = currentQuestion!!.optionFour
        countDownTimer.start()
        tv_circle_answer_text.setText(R.string.question_answer_wrong_sv)
        iv_circle_answer.setImageResource(R.drawable.circle_count_down)
        tv_circle_answer_text.visibility = View.GONE
        tv_timer.visibility = View.VISIBLE
        progress_bar_timer.visibility = View.VISIBLE
    }

    //När något av svarsalternativen klickas på så sätts mSelected... till det matchande numret och skickas sedan till submitAnswer
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {mSelectedOptionPosition = 1; submitAnswer()}
            R.id.tv_option_two -> {mSelectedOptionPosition = 2; submitAnswer()}
            R.id.tv_option_three -> {mSelectedOptionPosition = 3; submitAnswer()}
            R.id.tv_option_four -> {mSelectedOptionPosition = 4; submitAnswer()}
        }
    }

    /*
    Denna funktion kollar om svaret användaren uppgivit är rätt eller fel.
    Först så gör jag så att svarsalternativen ej är klickbara, sedan stoppar jag timern och byter design på den genom visibility.
    Sedan jämför jag question.correctAnswer med den valda mSelectedPos... om dessa är samma är svaret rätt annars är svaret fel.
    Efter det laddas en ny fråga så länge som mCurrentPosition är mindre eller lika med mTotalNrOf...
    Annars går vi vidare till resultActivity.
     */
    private fun submitAnswer() {

        questionSubmitted = true
        tv_option_one.isClickable = false
        tv_option_two.isClickable = false
        tv_option_three.isClickable = false
        tv_option_four.isClickable = false
        countDownTimer.cancel()
        tv_timer.visibility = View.GONE
        progress_bar_timer.visibility = View.GONE
        iv_circle_answer.visibility = View.VISIBLE
        tv_circle_answer_text.visibility = View.VISIBLE

        val question = currentQuestion
        if (question!!.correctAnswer != mSelectedOptionPosition) {
            soundPool.play(R.raw.alert_wrong_answer)
            iv_circle_answer.setImageResource(R.drawable.circle_wrong_answer)
            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
            answerView(question.correctAnswer, R.drawable.correct_option_border_when_wrong_bg)
        }
        else if (question.correctAnswer == mSelectedOptionPosition) {
            soundPool.play(R.raw.alert_right_answer)
            iv_circle_answer.setImageResource(R.drawable.circle_right_answer)
            tv_circle_answer_text.setText(R.string.question_answer_right_sv)
            answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

            mCorrectAnswers++
        }
        mCurrentPosition++

        if (mCurrentPosition <= mTotalNrOfQuestions && questionSubmitted == true) {
            setQuestionTimer.start()
        }
        else {
            loadResultTimer.start()
        }
    }
    private fun loadResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(Constants.USER_NAME, mUserName)
        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
        intent.putExtra(Constants.TOTAL_QUESTIONS, mTotalNrOfQuestions)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

}