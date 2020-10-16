package com.example.quizapp1

import android.content.Context
import android.graphics.Color
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class SettingsFragment : Fragment(){

    private var soundPool = SoundPool()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        soundPool.load(activity!!.applicationContext, R.raw.click_mouth_pop)
        soundPool.load(activity!!.applicationContext, R.raw.click_slime_drip)

        val pref = context?.getSharedPreferences("soundSettings", Context.MODE_PRIVATE)

        //Denna variabel använder jag för att spara ljudinställningarna i sharedPreferences
        var soundSetting = pref!!.getInt("soundSettings", 0)

        val btnBackHome: Button = view.findViewById(R.id.btn_back_home)
        val btnSoundOn: Button = view.findViewById(R.id.btn_sound_on)
        val btnSoundOff: Button = view.findViewById(R.id.btn_sound_off)

        when (soundSetting) {
            0 -> {
                btnSoundOn.setBackgroundResource(R.drawable.round_button_selected)
                btnSoundOn.setTextColor(Color.parseColor("#696969"))
            }
            1 -> {
                btnSoundOff.setBackgroundResource(R.drawable.round_button_selected)
                btnSoundOff.setTextColor(Color.parseColor("#696969"))
            }
        }

        btnBackHome.setOnClickListener {

            soundPool.play(R.raw.click_mouth_pop)
            val t: FragmentTransaction = this.fragmentManager!!.beginTransaction()
            val mFrag: Fragment = HomeFragment()
            t.replace(R.id.fragment_layout, mFrag)
            t.commit()
        }
        btnSoundOn.setOnClickListener {
            soundPool.play(R.raw.click_slime_drip)
            defaultButtonView()
            btnSoundOn.setBackgroundResource(R.drawable.round_button_selected)
            btnSoundOn.setTextColor(Color.parseColor("#696969"))
            unMute()
            soundSetting = 0
            pref.edit().putInt("soundSettings", soundSetting).apply()

        }
        btnSoundOff.setOnClickListener {
            soundPool.play(R.raw.click_slime_drip)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun mute() {

        val audioManager =
           activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        audioManager!!.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_TOGGLE_MUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_TOGGLE_MUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_TOGGLE_MUTE,0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_TOGGLE_MUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_TOGGLE_MUTE, 0)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun unMute() {

        val audioManager =
            activity!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        audioManager!!.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE,0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0)
        audioManager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0)
    }

}