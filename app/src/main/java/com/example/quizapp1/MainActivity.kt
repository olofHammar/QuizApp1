package com.example.quizapp1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    companion object {
        var questionList: QuestionList? = null
    }

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var db  : AppDatabase
    private var soundPool = SoundPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val argentina = R.drawable.argentina
        val aland = R.drawable.aland
        val antarktis = R.drawable.antarktis
        val australien = R.drawable.australien
        val albanien = R.drawable.albanien
        val angola = R.drawable.angola
        val osterriket = R.drawable.osterriket
        val usa = R.drawable.usa
        val belgien = R.drawable.belgien
        val bolivia = R.drawable.bolivia

        job = Job()
        db = AppDatabase.getInstance(this)


        //deleteQuestions()
/*
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", argentina, "Argentina",
        "Honduras", "Uruguay", "El Salvador", 1))
        addNewQuestion(QuestionTemplate(0,"Vilket land tillhör denna flagga?", aland, "Norge", "Island",
            "Åland", "Färöarna", 3))
        addNewQuestion(QuestionTemplate(0,"Vilket land tillhör denna flagga?", antarktis, "Grönland", "Antarktis",
        "Atlantis", "Belgien", 2))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", australien, "Storbritannien",
            "Nya Zeeland", "Singapore","Australien", 4))
        addNewQuestion(QuestionTemplate(0,"Vilket land tillhör denna flagga?", albanien, "Andorra", "Armenien",
        "Albanien", "Georgien", 3))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", angola, "Angola", "Kongo",
        "Tanzania", "Uganda", 1))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", osterriket, "Polen", "Österriket",
        "Danmark", "Lettland", 2))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", usa, "Puerto Rico", "Texas",
            "USA", "Kanada", 3))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", belgien, "Tyskland", "Brunei",
        "Belgien", "Uganda", 3))
        addNewQuestion(QuestionTemplate(0, "Vilket land tillhör denna flagga?", bolivia, "Bolivia", "Senegal",
        "Etiopien", "Litauen", 1))

 */


        //Genom att kalla på SoundPool-funktionen load så hämtar jag alla ljud jag behöver i denna aktivitet.
        soundPool.load(this, R.raw.click_mouth_pop)

        //Här laddar jag först fragmentet HomeFragment som är den första sidan som visas när appen öppnas
        loadFragment(HomeFragment())
        loadAllQuestions()



        /*
        if (questions == null) {
            Log.d("!!!", "listan är tom")
        }
        else {
            Log.d("!!!", "listan är inte tom")
        }

         */




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

    fun loadAllQuestions() {
        val q = async(Dispatchers.IO) {
            db.questionDao.getAll()
        }

        launch {
            val list = q.await().toMutableList()
            questionList = QuestionList(list)
        }
    }


    fun addNewQuestion(questionTemplate: QuestionTemplate) {

        launch(Dispatchers.IO) {
            db.questionDao.insert(questionTemplate)
        }
    }
    fun deleteQuestions () {
        launch(Dispatchers.IO) {
            db.questionDao.delete()
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