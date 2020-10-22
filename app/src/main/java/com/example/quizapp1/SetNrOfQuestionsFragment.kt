package com.example.quizapp1

import Communicator
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_set_nr_of_questions.*
import java.util.*

class SetNrOfQuestionsFragment : Fragment(){

    //Här skapar jag en communicator så att jag kan skicka data tillbaka till mainactivity och sedan vidare till Playfragment
    private var model: Communicator?=null
    private var soundPool = SoundPool()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_set_nr_of_questions, container, false)

        model= ViewModelProviders.of(activity!!).get(Communicator::class.java)


        soundPool.load(activity!!.applicationContext, R.raw.click_slime_drip)
        soundPool.load(activity!!.applicationContext, R.raw.click_mouth_pop)

        //Jag sätter värdet på totalNr.. till 0 vid uppstart om detta värde inte ändras så visas en toast som ber användaren välja ett nummer.
        var totalNrOfQuestions = 0
        val btnSelectTen: Button = view.findViewById(R.id.btn_select_ten)
        val btnSelectTwenty: Button = view.findViewById(R.id.btn_select_twenty)
        val btnSelectThirty: Button = view.findViewById(R.id.btn_select_thirty)
        val btnNext: Button = view.findViewById(R.id.btn_next)

        //Detta är toasten som säger åt användaren att välja nummer
        val btnEnterNrOfQuestionsMessage = view.findViewById<TextView>(R.id.tv_enter_nr_of_questions_message)
        //Den är osynlig så länge inte användaren klickar på next utan att välja ett nummer.
        btnEnterNrOfQuestionsMessage.visibility = View.GONE

        /*Här sätter jag klick-lyssnare på varje nummer-knapp. Varje knapp använder funktionen defaultButtonView så att knappens design
        återställs när användaren klickar på en annan nummer-knapp.
         */
        btnSelectTen.setOnClickListener {
            soundPool.play(R.raw.click_slime_drip)
            defaultButtonView()
            btnSelectTen.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTen.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 10
        }
        btnSelectTwenty.setOnClickListener {
            soundPool.play(R.raw.click_slime_drip)
            defaultButtonView()
            btnSelectTwenty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectTwenty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 20
        }
        btnSelectThirty.setOnClickListener {
            soundPool.play(R.raw.click_slime_drip)
            defaultButtonView()
            btnSelectThirty.setBackgroundResource(R.drawable.round_button_selected)
            btnSelectThirty.setTextColor(Color.parseColor("#696969"))
            totalNrOfQuestions = 30
        }
        /*
        Denna knapp visaren antingen en toast om användaren ej gjort ett val alt skickas antalet valda frågor tillbaka till
        mainactivity genom communicators funktion sendaData.
         */
        btnNext.setOnClickListener {

            soundPool.play(R.raw.click_mouth_pop)
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
                model!!.setMsgCommunicator(totalNrOfQuestions)
                val myfragment = PlayFragment()
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_layout, myfragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        return view
    }
//Denna funktion återställer alla knappar till default-design
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

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
