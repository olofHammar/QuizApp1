package com.example.quizapp1

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.plattysoft.leonids.ParticleSystem


public class ConfettiExampleActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewById<View>(R.id.button1).setOnClickListener(this)
    }

    override fun onClick(arg0: View) {
        ParticleSystem(this, 80, R.drawable.confeti2, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
            .setRotationSpeed(144f)
            .setAcceleration(0.000017f, 90)
            .emit(findViewById(R.id.emiter_top_right), 8)
        ParticleSystem(this, 80, R.drawable.confeti3, 10000)
            .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
            .setRotationSpeed(144f)
            .setAcceleration(0.000017f, 90)
            .emit(findViewById(R.id.emiter_top_left), 8)
    }
}