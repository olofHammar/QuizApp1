package com.example.quizapp1

import android.media.MediaPlayer
import android.content.Context


class Sound (val context: Context) {

    fun clickStandard () {
        val click = MediaPlayer.create(context, R.raw.click_mouth_pop)
        click.start()
    }
    fun clickSlime () {
        val clickSlime = MediaPlayer.create(context, R.raw.click_slime_drip)
        clickSlime.start()
    }
    fun clickDjungle () {
        val clickDjunle = MediaPlayer.create(context, R.raw.click_djungle)
        clickDjunle.start()
    }
    fun tickMarimba () {
        val tickMarimba = MediaPlayer.create(context, R.raw.tick_finished_marimba)
        tickMarimba.start()
    }
    fun alertRight () {
        val alertRight = MediaPlayer.create(context, R.raw.alert_right_answer)
        alertRight.start()
    }
    fun alertWrong () {
        val alertWrong = MediaPlayer.create(context, R.raw.alert_wrong_answer)
        alertWrong.start()
    }
    fun loadPieChart () {
        val loadPieChart = MediaPlayer.create(context, R.raw.load_piechart)
        loadPieChart.start()
    }
    fun fanfare () {
        val fanfare = MediaPlayer.create(context, R.raw.fanfare_highscore)
        fanfare.start()
    }

}