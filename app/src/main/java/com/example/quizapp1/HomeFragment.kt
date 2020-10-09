package com.example.quizapp1

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class HomeFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val click: MediaPlayer = MediaPlayer.create(activity!!.applicationContext, R.raw.click_mouth_pop)

        var btnSettings: Button = view.findViewById(R.id.btn_settings)

        btnSettings.setOnClickListener {

            click.start()
            val t: FragmentTransaction = this.fragmentManager!!.beginTransaction()
            val mFrag: Fragment = SettingsFragment()
            t.replace(R.id.fragment_layout, mFrag)
            t.commit()
        }

        return view
    }
}