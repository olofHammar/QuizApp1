package com.example.quizapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        loadFragment(HomeFragment())


        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_play -> {loadFragment(PlayFragment())
                    return@setOnNavigationItemSelectedListener true}

                R.id.nav_high_score -> {loadFragment(HighScoreFragment())
                    return@setOnNavigationItemSelectedListener true}

            }
            false
        }
        /*
        btn_start.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this, "Skriv in ditt namn", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, et_name.text.toString())
                startActivity(intent)
                //stänger aktiviteten vi lämnar.
                finish()
            }
        }

         */
    }
    //Denna funktion skapar fragmentet i mainActivity
    private fun loadFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}