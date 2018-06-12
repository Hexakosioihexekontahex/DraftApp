package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.test.hex.draftapp.R

class L083 : AppCompatActivity() {

    private val LOG_TAG = "myLogs"
    private lateinit var h: Handler
    private val hc = Handler.Callback {
        Log.d(LOG_TAG, "what = ${it.what}")
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l083)

        h = Handler(hc)
        sendMessages()
    }

    private fun sendMessages() {
        Log.d(LOG_TAG, "send messages")
        h.sendEmptyMessageDelayed(1, 1000)
        h.sendEmptyMessageDelayed(2, 2000)
        h.sendEmptyMessageDelayed(3, 3000)
        h.sendEmptyMessageDelayed(2, 4000)
        h.sendEmptyMessageDelayed(5, 5000)
        h.sendEmptyMessageDelayed(2, 6000)
        h.sendEmptyMessageDelayed(7, 7000)
        h.removeMessages(2)
    }
}
