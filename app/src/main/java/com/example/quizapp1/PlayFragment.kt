package com.example.quizapp1

import Communicator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_play.*

class PlayFragment : Fragment(){

    private var totalQuestions: Int? = 0
    private var soundPool = SoundPool()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_play, container, false)

        //Här hämtar jag totalNrOfQuestions från mainActivity
        //totalQuestions = arguments?.getInt("Q")

        val communicator= ViewModelProviders.of(activity!!).get(Communicator::class.java)

        communicator.nrOfQuestions.observe(this, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                totalQuestions = o!!.toString().toInt()
            }
        })

        soundPool.load(activity!!.applicationContext, R.raw.click_mouth_pop)
        //Detta är en toast som ber användaren att skriva in sitt namn. Denna fungerar på exakt samma sätt som toasten i tidigare fragment.
        val btnEnterNameMessage: TextView = view.findViewById(R.id.tv_enter_name_message)
        val btnStart: Button = view.findViewById(R.id.btn_start)

        btnEnterNameMessage.visibility = View.GONE

        /*
        Här sätter jag en klick-lyssnare till knappen btnStart. Knappen kräver att användaren skriver in text, annars visas en toast.
        Om användaren har skrivit in text så startas nästa aktivitet. Till nästa aktivitet så skickar jag med userName och totalQuestions.
         */
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
                val intent = Intent(this@PlayFragment.context, CountDownActivity::class.java)
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
