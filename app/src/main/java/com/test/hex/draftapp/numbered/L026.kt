package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R

class L026 : AppCompatActivity(), View.OnClickListener {

    private lateinit var buttonTime: Button
    private lateinit var buttonDate: Button
    lateinit var intention: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l026)

        buttonTime = findViewById(R.id.button1)
        buttonDate = findViewById(R.id.button2)
        buttonTime.setOnClickListener(this)
        buttonDate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                intention = Intent("com.test.hex.intent.action.time")
                startActivity(intention)
            }
            R.id.button2 -> {
                intention = Intent("com.test.hex.intent.action.date")
                startActivity(intention)
            }
        }
    }
}
