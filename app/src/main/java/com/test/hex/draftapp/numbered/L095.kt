package com.test.hex.draftapp.numbered

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l095.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val LOG_TAG = "myLogs"

private const val TASK_CODE_1 = 1
private const val TASK_CODE_2 = 2
private const val TASK_CODE_3 = 3

const val STATUS_START = 100
const val STATUS_FINISH = 200

const val PARAM_TIME = "time"
const val PARAM_P_INTENT = "pendingIntent"
const val PARAM_RESULT = "result"

class L095 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l095)

        tvText1.setText(R.string.task1)
        tvText2.setText(R.string.task2)
        tvText3.setText(R.string.task3)

        button1.setOnClickListener {
            Thread(Runnable {
                /*
                var pi: PendingIntent = createPendingResult(TASK_CODE_1, null, 0)
                var intention: Intent

                intention = Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_P_INTENT, pi)
                startService(intention)

                pi = createPendingResult(TASK_CODE_2, null, 0)
                intention = Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_P_INTENT, pi)
                startService(intention)

                pi = createPendingResult(TASK_CODE_3, null, 0)
                intention = Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 6)
                        .putExtra(PARAM_P_INTENT, pi)
                startService(intention)*/

                startService(Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_P_INTENT,
                                createPendingResult(TASK_CODE_1, Intent(), 0)))

                startService(Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_P_INTENT,
                                createPendingResult(TASK_CODE_2, Intent(), 0)))

                startService(Intent(this, L095Service::class.java)
                        .putExtra(PARAM_TIME, 6)
                        .putExtra(PARAM_P_INTENT,
                                createPendingResult(TASK_CODE_3, Intent(), 0)))
            }).start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(LOG_TAG, "requestCode=$requestCode, resultCode=$resultCode;")

        when (resultCode) {
            STATUS_START -> {
                when (requestCode) {
                    TASK_CODE_1 -> tvText1.setText(R.string.task1start)
                    TASK_CODE_2 -> tvText2.setText(R.string.task2start)
                    TASK_CODE_3 -> tvText3.setText(R.string.task3start)
                }
            }
            STATUS_FINISH -> {
                val result = data?.getIntExtra(PARAM_RESULT, 0)
                when (requestCode) {
                    TASK_CODE_1 -> {
                        val result1 = "Task1 finish, result = $result"
                        tvText1.text = result1
                    }
                }
                when (requestCode) {
                    TASK_CODE_2 -> {
                        val result2 = "Task2 finish, result = $result"
                        tvText2.text = result2
                    }
                }
                when (requestCode) {
                    TASK_CODE_3 -> {
                        val result3 = "Task3 finish, result = $result"
                        tvText3.text = result3
                    }
                }
            }
        }
    }
}

class L095Service : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private var es: ExecutorService? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L095Service onCreate")
        es = Executors.newFixedThreadPool(2)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L095Service onDestroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "L095Service onStartCommand")
        val time = intent.getIntExtra(PARAM_TIME, 1)
        val pi: PendingIntent = intent.getParcelableExtra(PARAM_P_INTENT)
        es?.execute {
            Log.d(LOG_TAG, "L095Run#$startId start, time($time)")
            try {
                pi.send(STATUS_START)

                TimeUnit.SECONDS.sleep(time.toLong())

                val intention = Intent().putExtra(PARAM_RESULT, time * 100)
                pi.send(this@L095Service, STATUS_FINISH, intention)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: PendingIntent.CanceledException) {
                e.printStackTrace()
            } finally {
                Log.d(LOG_TAG, "L095Service#$startId end, stopSelfResult:${stopSelfResult(startId)}")
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }
}
