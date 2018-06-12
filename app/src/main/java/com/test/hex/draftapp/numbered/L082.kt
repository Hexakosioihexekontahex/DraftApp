package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.test.hex.draftapp.R
import java.util.*
import java.util.concurrent.TimeUnit

class L082 : AppCompatActivity() {
    private val STATUS_NONE = 0
    private val STATUS_CONNECTING = 1
    private val STATUS_CONNECTED = 2
    private val STATUS_DOWNLOAD_START = 3
    private val STATUS_DOWNLOAD_FILE = 4
    private val STATUS_DOWNLOAD_END = 5
    private val STATUS_DOWNLOAD_NONE = 6

    private lateinit var h: Handler
    lateinit var btnConnect: Button
    lateinit var tvStatus: TextView
    lateinit var pbDownload: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l082)

        tvStatus = findViewById(R.id.tvStatus)
        btnConnect = findViewById(R.id.btnConnect)
        pbDownload = findViewById(R.id.pbDownload)

        h = object : Handler() {
            override fun handleMessage(msg: Message?) {
                when (msg?.what) {
                    STATUS_NONE -> {
                        btnConnect.isEnabled = true
                        tvStatus.text = "Disconnected"
                        pbDownload.visibility = ProgressBar.GONE
                    }
                    STATUS_CONNECTING -> {
                        btnConnect.isEnabled = false
                        tvStatus.text = "Connecting"
                    }
                    STATUS_CONNECTED -> {
                        tvStatus.text = "Connected"
                    }
                    STATUS_DOWNLOAD_START -> {
                        tvStatus.text = "Start downloading ${msg.arg1} files"
                        pbDownload.max = msg.arg1
                        pbDownload.progress = 0
                        pbDownload.visibility = ProgressBar.VISIBLE
                    }
                    STATUS_DOWNLOAD_FILE -> {
                        tvStatus.text = "Downloading. Left ${msg.arg2} files"
                        pbDownload.progress = msg.arg1
                        saveFile(msg.obj as ByteArray)
                    }
                    STATUS_DOWNLOAD_END -> {
                        tvStatus.text = "Downloading complete!"
                    }
                    STATUS_DOWNLOAD_NONE -> {
                        tvStatus.text = "There are no files to download"
                    }
                }
            }
        }
        h.sendEmptyMessage(STATUS_NONE)

        btnConnect.setOnClickListener {
            Thread(Runnable {
                var msg: Message
                var file: ByteArray
                val rand = Random()
                try {
                    h.sendEmptyMessage(STATUS_CONNECTING)
                    TimeUnit.SECONDS.sleep(1)

                    h.sendEmptyMessage(STATUS_CONNECTED)
                    TimeUnit.SECONDS.sleep(1)

                    val filesCount = rand.nextInt(5)
                    if (filesCount <= 0) {
                        h.sendEmptyMessage(STATUS_DOWNLOAD_NONE)
                        TimeUnit.MILLISECONDS.sleep(1500)
                        h.sendEmptyMessage(STATUS_NONE)
                        return@Runnable
                    }
                    msg = h.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0)
                    h.sendMessage(msg)

                    for (i in 1..filesCount) {
                        file = downloadFile()
                        msg = h.obtainMessage(STATUS_DOWNLOAD_FILE, i, filesCount - i, file)
                        h.sendMessage(msg)
                    }

                    h.sendEmptyMessage(STATUS_DOWNLOAD_END)
                    TimeUnit.MILLISECONDS.sleep(1500)
                    h.sendEmptyMessage(STATUS_NONE)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }).start()
        }
    }

    @Throws(InterruptedException::class)
    private fun downloadFile(): ByteArray {
        TimeUnit.SECONDS.sleep(2)
        return ByteArray(1024)
    }

    private fun saveFile(file: ByteArray) {

    }
}
