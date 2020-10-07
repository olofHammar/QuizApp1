package com.example.quizapp1

import android.graphics.Color
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_set_nr_of_questions, container, false)
        Singletons.totalNrOfQuestions = 0

        val btnSelectTen: Button = view.findViewById(R.id.btn_select_ten)
        val btnSelectTwenty: Button = view.findViewById(R.id.btn_select_twenty)
        val btnSelectThirty: Button = view.findViewById(R.id.btn_select_thirty)
        val btnNext: Button = view.findViewById(R.id.btn_next)
        val btnEnterNrOfQuestionsMessage = view.findViewById<TextView>(R.id.tv_enter_nr_of_questions_message)
        btnEnterNrOfQuestionsMessage.visibility = View.GONE

        btnSelectTen.setOnClickListener {
            defaultButtonView()
            btnSelectTen.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTen.setTextColor(Color.parseColor("#696969"))
            Singletons.totalNrOfQuestions = 10
        }
        btnSelectTwenty.setOnClickListener {
            defaultButtonView()
            btnSelectTwenty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTwenty.setTextColor(Color.parseColor("#696969"))
            Singletons.totalNrOfQuestions = 20
        }
        btnSelectThirty.setOnClickListener {
            defaultButtonView()
            btnSelectThirty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectThirty.setTextColor(Color.parseColor("#696969"))
            Singletons.totalNrOfQuestions = 30
        }

        btnNext.setOnClickListener {

            if (Singletons.totalNrOfQuestions == 0) {
                btnEnterNrOfQuestionsMessage.visibility = View.VISIBLE
                val messageTimer = object : CountDownTimer(1500, 1000) {
                    override fun onFinish() {
                        btnEnterNrOfQuestionsMessage.visibility = View.GONE
                    }
                    override fun onTick(millisUntilFinished: Long) {
                    }
                }
                messageTimer.start()
            } else {

                val fragment = PlayFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_layout, fragment)
                transaction?.commit()
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
