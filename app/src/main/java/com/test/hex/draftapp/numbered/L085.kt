package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l085.*
import java.util.concurrent.TimeUnit

class L085 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l085)

        Thread(Runnable {
            try {
                Log.d(LOG_TAG, "Start")
                TimeUnit.SECONDS.sleep(2)
                runOnUiThread {
                    tvInfo.text = "Runnable 1"
                    Log.d(LOG_TAG, "Runnable 1")
                }
                TimeUnit.SECONDS.sleep(1)
                tvInfo.postDelayed({
                    tvInfo.text = "Runnable 3"
                    Log.d(LOG_TAG, "Runnable 3")
                    Log.d(LOG_TAG, "End")
                }, 2000)
                tvInfo.post({
                    tvInfo.text = "Runnable 2"
                    Log.d(LOG_TAG, "Runnable 2")
                })
            } catch (e: InterruptedException) {
                e.printStackTrace()
                Log.d(LOG_TAG, "InterruptedException")
            }
        }).start()
    }
}
