package com.test.hex.draftapp.numbered

import android.app.IntentService
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l100.*
import java.util.concurrent.TimeUnit

private const val LOG_TAG = "myLogs"

class L100 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l100)

        button1.setOnClickListener {
            startService(Intent(this, L100Service::class.java)
                    .putExtra("time", 3).putExtra("label", "Call 1"))
            startService(Intent(this, L100Service::class.java)
                    .putExtra("time", 1).putExtra("label", "Call 2"))
            startService(Intent(this, L100Service::class.java)
                    .putExtra("time", 4).putExtra("label", "Call 3"))
        }
    }
}

class L100Service : IntentService("l100Service") {
    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L100Service onCreate")
    }

    override fun onHandleIntent(intent: Intent) {
        val tm = intent.getIntExtra("time", 0)
        val label = intent.getStringExtra("label")
        Log.d(LOG_TAG, "onHandleIntent start $label")
        try {
            TimeUnit.SECONDS.sleep(tm.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d(LOG_TAG, "onHandleIntent end $label")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L100Service onDestroy")
    }
}

/*
* class L100Service : Service() {
* ...
* startForeGroundCompat(Int, NotificationCompat.Builder(...) { ... }.build())
* ...
* */
