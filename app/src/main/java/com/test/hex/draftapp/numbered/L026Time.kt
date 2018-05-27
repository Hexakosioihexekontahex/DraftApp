package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R
import java.text.SimpleDateFormat
import java.util.*

class L026Time : AppCompatActivity() {

    lateinit var tvTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l026_time)

        tvTime = findViewById(R.id.time)
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
        val sTime = sdf.format(Date(System.currentTimeMillis()))
        tvTime.text = sTime
    }
}
