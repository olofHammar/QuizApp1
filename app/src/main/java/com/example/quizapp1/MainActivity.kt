package com.example.quizapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        var btmNav: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        btmNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {}

                R.id.nav_play -> {}

                R.id.nav_high_score -> {true}

                else -> {}
            }
            true
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
}