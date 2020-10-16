package com.example.quizapp1

import android.content.Context
import android.media.AudioAttributes

/**
 * Created by songc on 2017/02/21.
 * @param maxStreams 同時に再生できる効果音の数。デフォルトでは３つ。
 */
class SoundPool(maxStreams: Int = 3) {
    val audioAttributes = AudioAttributes
        .Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
        .build()

    val soundPool = android.media.SoundPool.Builder()
        .setAudioAttributes(audioAttributes)
        .setMaxStreams(maxStreams)
        .build()

    val aliases = mutableMapOf<Int, Int>()

    fun load(context: Context, resourceId: Int) {
        aliases[resourceId] = soundPool.load(context, resourceId, 1)
    }

    fun unload(resourceId: Int) {
        aliases[resourceId]?.let { soundPool.unload(it) }
    }

    fun play(resourceId: Int) {
        aliases[resourceId]?.let {
            soundPool.play(it, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}