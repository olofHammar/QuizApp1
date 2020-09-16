package com.example.quizapp1

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import java.util.*


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
            questionSubmitted = true
            btn_submit.performClick()
        }
        override fun onTick(millisUntilFinished: Long) {
            tv_timer.text = "${millisUntilFinished/1000}"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestion()
        Collections.shuffle(mQuestionsList)

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun reSetTimer(t: CountDownTimer) {
        t.cancel()
        t.start()
    }
    private fun setQuestion() {
        tv_option_one.isClickable = true
        tv_option_two.isClickable = true
        tv_option_three.isClickable = true
        tv_option_four.isClickable = true
        questionSubmitted = false
        mSelectedOptionPosition = 0

        val question = mQuestionsList[mCurrentPosition - 1]

        defaultOptionsView()

        progress_bar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progress_bar.max

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        reSetTimer(countDownTimer)
    }
    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {mSelectedOptionPosition = 1; questionSubmitted = true; btn_submit.performClick()}
            R.id.tv_option_two -> {mSelectedOptionPosition = 2; questionSubmitted = true; btn_submit.performClick()}
            R.id.tv_option_three -> {mSelectedOptionPosition = 3; questionSubmitted = true; btn_submit.performClick()}
            R.id.tv_option_four -> {mSelectedOptionPosition = 4; questionSubmitted = true; btn_submit.performClick()}
            R.id.btn_submit -> {

                tv_option_one.isClickable = false
                tv_option_two.isClickable = false
                tv_option_three.isClickable = false
                tv_option_four.isClickable = false
                countDownTimer.cancel()

                val question = mQuestionsList?.get(mCurrentPosition - 1)
                if (question!!.correctAnswer != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    answerView(question.correctAnswer, R.drawable.correct_option_border_when_wrong_bg)
                }
                else if (question.correctAnswer == mSelectedOptionPosition) {
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    mCorrectAnswers++
                }
                mCurrentPosition++

                if (mCurrentPosition <= mTotalNrOfQuestions && questionSubmitted == true) {
                    Handler().postDelayed(
                        {
                            setQuestion()
                        },1000
                    )
                }
                else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, mUserName)
                    intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mTotalNrOfQuestions)
                    startActivity(intent)
                    finish()
                }
            }
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
    private fun selectedOptionsView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )
    }

 */
}