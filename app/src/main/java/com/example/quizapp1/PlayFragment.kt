package com.example.quizapp1

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.fragment_play.*
import java.util.ArrayList

class PlayFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_play, container, false)
        /*
        Här skapar jag en variabel som kopplas till startknappen. Sedan sätter jag en klicklyssnare till den knappen.
        Lyssnaren kräver att användaren skriver in sitt namn, görs ej detta så visas en Toast som ber användaren skriva in sitt namn.
        Är namnet ifyllt så skickas vi till QuizActivity. Jag skickar med namnet på spelaren. Jag skickar även med informationen från
        det förra fragmentet gällande hur många frågot quizet ska innehålla.
         */
        val btnEnterNameMessage: TextView = view.findViewById(R.id.tv_enter_name_message)
        val btnStart: Button = view.findViewById(R.id.btn_start)
        btnEnterNameMessage.visibility = View.GONE

        btnStart.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {

                btnEnterNameMessage.visibility = View.VISIBLE
                Handler().postDelayed(
                    {
                        btnEnterNameMessage.visibility = View.GONE
                    },1500
                )
            }
            else {
                Singletons.userName = et_name.text.toString()
                val intent =
                    Intent(this@PlayFragment.context, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, Singletons.userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, Singletons.totalNrOfQuestions)
                startActivity(intent)
            }
        }
        return view
    }
}
