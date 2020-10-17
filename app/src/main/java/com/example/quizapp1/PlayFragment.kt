package com.example.quizapp1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_play.*
import kotlinx.android.synthetic.main.fragment_play.view.*

class PlayFragment : Fragment(){

    private var totalQuestions: Int? = 0
    private var soundPool = SoundPool()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_play, container, false)
        /*
        Här skapar jag en variabel som kopplas till startknappen. Sedan sätter jag en klicklyssnare till den knappen.
        Lyssnaren kräver att användaren skriver in sitt namn, görs ej detta så visas en Toast som ber användaren skriva in sitt namn.
        Är namnet ifyllt så skickas vi till QuizActivity. Jag skickar med namnet på spelaren. Jag skickar även med informationen från
        det förra fragmentet gällande hur många frågot quizet ska innehålla.
         */
        totalQuestions = arguments?.getInt("Q")
        soundPool.load(activity!!.applicationContext, R.raw.click_mouth_pop)
        //Detta är en toast som ber användaren att skriva in sitt namn.
        val btnEnterNameMessage: TextView = view.findViewById(R.id.tv_enter_name_message)
        val btnStart: Button = view.findViewById(R.id.btn_start)
        btnEnterNameMessage.visibility = View.GONE

        btnStart.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {

                soundPool.play(R.raw.click_mouth_pop)
                btnEnterNameMessage.visibility = View.VISIBLE
                val messageTimer = object : CountDownTimer(1500, 1000) {
                    override fun onFinish() {
                        btnEnterNameMessage.visibility = View.GONE
                    }
                    override fun onTick(millisUntilFinished: Long) {
                    }
                }
                messageTimer.start()
            }
            else {

                soundPool.play(R.raw.click_mouth_pop)
                val userName = et_name.text.toString()
                val intent =
                    Intent(this@PlayFragment.context, CountDownActivity::class.java)
                startActivity(intent)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, totalQuestions)
                startActivity(intent)
                activity!!.finish()
            }
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
