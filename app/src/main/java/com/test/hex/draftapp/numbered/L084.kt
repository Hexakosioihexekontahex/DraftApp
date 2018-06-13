package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import com.test.hex.draftapp.R
import java.util.concurrent.TimeUnit

class L084 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private val max = 100

    private lateinit var pbCount: ProgressBar
    private lateinit var tvDetails: TextView
    private lateinit var cbDetails: CheckBox
    private lateinit var h: Handler

    private var cnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l084)


        h = Handler()

        pbCount = findViewById(R.id.pbCount)
        pbCount.max = max
        pbCount.progress = 0

        tvDetails = findViewById(R.id.tvDetails)

        cbDetails = findViewById(R.id.cbDetails)
        cbDetails.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                tvDetails.visibility = View.VISIBLE
                h.post(showDetails)
            } else {
                tvDetails.visibility = View.GONE
                h.removeCallbacks(showDetails)
            }
        }
        val t = Thread(Runnable {
            cnt = 1
            try {
                for (i in cnt until max) {
                    cnt = i
                    TimeUnit.MILLISECONDS.sleep(100)
                    h.post(updateProgress)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
        t.start()
    }

    private val updateProgress: Runnable = Runnable {
        pbCount.progress = cnt
    }

    private val showDetails: Runnable = Runnable {
        Log.d(LOG_TAG, "showDetails")
        val s = "Count = $cnt"
        tvDetails.text = s
        showDetails()
    }

    private fun showDetails() = h.postDelayed(showDetails, 1000)
}
