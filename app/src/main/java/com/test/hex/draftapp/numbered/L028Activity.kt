package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R

class L028Activity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l028_activity)

        textView = findViewById(R.id.textView)

        textView.text = intent.getStringExtra("name")
    }
}
