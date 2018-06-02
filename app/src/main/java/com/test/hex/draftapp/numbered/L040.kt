package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L040 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l040)

        val linearLayout1 = findViewById<LinearLayout>(R.id.linearLayout1)

        val view1 = layoutInflater.inflate(R.layout.l040_textview,
                linearLayout1, true)
        val lp1 = view1.layoutParams

        Log.d(LOG_TAG, "Class of view1: ${view1::class.java}")
        Log.d(LOG_TAG, "Class of layoutParams of view1: ${lp1::class.java}")
        Log.d(LOG_TAG, "Text of view1: ${(view1 as? TextView)?.text}")

        val relativeLayout1 = findViewById<RelativeLayout>(R.id.relativeLayout1)
        val view2 = layoutInflater.inflate(R.layout.l040_textview,
                relativeLayout1, true)
        val lp2 = view2.layoutParams

        Log.d(LOG_TAG, "Class of view2: ${view2::class.java}")
        Log.d(LOG_TAG, "Class of layoutParams of view2: ${lp2::class.java}")
        Log.d(LOG_TAG, "Text of view2: ${(view2 as? TextView)?.text}")
    }
}
