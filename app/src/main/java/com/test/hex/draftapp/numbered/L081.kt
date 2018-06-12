package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.test.hex.draftapp.R
import java.util.concurrent.TimeUnit

class L081 : AppCompatActivity() {
    private val STATUS_DISCONNECTED = 0
    private val STATUS_CONNECTING = 1
    private val STATUS_CONNECTED = 2
    lateinit var btnConnect: Button
    lateinit var tvConnect: TextView
    lateinit var pbConnect: ProgressBar
    lateinit var h: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l081)

        tvConnect = findViewById(R.id.tvConnect)
        btnConnect = findViewById(R.id.btnConnect)
        pbConnect = findViewById(R.id.pbConnect)

        h = object : Handler() {
            override fun handleMessage(msg: Message?) {
                when (msg?.what) {
                    STATUS_DISCONNECTED -> {
                        btnConnect.isEnabled = true
                        pbConnect.visibility = ProgressBar.INVISIBLE
                        tvConnect.text = "Disconnected"
                    }
                    STATUS_CONNECTING -> {
                        btnConnect.isEnabled = false
                        pbConnect.visibility = ProgressBar.VISIBLE
                        tvConnect.text = "Connecting..."
                    }
                    STATUS_CONNECTED -> {
                        btnConnect.isEnabled = true
                        pbConnect.visibility = ProgressBar.GONE
                        tvConnect.text = "Connected!"
                        Toast.makeText(this@L081,
                                "Connected",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            }
        }
        h.sendEmptyMessage(STATUS_DISCONNECTED)

        btnConnect.setOnClickListener {
            Thread(Runnable {
                try {
                    h.sendEmptyMessage(STATUS_CONNECTING)
                    TimeUnit.SECONDS.sleep(7)
                    h.sendEmptyMessage(STATUS_CONNECTED)
                    TimeUnit.SECONDS.sleep(4)
                    h.sendEmptyMessage(STATUS_DISCONNECTED)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }).start()
        }
    }
}
