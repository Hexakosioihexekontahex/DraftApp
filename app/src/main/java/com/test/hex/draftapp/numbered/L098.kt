package com.test.hex.draftapp.numbered

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l098.*
import java.util.*

private const val LOG_TAG = "myLogs"
private var interval: Long = 1000

class L098 : AppCompatActivity() {
    private var bound: Boolean? = null
    private lateinit var sConn: ServiceConnection
    private lateinit var intention: Intent
    private lateinit var l098Service: L098Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l098)

        intention = Intent(this, L098Service::class.java)
        sConn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(LOG_TAG, "L098 onServiceConnected")
                l098Service = (service as L098Service.L098Binder).service
                bound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(LOG_TAG, "L098 onServiceDisconnected")
                bound = false
            }
        }

        button1.setOnClickListener {
            //start
            startService(intention)
            val s = "interval = $interval"
            tvText1.text = s
        }
        button2.setOnClickListener {
            //up
            if (bound == true) {
                interval = l098Service.upInterval(500)
                val s = "interval = $interval"
                tvText1.text = s
            }
        }
        button3.setOnClickListener {
            //down
            if (bound == true) {
                interval = l098Service.downInterval(500)
                val s = "interval = $interval"
                tvText1.text = s
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(intention, sConn, 0)
    }

    override fun onStop() {
        super.onStop()
        if (bound == true) {
            unbindService(sConn)
            bound = false
        }
    }
}

class L098Service : Service() {
    private val binder = L098Binder()
    private lateinit var timer: Timer
    private var tTask: TimerTask? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L098Service onCreate")
        timer = Timer()
        schedule()
    }

    private fun schedule() {
        tTask?.cancel()
        if (interval > 0) {
            tTask = object : TimerTask() {
                override fun run() {
                    Log.d(LOG_TAG, "running")
                }
            }
            timer.schedule(tTask, interval, interval)
        }
    }

    fun upInterval(gap: Long): Long {
        interval += gap
        schedule()
        return interval
    }

    fun downInterval(gap: Long): Long {
        interval -= gap
        if (interval < 0) interval = 0
        schedule()
        return interval
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "L098Service onBind")
        return binder
    }


    internal inner class L098Binder : Binder() {
        val service: L098Service
            get() = this@L098Service
    }
}
