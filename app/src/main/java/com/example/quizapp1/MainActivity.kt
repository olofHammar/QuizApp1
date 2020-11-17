package com.example.quizapp1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var questionList : QuestionList? = null
    var curretQuestion : QuestionTemplate? = null
    private lateinit var db  : AppDatabase


    private var soundPool = SoundPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val argentina = R.drawable.argentina

        job = Job()
        db = AppDatabase.getInstance(this)

        addNewQuestion(
            QuestionTemplate(0, "Vilket land tillhör denna flagga?", argentina, "Argentina",
        "Honduras", "Uruguay", "El Salvador", 1)
        )


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
                    soundPool.play(R.raw.click_mouth_pop); loadFragment(PlayFragment())
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
    fun addNewQuestion(questionTemplate: QuestionTemplate) {

        launch(Dispatchers.IO) {
            db.questionDao.insert(questionTemplate)
        }
    }
    //Denna funktion skapar fragmentet i mainActivity
    private fun loadFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    //Denna funktion slänger bort alla ljud som skapats i denna aktivitet när aktiviteten avslutas
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}