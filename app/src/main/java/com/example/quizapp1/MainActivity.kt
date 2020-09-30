package com.example.quizapp1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
var userName: String = ""
var totalNrOfQuestions: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadFragment(HomeFragment())

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