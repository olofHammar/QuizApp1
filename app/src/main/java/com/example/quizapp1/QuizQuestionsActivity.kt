package com.example.quizapp1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import java.util.*
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/*
Jag har satt en onClickListener här som fungerar som lyssnare till alla knappar som ska klickas.
Jag har sedan skapat en override funktion som hanterar vad som ska hända vid respektive klick.
 */
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mTotalNrOfQuestions: Int = 10
    private lateinit var mQuestionsList: ArrayList<Question>
    private var mUserName: String? = null
    private var questionSubmitted: Boolean = false
    private val countDownTimer = object : CountDownTimer(10000, 1000) {
        override fun onFinish() {
            submitAnswer()
        }
        override fun onTick(millisUntilFinished: Long) {
            tv_timer.text = "${(millisUntilFinished+1000)/1000}"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        //Här hämtar jag in användarnamnet från mainActivity
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mTotalNrOfQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        //Här hämtar jag in frågor som jag sparar i en lista som sedan blandas med shuffle
        mQuestionsList = Constants.getQuestion()
        Collections.shuffle(mQuestionsList)

        //Här kallar jag på funktionen som skapar en fråga
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            //option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.default_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {tv_option_one.background = ContextCompat.getDrawable(this, drawableView)}
            2 -> {tv_option_two.background = ContextCompat.getDrawable(this, drawableView)}
            3 -> {tv_option_three.background = ContextCompat.getDrawable(this, drawableView)}
            4 -> {tv_option_four.background = ContextCompat.getDrawable(this, drawableView)}
        }
    }

/*
Denna funktion skapar en fråga. Jag gör svarsalternativen klickbara, och sätter dom till deras default illustration.
Jag skapar sedan variabeln question som hämtar en fråga från listan med hjälp av mCurrentPosition
Sedan ger jag varje text/imageView rätt variabel från question och startar countDownTimer.
Min timer består av fyra delar, en textView för nedräkning av siffror, en circularProgressBar,
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

        val question = mQuestionsList[mCurrentPosition - 1]

        defaultOptionsView()

        progress_bar.setMax(mTotalNrOfQuestions)

        progress_bar.progress = mCurrentPosition
        tv_progress.text = resources.getString(R.string.tv_progress_bar_text_sv, mCurrentPosition.toString(), progress_bar.max.toString())

        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        countDownTimer.start()
        tv_circle_answer_text.setText(R.string.question_answer_wrong_sv)
        iv_circle_answer.setImageResource(R.drawable.circle_count_down)
        tv_circle_answer_text.visibility = View.GONE
        tv_timer.visibility = View.VISIBLE
        progress_bar_timer.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {mSelectedOptionPosition = 1; submitAnswer()}
            R.id.tv_option_two -> {mSelectedOptionPosition = 2; submitAnswer()}
            R.id.tv_option_three -> {mSelectedOptionPosition = 3; submitAnswer()}
            R.id.tv_option_four -> {mSelectedOptionPosition = 4; submitAnswer()}
        }
    }

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

        val question = mQuestionsList.get(mCurrentPosition - 1)
        if (question.correctAnswer != mSelectedOptionPosition) {
            iv_circle_answer.setImageResource(R.drawable.circle_wrong_answer)
            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
            answerView(question.correctAnswer, R.drawable.correct_option_border_when_wrong_bg)
        }
        else if (question.correctAnswer == mSelectedOptionPosition) {
            iv_circle_answer.setImageResource(R.drawable.circle_right_answer)
            tv_circle_answer_text.setText(R.string.question_answer_right_sv)
            answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

            mCorrectAnswers++
        }
        mCurrentPosition++

        if (mCurrentPosition <= mTotalNrOfQuestions && questionSubmitted == true) {

            Handler().postDelayed(
                {
                    setQuestion()
                },1500
            )
        }
        else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Constants.USER_NAME", mUserName)
            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
            intent.putExtra(Constants.TOTAL_QUESTIONS, mTotalNrOfQuestions)
            startActivity(intent)
        }
    }
}