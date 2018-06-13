package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.test.hex.draftapp.R
import java.util.concurrent.TimeUnit


class L086 : AppCompatActivity() {
    internal lateinit var tvInfo: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l086)

        tvInfo = findViewById(R.id.tvInfo)
        findViewById<Button>(R.id.button1).setOnClickListener {
            L086Task().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class L086Task : AsyncTask<Unit?, Unit?, Unit?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo.setText(R.string.begin)
        }

        override fun doInBackground(vararg units: Unit?): Unit? {
            try {
                TimeUnit.SECONDS.sleep(2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(aUnit: Unit?) {
            super.onPostExecute(aUnit)
            tvInfo.setText(R.string.end)
        }
    }
}
