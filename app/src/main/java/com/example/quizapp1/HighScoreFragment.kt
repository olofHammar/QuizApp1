package com.example.quizapp1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

//Detta är fragment-klassen som visar spelets highscore-lista
class HighScoreFragment : Fragment(){
    @ExperimentalStdlibApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_high_score, container, false)

        /*
        Här skapar jag en pref-variabel som jag använder för att kunna hämta in den sparade highscore-listan
        genom SharedPreferences.
         */
        val pref = context?.getSharedPreferences("highScore", Context.MODE_PRIVATE)
        val pOne = PlayerHighScore()
        pOne.playerName = pref?.getString("highScoreOneName", "---").toString()
        val pTwo = PlayerHighScore()
        pOne.playerPoints = pref!!.getInt("highScoreOnePoints", 0)
        pTwo.playerName = pref.getString("highScoreTwoName", "---").toString()
        pTwo.playerPoints = pref.getInt("highScoreTwoPoints", 0)
        val pThree = PlayerHighScore()
        pThree.playerName = pref.getString("highScoreThreeName", "---").toString()
        pThree.playerPoints = pref.getInt("highScoreThreePoints", 0)
        /*
        Här sätter jag två variabler för varje highscore, namn och poäng. Dessa kopplas till rätt textView
        och sedan sätter jag rätt text till respektive textView.
         */
        val tvHsOneName: TextView = view.findViewById(R.id.tv_hs_one_name)
        val tvHsOnePoints: TextView = view.findViewById(R.id.tv_hs_one_points)
        val tvHsTwoName: TextView = view.findViewById(R.id.tv_hs_two_name)
        val tvHsTwoPoints: TextView = view.findViewById(R.id.tv_hs_two_points)
        val tvHsThreeName: TextView = view.findViewById(R.id.tv_hs_three_name)
        val tvHsThreePoints: TextView = view.findViewById(R.id.tv_hs_three_points)


        tvHsOneName.text = pOne.playerName.capitalize(Locale.ROOT)
        tvHsOnePoints.text = resources.getString(R.string.player_highscore_points, pOne.playerPoints.toString())

        tvHsTwoName.text = pTwo.playerName.capitalize(Locale.ROOT)
        tvHsTwoPoints.text = resources.getString(R.string.player_highscore_points, pTwo.playerPoints.toString())

        tvHsThreeName.text = pThree.playerName.capitalize(Locale.ROOT)
        tvHsThreePoints.text = resources.getString(R.string.player_highscore_points, pThree.playerPoints.toString())

        return view
    }
}