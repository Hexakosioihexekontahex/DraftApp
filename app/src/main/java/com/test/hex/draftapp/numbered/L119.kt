package com.test.hex.draftapp.numbered

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L119 : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var alarmManager: AlarmManager
    lateinit var intent1: Intent
    lateinit var intent2: Intent
    lateinit var pIntent1: PendingIntent
    lateinit var pIntent2: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l119)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        findViewById<Button>(R.id.button1).setOnClickListener {
            intent1 = createIntent("action", "extra 1");
            pIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);

            intent2 = createIntent("action", "extra 2");
            pIntent2 = PendingIntent.getBroadcast(this, 0, intent2, 0);

            Log.d("qwe", "start");
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 2000, pIntent1);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 4000, pIntent2);
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            alarmManager.cancel(pIntent2)
        }
    }

    private fun compare() {
        Log.d(LOG_TAG, "intent1 == intent2 : ${intent1.filterEquals(intent2)}")
        Log.d(LOG_TAG, "pIntent1 == pIntent2 : ${pIntent1 == pIntent2}")
    }

    private fun createIntent(action: String, extra: String): Intent {
        return Intent(this, L119Receiver::class.java)
                .setAction(action)
                .putExtra("extra", extra)
    }

    private fun sendNotif(id: Int, pIntent: PendingIntent) {
        val notif = NotificationCompat.Builder(this, "Notif$id")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notification")
                .setContentText("Notif $id")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
        notif.flags = notif.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.notify(id, notif)
    }
}

class L119Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "onReceive")
        Log.d(LOG_TAG, "action: ${intent?.action}")
        Log.d(LOG_TAG, "extra: ${intent?.getStringExtra("extra")}")
    }
}
