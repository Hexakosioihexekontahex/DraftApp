package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l090.*
import java.util.concurrent.TimeUnit

class L090 : AppCompatActivity() {
    private var l090Task: L090Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l090)

        button1.setOnClickListener {
            l090Task = L090Task()
            l090Task?.execute()
        }
        button2.setOnClickListener {
            Toast.makeText(this,
                    (l090Task?.status?.toString() ?: "null")
                    + (if (l090Task?.isCancelled == true) "(cancelled)" else ""), Toast.LENGTH_SHORT)
                    .show()

        }
        button3.setOnClickListener {
            l090Task?.cancel(false)
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class L090Task : AsyncTask<Unit?, Unit?, Unit?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo.text = getString(R.string.begin)
        }

        override fun doInBackground(vararg params: Unit?): Unit? {
            try {
                for (i in 0..4) {
                    if (isCancelled) {
                        return null
                    }
                    TimeUnit.SECONDS.sleep(1)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            tvInfo.text = getString(R.string.end)
        }

        override fun onCancelled() {
            super.onCancelled()
            tvInfo.text = getString(R.string.cancelled)
        }
    }
}
