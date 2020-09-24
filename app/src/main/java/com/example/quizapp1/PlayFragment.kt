package com.example.quizapp1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_play.*

class PlayFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_play, container, false)

        val btnStart: Button = view.findViewById(R.id.btn_start)

        btnStart.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this@PlayFragment.context, "Skriv in ditt namn", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent (this@PlayFragment.context, QuizQuestionsActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
            }
        }
        return view
    }
    }
