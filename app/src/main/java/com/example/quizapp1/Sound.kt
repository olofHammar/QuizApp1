package com.example.quizapp1

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener


class Sound (val context: Context) {

    fun clickStandard () {
        var click = MediaPlayer.create(context, R.raw.click_mouth_pop)
        click.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            click = null
        })
        click.start()
    }
    fun clickSlime () {
        var clickSlime = MediaPlayer.create(context, R.raw.click_slime_drip)
        clickSlime.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            clickSlime = null
        })
        clickSlime.start()
    }
    fun clickDjungle () {
        var clickDjunle = MediaPlayer.create(context, R.raw.click_djungle)
        clickDjunle.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            clickDjunle = null
        })
        clickDjunle.start()
    }
    fun tickMarimba () {
        var tickMarimba = MediaPlayer.create(context, R.raw.tick_finished_marimba)
        tickMarimba.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            tickMarimba = null
        })
        tickMarimba.start()
    }
    fun alertRight () {
        var alertRight = MediaPlayer.create(context, R.raw.alert_right_answer)
        alertRight.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            alertRight = null
        })
        alertRight.start()
    }
    fun alertWrong () {
        var alertWrong = MediaPlayer.create(context, R.raw.alert_wrong_answer)
        alertWrong.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            alertWrong = null
        })
        alertWrong.start()
    }
    fun loadPieChart () {
        var loadPieChart = MediaPlayer.create(context, R.raw.load_piechart)
        loadPieChart.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            loadPieChart = null
        })
        loadPieChart.start()
    }
    fun fanfare () {
        var fanfare = MediaPlayer.create(context, R.raw.fanfare_highscore)
        fanfare.setOnCompletionListener(OnCompletionListener { mp ->
            mp.reset()
            mp.release()
            fanfare = null
        })
        fanfare.start()
    }

}