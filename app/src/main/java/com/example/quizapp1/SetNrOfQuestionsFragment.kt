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
import kotlinx.android.synthetic.main.fragment_set_nr_of_questions.*
import java.util.ArrayList

class SetNrOfQuestionsFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_set_nr_of_questions, container, false)
        totalNrOfQuestions = 0

        val btnSelectTen: Button = view.findViewById(R.id.btn_select_ten)
        val btnSelectTwenty: Button = view.findViewById(R.id.btn_select_twenty)
        val btnSelectThirty: Button = view.findViewById(R.id.btn_select_thirty)
        val btnNext: Button = view.findViewById(R.id.btn_next)

        btnSelectTen.setOnClickListener {
            defaultButtonView()
            btnSelectTen.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTen.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 10
        }
        btnSelectTwenty.setOnClickListener {
            defaultButtonView()
            btnSelectTwenty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTwenty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 20
        }
        btnSelectThirty.setOnClickListener {
            defaultButtonView()
            btnSelectThirty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectThirty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 30
        }

        btnNext.setOnClickListener {

            if (totalNrOfQuestions == 0) {
                Toast.makeText(
                    this@SetNrOfQuestionsFragment.context,
                    "Välj hur många frågor du vill svara på",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
/*
                val intent =
                    Intent(this@SetNrOfQuestionsFragment.context, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalNrOfQuestions)
                startActivity(intent)
 */
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
