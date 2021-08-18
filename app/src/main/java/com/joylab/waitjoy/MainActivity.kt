package com.joylab.waitjoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var textLabel: TextView
    private lateinit var btnClear: Button
    private lateinit var btn01: Button
    private lateinit var btn05: Button
    private lateinit var btn10: Button
    private lateinit var btn30: Button
    private lateinit var btn60: Button

    private  var timeCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textLabel = findViewById(R.id.textView)

        btnClear = findViewById(R.id.btnClear);
        btn01 = findViewById(R.id.btn01)
        btn05 = findViewById(R.id.btn05)
        btn10 = findViewById(R.id.btn10)
        btn30 = findViewById(R.id.btn30)
        btn60 = findViewById(R.id.btn60)

        btn01.setOnClickListener { setOnTime(it) }
        btn05.setOnClickListener { setOnTime(it) }
        btn10.setOnClickListener { setOnTime(it) }
        btn30.setOnClickListener { setOnTime(it) }
        btn60.setOnClickListener { setOnTime(it) }

        btnClear.setOnClickListener {
            timeCount = 0
            updateTimeLabel()
        }

        textLabel.setOnClickListener { startTimer() }
    }

    private fun setOnTime( targetBtn: View ) {
        when ( targetBtn ) {
            btn01 -> timeCount += 1
            btn05 -> timeCount += 5
            btn10 -> timeCount += 10
            btn30 -> timeCount += 30
            btn60 -> timeCount += 60
            else -> null
        }
        Log.d("WaitJoy", "setOnTime: $timeCount")
        updateTimeLabel()
    }

    private fun updateTimeLabel() {
        textLabel.text = "$timeCount"
    }

    private fun startTimer() {
        val intent = Intent(this, TimerActivity::class.java)
        intent.putExtra("setTime", timeCount)
        startActivity(intent)
    }

}