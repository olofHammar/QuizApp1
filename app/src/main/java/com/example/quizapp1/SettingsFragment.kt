package com.example.quizapp1

import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


class SettingsFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)


        val click: MediaPlayer = MediaPlayer.create(activity!!.applicationContext, R.raw.click_mouth_pop)
        val pref = context?.getSharedPreferences("soundSettings", Context.MODE_PRIVATE)
        var soundSetting: Int
        val btnBackHome: Button = view.findViewById(R.id.btn_back_home)
        val btnSoundOn: Button = view.findViewById(R.id.btn_sound_on)
        val btnSoundOff: Button = view.findViewById(R.id.btn_sound_off)

        soundSetting = pref!!.getInt("soundSettings", 0)

        when (soundSetting) {
            0 -> {
                btnSoundOn.setBackgroundResource(R.drawable.round_button_selected);
                btnSoundOn.setTextColor(Color.parseColor("#696969"))
            }
            1 -> {
                btnSoundOff.setBackgroundResource(R.drawable.round_button_selected);
                btnSoundOff.setTextColor(Color.parseColor("#696969"))
            }
        }


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
            unMute()
            soundSetting = 0
            pref.edit().putInt("soundSettings", soundSetting).apply()

        }
        btnSoundOff.setOnClickListener {
            click.start()
            defaultButtonView()
            btnSoundOff.setBackgroundResource(R.drawable.round_button_selected)
            btnSoundOff.setTextColor(Color.parseColor("#696969"))
            mute()
            soundSetting = 1
            pref.edit().putInt("soundSettings", soundSetting).apply()
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

    private fun mute() {

        val audioManager =
           activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        audioManager!!.setStreamMute(AudioManager.STREAM_NOTIFICATION, true)
        audioManager.setStreamMute(AudioManager.STREAM_ALARM, true)
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true)
        audioManager.setStreamMute(AudioManager.STREAM_RING, true)
        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true)
    }
    private fun unMute() {

        val audioManager =
            activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        audioManager!!.setStreamMute(AudioManager.STREAM_NOTIFICATION, false)
        audioManager.setStreamMute(AudioManager.STREAM_ALARM, false)
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false)
        audioManager.setStreamMute(AudioManager.STREAM_RING, false)
        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false)

    }
}