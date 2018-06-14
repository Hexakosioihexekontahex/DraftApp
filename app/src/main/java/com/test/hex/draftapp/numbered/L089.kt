package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l089.*
import org.jetbrains.anko.find
import java.util.concurrent.TimeUnit

class L089 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private lateinit var l089Task: L089Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l089)

        find<Button>(R.id.button1).setOnClickListener {
            l089Task = L089Task()
            l089Task.execute()
        }
        find<Button>(R.id.button2).setOnClickListener {
            cancelTask()
        }
    }

    private fun cancelTask() {
        Log.d(LOG_TAG, "cancel result: ${l089Task?.cancel(false)}")
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class L089Task : AsyncTask<Unit?, Unit?, Unit?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(LOG_TAG, getString(R.string.begin))
            tvInfo.text = getString(R.string.begin)
        }

        override fun doInBackground(vararg params: Unit?): Unit? {
            try {
                for (i in 0..4) {
                    TimeUnit.SECONDS.sleep(1)
                    if (isCancelled) return null
                    Log.d(LOG_TAG, "isCancelled: $isCancelled")
                }
            } catch (e: InterruptedException) {
                Log.d(LOG_TAG, "Interrupted")
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Log.d(LOG_TAG, getString(R.string.end))
            tvInfo.text = getString(R.string.end)
        }

        override fun onCancelled() {
            super.onCancelled()
            Log.d(LOG_TAG, getString(R.string.cancelled))
            tvInfo.text = getString(R.string.cancelled)
        }

    }
}
