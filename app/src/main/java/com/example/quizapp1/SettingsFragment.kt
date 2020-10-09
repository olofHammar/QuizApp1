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
import java.util.ArrayList

class SettingsFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)


        val click: MediaPlayer = MediaPlayer.create(activity!!.applicationContext, R.raw.click_mouth_pop)
        var btnBackHome: Button = view.findViewById(R.id.btn_back_home)

        btnBackHome.setOnClickListener {

            click.start()
            val t: FragmentTransaction = this.fragmentManager!!.beginTransaction()
            val mFrag: Fragment = HomeFragment()
            t.replace(R.id.fragment_layout, mFrag)
            t.commit()
        }

        return view
    }
}