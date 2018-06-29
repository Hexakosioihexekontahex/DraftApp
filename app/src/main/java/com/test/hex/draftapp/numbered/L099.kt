package com.test.hex.draftapp.numbered

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l099.*
import java.util.concurrent.TimeUnit

private const val FILE_NAME = "filename"

class L099 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l099)

        val fileName: String? = intent.getStringExtra(FILE_NAME)
        if (fileName?.isEmpty() == false) {
            tvText1.text = fileName
        }

        button1.setOnClickListener {
            startService(Intent(this, L099Service::class.java))
        }
        button2.setOnClickListener {
            stopService(Intent(this, L099Service::class.java))
        }
    }
}

class L099Service : Service() {
    lateinit var nm: NotificationManager

    override fun onCreate() {
        super.onCreate()
        nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            TimeUnit.SECONDS.sleep(5)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        sendNotif()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun sendNotif() {
        val intention = Intent(this, L099::class.java)
        intention.putExtra(FILE_NAME, "somefile")
        val notif = NotificationCompat.Builder(
                this, "l099notif"
        ).apply {
            setSmallIcon(R.drawable.android_kotlin2)
            setTicker("Text in status bar")
            setWhen(System.currentTimeMillis())
            setContentInfo("contentInfo")
            setContentText("contentText")
            setContentTitle("contentTitle")
            setContentIntent(PendingIntent.getActivity(
                    this@L099Service, 0, intention, 0
            ))
        }.build()

        notif.flags = notif.flags or Notification.FLAG_AUTO_CANCEL
        nm.notify(1, notif)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
