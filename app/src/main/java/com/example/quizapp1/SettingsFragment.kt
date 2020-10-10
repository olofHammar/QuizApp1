package com.example.quizapp1

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.android.synthetic.main.fragment_play.*
import kotlinx.android.synthetic.main.fragment_set_nr_of_questions.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.ArrayList

class SettingsFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)


        val click: MediaPlayer = MediaPlayer.create(activity!!.applicationContext, R.raw.click_mouth_pop)
        val btnBackHome: Button = view.findViewById(R.id.btn_back_home)
        val btnSoundOn: Button = view.findViewById(R.id.btn_sound_on)
        val btnSoundOff: Button = view.findViewById(R.id.btn_sound_off)

        btnBackHome.setOnClickListener {

            click.start()
            val t: FragmentTransaction = this.fragmentManager!!.beginTransaction()
            val mFrag: Fragment = HomeFragment()
            t.replace(R.id.fragment_layout, mFrag)
            t.commit()
        }
        btnSoundOn.setOnClickListener {
            click.start()
            defaultButtonView()
            btnSoundOn.setBackgroundResource(R.drawable.round_button_selected)
            btnSoundOn.setTextColor(Color.parseColor("#696969"))
        }
        btnSoundOff.setOnClickListener {
            click.start()
            defaultButtonView()
            btnSoundOff.setBackgroundResource(R.drawable.round_button_selected)
            btnSoundOff.setTextColor(Color.parseColor("#696969"))
        }

        return view
    }
    private fun defaultButtonView() {
        val options = ArrayList<TextView>()
        options.add(0, btn_sound_on)
        options.add(1, btn_sound_off)

        for (option in options) {
            option.setBackgroundResource(R.drawable.round_button)
            option.setTextColor(Color.parseColor("#888888"))
        }
    }
}