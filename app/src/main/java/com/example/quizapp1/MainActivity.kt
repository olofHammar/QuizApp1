package com.example.quizapp1

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val click: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.click_mouth_pop)
        val sound = Sound(this)
        //Här laddar jag först fragmentet HomeFragment som är den första sidan som visas när appen öppnas
        loadFragment(HomeFragment())

        //Under detta fragment finns en navigationsmeny där användaren kan välja att spela spelet eller se highscore-listan.
        //Varje itemId i navigationsmenyn är kopplat till ett fragment som öppnas vid klick på respektive itemId.
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {sound.clickStandard(); loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_play -> {sound.clickStandard(); loadFragment(SetNrOfQuestionsFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_high_score -> {sound.clickStandard(); loadFragment(HighScoreFragment())
                    return@setOnNavigationItemSelectedListener true}
            }
            false
        }
    }
    //Denna funktion skapar fragmentet i mainActivity
    private fun loadFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
/*
 Denna funktion laddar playFragment när funktionen kalla på från setNrOfQuestionsFragment
 och skickar med information om hur många frågor quizet ska ha.
 */
    override fun sendData(nr: Int) {
        val bundle = Bundle()
        bundle.putInt("Q", nr)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentPlay = PlayFragment()
        fragmentPlay.arguments = bundle

        transaction.replace(R.id.fragment_layout, fragmentPlay)
        transaction.commit()
    }
}