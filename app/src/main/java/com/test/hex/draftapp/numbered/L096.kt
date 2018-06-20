package com.test.hex.draftapp.numbered

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l096.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val LOG_TAG = "myLogs"

private const val TASK_CODE_1 = 1
private const val TASK_CODE_2 = 2
private const val TASK_CODE_3 = 3

private const val STATUS_START = 100
private const val STATUS_FINISH = 200

private const val PARAM_TIME = "time"
private const val PARAM_TASK = "task"
private const val PARAM_RESULT = "result"
private const val PARAM_STATUS = "status"

private const val BROADCAST_ACTION = "com.test.hex.draftapp.l96.servicebackbroadcast"

class L096 : AppCompatActivity() {
    private lateinit var br: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l096)

        tvText1.setText(R.string.task1)
        tvText2.setText(R.string.task2)
        tvText3.setText(R.string.task3)

        br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val task = intent?.getIntExtra(PARAM_TASK, 0)
                val status = intent?.getIntExtra(PARAM_STATUS, 0)
                Log.d(LOG_TAG, "onReceive: task = $task, status = $status")

                when (status) {
                    STATUS_START -> {
                        when (task) {
                            TASK_CODE_1 -> tvText1.setText(R.string.task1start)
                            TASK_CODE_2 -> tvText2.setText(R.string.task2start)
                            TASK_CODE_3 -> tvText3.setText(R.string.task3start)
                        }
                    }
                    STATUS_FINISH -> {
                        val result = intent.getIntExtra(PARAM_RESULT, 0)
                        when (task) {
                            TASK_CODE_1 -> {
                                val result1 = "Task1 finish, result = $result"
                                tvText1.text = result1
                            }
                            TASK_CODE_2 -> {
                                val result2 = "Task2 finish, result = $result"
                                tvText2.text = result2
                            }
                            TASK_CODE_3 -> {
                                val result3 = "Task3 finish, result = $result"
                                tvText3.text = result3
                            }
                        }
                    }
                }
            }
        }

        registerReceiver(br, IntentFilter(BROADCAST_ACTION))

        button1.setOnClickListener {
            Thread(Runnable {
                startService(Intent(this, L096Service::class.java)
                        .putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_TASK, TASK_CODE_1))
                startService(Intent(this, L096Service::class.java)
                        .putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_TASK, TASK_CODE_2))
                startService(Intent(this, L096Service::class.java)
                        .putExtra(PARAM_TIME, 6)
                        .putExtra(PARAM_TASK, TASK_CODE_3))
            }).start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
}

class L096Service : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private lateinit var es: ExecutorService

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "L096Service onCreate")
        es = Executors.newFixedThreadPool(2)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L096Service onDestroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "L096Service onStartCommand")

        val time = intent.getIntExtra(PARAM_TIME, 1)
        val task = intent.getIntExtra(PARAM_TASK, 0)

        es.execute {
            val intention = Intent(BROADCAST_ACTION)
            Log.d(LOG_TAG, "L096Run#$startId, time = $time")
            try {
                intention.putExtra(PARAM_TASK, task)
                intention.putExtra(PARAM_STATUS, STATUS_START)
                sendBroadcast(intention)

                TimeUnit.SECONDS.sleep(time.toLong())

                intention.putExtra(PARAM_STATUS, STATUS_FINISH)
                intention.putExtra(PARAM_RESULT, time * 100)
                sendBroadcast(intention)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                Log.d(LOG_TAG, "L096Run#$startId end, stopSelfResult = ${
                stopSelfResult(startId)
                }")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}
