package com.example.quizapp1

import android.app.Activity
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

        val btnStart: Button = view.findViewById(R.id.btn_start)


        btnStart.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this@PlayFragment.context, "Skriv in ditt namn", Toast.LENGTH_SHORT).show()
            }
            else {
                /*
                userName = et_name.text.toString()
                val fragment = SetNrOfQuestionsFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_layout, fragment)
                transaction?.commit()
                 */
                userName = et_name.text.toString()
                val intent =
                    Intent(this@PlayFragment.context, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalNrOfQuestions)
                startActivity(intent)
            }
        }
        return view
    }

}
