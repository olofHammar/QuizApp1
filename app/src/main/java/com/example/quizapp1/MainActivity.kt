package com.example.quizapp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Communicator {
//I varje aktivitet/fragment där jag använder ljud så skapar jag ett objekt av klassen SoundPool
    private var soundPool = SoundPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Genom att kalla på SoundPool-funktionen load så hämtar jag alla ljud jag behöver i denna aktivitet.
        soundPool.load(this, R.raw.click_mouth_pop)

        //Här laddar jag först fragmentet HomeFragment som är den första sidan som visas när appen öppnas
        loadFragment(HomeFragment())

        /*
        Under detta fragment finns en navigationsmeny där användaren kan välja att spela spelet eller se highscore-listan.
        Varje itemId i navigationsmenyn är kopplat till ett fragment som öppnas vid klick på respektive itemId.

         */
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    soundPool.play(R.raw.click_mouth_pop); loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_play -> {
                    soundPool.play(R.raw.click_mouth_pop); loadFragment(SetNrOfQuestionsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_high_score -> {
                    soundPool.play(R.raw.click_mouth_pop); loadFragment(HighScoreFragment())
                    return@setOnNavigationItemSelectedListener true
                }
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
    Denna funktion laddar playFragment när funktionen kallas på från setNrOfQuestionsFragment
    och skickar med information om hur många frågor quizet ska ha.
    */
    override fun sendData(nr: Int) {

        val bundle = Bundle()
        bundle.putInt("Q", nr)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentPlay = PlayFragment()
        fragmentPlay.arguments = bundle

        transaction.replace(R.id.fragment_layout, fragmentPlay)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    //Denna funktion slänger bort alla ljud som skapats i denna aktivitet när aktiviteten avslutas
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}