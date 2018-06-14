package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l088.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class L088 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private lateinit var l088Task: L088Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l088)

        findViewById<Button>(R.id.button1).setOnClickListener {
            l088Task = L088Task()
            l088Task.execute()
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            showResult()
        }
    }

    private fun showResult() {
        var result: Int? = -1
        try {
            Log.d(LOG_TAG, "Try to get result")
            result = l088Task.get(1, TimeUnit.SECONDS)
            Log.d(LOG_TAG, "get() returns $result")
            Toast.makeText(this, "get() returns $result", Toast.LENGTH_LONG)
                    .show()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: TimeoutException) {
            Log.d(LOG_TAG, "get() timeout, result = $result")
            e.printStackTrace()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class L088Task : AsyncTask<Unit?, Unit?, Int?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(LOG_TAG, getString(R.string.begin))
            tvInfo.text = getString(R.string.begin)
        }

        override fun doInBackground(vararg params: Unit?): Int? {
            try {
                TimeUnit.SECONDS.sleep(5)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return 100500
        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            Log.d(LOG_TAG, "End. Result = $result")
            tvInfo.text = "End. Result = $result"
        }

    }
}
