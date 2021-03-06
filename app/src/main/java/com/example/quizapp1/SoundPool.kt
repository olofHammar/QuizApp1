package com.example.quizapp1

import android.content.Context
import android.media.AudioAttributes
//I denna klass skapar jag en Soundpool med funktioner för att ladda, spela och radera ljud.

class SoundPool(maxStreams: Int = 3) {
    val audioAttributes = AudioAttributes
        .Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()

    var soundPool = android.media.SoundPool.Builder()
        .setAudioAttributes(audioAttributes)
        .setMaxStreams(maxStreams)
        .build()

    val aliases = mutableMapOf<Int, Int>()

    fun load(context: Context, resourceId: Int) {
        aliases[resourceId] = soundPool.load(context, resourceId, 1)
    }

    fun release() {
        soundPool.release()
        soundPool = null
    }

    fun play(resourceId: Int) {
        aliases[resourceId]?.let {
            soundPool.play(it, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}