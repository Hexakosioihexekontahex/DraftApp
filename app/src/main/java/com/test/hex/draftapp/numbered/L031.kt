package com.test.hex.draftapp.numbered

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.test.hex.draftapp.R

class L031 : AppCompatActivity() {

    lateinit var webBtn: Button
    lateinit var mapBtn: Button
    lateinit var callBtn: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l031)

        webBtn = findViewById(R.id.button1)
        mapBtn = findViewById(R.id.button2)
        callBtn = findViewById(R.id.button3)

        webBtn.setOnClickListener {
            intention = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://developer.android.com"))
            startActivity(intention)
        }
        mapBtn.setOnClickListener {
            intention = Intent()
            intention.action = Intent.ACTION_VIEW
            intention.data = Uri.parse("geo:52.479761, 62.185661")
            startActivity(intention)
        }
        callBtn.setOnClickListener {
            intention = Intent(Intent.ACTION_DIAL)
            intention.data = Uri.parse("tel:12345")
            startActivity(intention)
        }
    }
}
