package com.example.quizapp1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.fragment_play.*
import java.util.ArrayList

class PlayFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_play, container, false)

        var totalNrOfQuestions = 0
        val btnStart: Button = view.findViewById(R.id.btn_start)
        val btnSelectTen: Button = view.findViewById(R.id.btn_select_ten)
        val btnSelectTwenty: Button = view.findViewById(R.id.btn_select_twenty)
        val btnSelectThirty: Button = view.findViewById(R.id.btn_select_thirty)

        btnSelectTen.setOnClickListener {
            defaultButtonView()
            btnSelectTen.setBackgroundResource(R.drawable.round_button_selected)
            totalNrOfQuestions = 10
        }
        btnSelectTwenty.setOnClickListener {
            defaultButtonView()
            btnSelectTwenty.setBackgroundResource(R.drawable.round_button_selected)
            totalNrOfQuestions = 20
        }
        btnSelectThirty.setOnClickListener {
            defaultButtonView()
            btnSelectThirty.setBackgroundResource(R.drawable.round_button_selected)
            totalNrOfQuestions = 30
        }

        btnStart.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this@PlayFragment.context, "Skriv in ditt namn", Toast.LENGTH_SHORT).show()
            }
            else if (totalNrOfQuestions == 0) {
                Toast.makeText(this@PlayFragment.context, "Välj hur många frågor du vill svara på", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent (this@PlayFragment.context, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalNrOfQuestions)
                startActivity(intent)
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
        }
    }
    }
