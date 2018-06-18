package com.test.hex.draftapp.numbered

import android.app.Service
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l093.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val LOG_TAG = "myLogs"

class L093 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l093)

        button1.setOnClickListener {
            startService(Intent(this, L093Service::class.java)
                    .putExtra("time", 7))
            startService(Intent(this, L093Service::class.java)
                    .putExtra("time", 2))
            startService(Intent(this, L093Service::class.java)
                    .putExtra("time", 4))
        }
    }
}

class L093Service : Service() {
    private lateinit var es: ExecutorService
    var result: Any? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L093Service: onCreate()")
        es = Executors.newFixedThreadPool(1)
        result = Any()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L093Service: onDestroy()")
        result = null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "L093Service: onStartCommand($startId)")
        val time = intent.getIntExtra("time", 1)
        es.execute {
            Log.d(LOG_TAG, "L093Run: startId($startId), time($time)")
            try {
                TimeUnit.SECONDS.sleep(time.toLong())
            } catch (e: InterruptedException) {
                Log.d(LOG_TAG, "L093Run#$startId: ${if (result != null)
                    result!!::class.java else "null"}")
                e.printStackTrace()
            } catch (e: NullPointerException) {
                Log.d(LOG_TAG, "L093Run#$startId: ${e::class.java}")
                e.printStackTrace()
            } catch (e: Exception) {
                Log.d(LOG_TAG, "L093Run#$startId: ${e::class.java}")
                e.printStackTrace()
            }
            Log.d(LOG_TAG, "L093Run#$startId stopSelfResult=${stopSelfResult(startId)}")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
