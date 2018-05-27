package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R
import java.text.SimpleDateFormat
import java.util.*

class L026Date : AppCompatActivity() {

    lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l026_date)

        tvDate = findViewById(R.id.date)
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
        val sDate = sdf.format(Date(System.currentTimeMillis()))
        tvDate.text = sDate
    }
}
