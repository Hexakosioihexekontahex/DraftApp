package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l027_datetime.*
import java.text.SimpleDateFormat
import java.util.*

class L027DateTime : AppCompatActivity() {

    lateinit var format: String
    lateinit var textInfo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l027_datetime)

        if (intent.action == "com.test.hex.intent.action.date2")  {
            format = "dd.MM.yyyy"
            textInfo = "Date:"
        } else
        if (intent.action == "com.test.hex.intent.action.time2")  {
            format = "HH:mm:ss"
            textInfo = "Time:"
        }

        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        val dateTime = "$textInfo ${sdf.format(Date(System.currentTimeMillis()))}"
        tvDateTime.text = dateTime
    }
}
