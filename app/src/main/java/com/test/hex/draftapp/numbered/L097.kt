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
import kotlinx.android.synthetic.main.l097.*

private const val LOG_TAG = "myLogs"

class L097 : AppCompatActivity() {

    private var bound: Boolean? = null
    private lateinit var sConn: ServiceConnection
    private lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l097)

        intention = Intent("com.test.hex.draftapp.l97.servicebinding")

        sConn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(LOG_TAG, "L097 onServiceConnected")
                bound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(LOG_TAG, "L097 onServiceDisconnected")
                bound = false
            }
        }

        button1.setOnClickListener { startService(intention) }
        button2.setOnClickListener { stopService(intention) }
        button3.setOnClickListener { bindService(intention, sConn, 0) }
        button4.setOnClickListener {
            if (bound == true) {
                unbindService(sConn)
                bound = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (bound == true) {
            unbindService(sConn)
            bound = false
        }
    }
}

class L097Service : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L097Service onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(LOG_TAG, "L097Service onBind")
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        Log.d(LOG_TAG, "L097Service onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(LOG_TAG, "L097Service onUnbind")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L097Service onDestroy")
    }
}
