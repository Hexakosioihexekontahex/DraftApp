package com.test.hex.draftapp.numbered

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class L094Service : Service() {
    private val LOG_TAG = "myLogs"

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L094Service onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L094Service onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "L094Service onStartCommand")
        readFlags(flags)
        Thread(Runnable {
            Log.d(LOG_TAG, "L094Service $startId")
            try {
                TimeUnit.SECONDS.sleep(15)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            Log.d(LOG_TAG, "L094Service#$startId end, stopSelfResult=${
            stopSelfResult(startId)
            }")
        }).start()
        return START_NOT_STICKY
    }

    private fun readFlags(flags: Int) {
        if ((flags and START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY) {
            Log.d(LOG_TAG, "START_FLAG_REDELIVERY")
        }
        if ((flags and START_FLAG_RETRY) == START_FLAG_RETRY) {
            Log.d(LOG_TAG, "START_FLAG_RETRY")
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
