package com.joylab.waitjoy

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class TimerActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView
    private lateinit var clipImage: ClipDrawable
    private var timeCount: Int = 0
    private var clipImageLevel: Int = 0
    private var timeCountMax: Int = 1

    private var timerTask: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        if( intent.hasExtra("setTime") ) {
            timeCountMax = intent.getIntExtra("setTime", 1) * 60
        }

        Log.d("WaitJoy", "setOnMaxTime: $timeCountMax")

        progressBar = findViewById(R.id.progressBar)
        progressBar.max = timeCountMax
        progressBar.progress = 0

        imageView = findViewById(R.id.imageView)
        clipImage = imageView.drawable as ClipDrawable

        startTimer();
    }

    override fun onBackPressed() {
        timerTask?.cancel()
        super.onBackPressed()

    }

    private fun startTimer() {
        timerTask = kotlin.concurrent.timer(period = 1000) {
            timeCount++
            clipImageLevel = calculateLevel()
            Log.d("WaitJoy", "1초마다 호출되고 있음 $timeCount, $clipImageLevel")
            if( timeCount <= timeCountMax ) {
                runOnUiThread {
                    progressBar.progress += 1
                    clipImage.level = clipImageLevel
                }
            } else {
                timerTask?.cancel()
            }
        }
    }

    private fun calculateLevel() : Int {
        return (timeCount * 10000) / timeCountMax
    }
}