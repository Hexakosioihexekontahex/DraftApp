package com.test.hex.draftapp.numbered

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l031.*

class L031 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l031)

        webBtn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://developer.android.com")))
        }
        mapBtn.setOnClickListener {
            startActivity(Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("geo:52.479761, 62.185661")
            })
        }
        callBtn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:12345")
            })
        }
    }
}
