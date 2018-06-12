package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.test.hex.draftapp.R
import java.util.concurrent.TimeUnit

class L080 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    lateinit var h: Handler
    lateinit var tvInfo: TextView
    lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l080)

        tvInfo = findViewById(R.id.tvInfo)
        btnStart = findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            btnStart.isEnabled = false
            Thread(Runnable {
                for (i in 1..10) {
                    downloadFile()
                    h.sendEmptyMessage(i)
                    Log.d(LOG_TAG, "Downloaded files count = $i")
                }
            }).start()
        }
        findViewById<Button>(R.id.btnTest).setOnClickListener {
            Log.d(LOG_TAG, "test")
        }
        h = object : Handler() {
            override fun handleMessage(msg: android.os.Message) {
                // обновляем TextView
                tvInfo.text = "Downloaded files count = " + msg.what
                if (msg.what == 10) btnStart.isEnabled = true
            }
        }

    }

    private fun downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
