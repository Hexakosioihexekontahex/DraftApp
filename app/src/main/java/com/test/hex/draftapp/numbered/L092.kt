package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l092.*

class L092 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l092)

        button1.setOnClickListener {
            startService(Intent(this, L092Service::class.java))
        }
        button2.setOnClickListener {
            stopService(Intent(this, L092Service::class.java))
        }
    }
}
