package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l028_activity.*

class L028Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l028_activity)

        textView.text = intent.getStringExtra("name")
    }
}
