package com.test.hex.draftapp.numbered

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class L092Service : Service() {
    private val LOG_TAG = "myLogs"

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L092: service: onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "L092: service: onStartCommand")
        task()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L092: service: onDestroy")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "L092: service: onBind")
        return null
    }

    private fun task() {
        Thread(Runnable {
            try {
                for(i in 0..4) {
                    TimeUnit.SECONDS.sleep(1)
                    Log.d(LOG_TAG, "L092: service: task($i)")
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                stopSelf()
            }
        }).start()
    }
}
