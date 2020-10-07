package com.example.quizapp1

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

var userName: String = ""
var totalNrOfQuestions: Int = 0
var highScoreOne: PlayerHighScore = PlayerHighScore()
var highScoreTwo: PlayerHighScore = PlayerHighScore()
var highScoreThree: PlayerHighScore = PlayerHighScore()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Här skapar jag variabeln pref som kan hämta in strängarna som sparats i ResultActivity
        //När dom hämtats sätter jag namn och poäng till rätt nummer i highscore-listan.
        val pref = getSharedPreferences("highScore", Context.MODE_PRIVATE)

        highScoreOne.playerName = pref.getString("highScoreOneName", "---").toString()
        highScoreOne.playerPoints = pref.getInt("highScoreOnePoints", 0)

        highScoreTwo.playerName = pref.getString("highScoreTwoName","---").toString()
        highScoreTwo.playerPoints = pref.getInt("highScoreTwoPoints",0)

        highScoreThree.playerName = pref.getString("highScoreThreeName","---").toString()
        highScoreThree.playerPoints = pref.getInt("highScoreThreePoints",0)

        //Här laddar jag först fragmentet HomeFragment som är den första sidan som visas när appen öppnas
        loadFragment(HomeFragment())

        //Under detta fragment finns en navigationsmeny där användaren kan välja att spela spelet eller se highscore-listan.
        //Varje itemId i navigationsmenyn är kopplat till ett fragment som öppnas vid klick på respektive itemId.
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_play -> {loadFragment(SetNrOfQuestionsFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_high_score -> {loadFragment(HighScoreFragment())
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
}