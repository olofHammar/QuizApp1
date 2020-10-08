package com.example.quizapp1

import android.content.Context
import android.media.MediaPlayer

object Sounds {

    fun click (context: Context) {
        val click: MediaPlayer = MediaPlayer.create(context, R.raw.click_multimedia)
        click.start()
    }

    fun tick (context: Context) {
        val tick: MediaPlayer = MediaPlayer.create(context, R.raw.timer_tick)
        tick.start()
    }
}