package com.example.quizapp1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/*
Detta fragment är det första som syns när appen startas. Fragmentet visar ett välkomstmeddelande och har även en knapp som
skickar användaren vidare till settings-fragmentet.
 */
class HomeFragment : Fragment(){

    private var soundPool = SoundPool()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        soundPool.load(activity!!.applicationContext, R.raw.click_mouth_pop)
        Log.d("!!!", "Hej hej")

        val btnSettings: Button = view.findViewById(R.id.btn_settings)

        btnSettings.setOnClickListener {

            soundPool.play(R.raw.click_mouth_pop)
            val t: FragmentTransaction = this.fragmentManager!!.beginTransaction()
            val mFrag: Fragment = SettingsFragment()
            t.replace(R.id.fragment_layout, mFrag)
            t.commit()
        }

        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}