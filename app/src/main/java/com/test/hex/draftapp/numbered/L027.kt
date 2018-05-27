package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R

class L027 : AppCompatActivity(), View.OnClickListener {

    lateinit var timeButton: Button
    lateinit var dateButton: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l027)

        timeButton = findViewById(R.id.time)
        dateButton = findViewById(R.id.date)
        timeButton.setOnClickListener(this)
        dateButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.time -> {
                intention = Intent("com.test.hex.intent.action.time2")
                startActivity(intention)
            }
            R.id.date -> {
                intention = Intent("com.test.hex.intent.action.date2")
                startActivity(intention)
            }
        }
    }
}
