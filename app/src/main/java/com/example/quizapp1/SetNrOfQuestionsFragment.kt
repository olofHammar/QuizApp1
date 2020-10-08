package com.example.quizapp1

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_set_nr_of_questions.*
import java.util.*

class SetNrOfQuestionsFragment : Fragment(){
    private lateinit var communicator: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_set_nr_of_questions, container, false)

        communicator = activity as Communicator
        var totalNrOfQuestions = 0
        val btnSelectTen: Button = view.findViewById(R.id.btn_select_ten)
        val btnSelectTwenty: Button = view.findViewById(R.id.btn_select_twenty)
        val btnSelectThirty: Button = view.findViewById(R.id.btn_select_thirty)
        val btnNext: Button = view.findViewById(R.id.btn_next)
        val btnEnterNrOfQuestionsMessage = view.findViewById<TextView>(R.id.tv_enter_nr_of_questions_message)
        btnEnterNrOfQuestionsMessage.visibility = View.GONE

        btnSelectTen.setOnClickListener {
            Sounds.click(activity!!.applicationContext)
            defaultButtonView()
            btnSelectTen.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTen.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 10
        }
        btnSelectTwenty.setOnClickListener {
            Sounds.click(activity!!.applicationContext)
            defaultButtonView()
            btnSelectTwenty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTwenty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 20
        }
        btnSelectThirty.setOnClickListener {
            Sounds.click(activity!!.applicationContext)
            defaultButtonView()
            btnSelectThirty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectThirty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 30
        }
        btnNext.setOnClickListener {

            Sounds.click(activity!!.applicationContext)
            if (totalNrOfQuestions == 0) {
                btnEnterNrOfQuestionsMessage.visibility = View.VISIBLE
                val messageTimer = object : CountDownTimer(1500, 1000) {
                    override fun onFinish() {
                        btnEnterNrOfQuestionsMessage.visibility = View.GONE
                    }
                    override fun onTick(millisUntilFinished: Long) {
                    }
                }
                messageTimer.start()
            }
            else {
                communicator.sendData(nr = totalNrOfQuestions)
            }
        }
        return view
    }

    private fun defaultButtonView() {
        val options = ArrayList<TextView>()
        options.add(0, btn_select_ten)
        options.add(1, btn_select_twenty)
        options.add(2, btn_select_thirty)

        for (option in options) {
            option.setBackgroundResource(R.drawable.round_button)
            option.setTextColor(Color.parseColor("#888888"))
        }
    }
}
