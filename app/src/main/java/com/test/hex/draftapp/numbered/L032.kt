package com.test.hex.draftapp.numbered

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.test.hex.draftapp.R

class L032 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l032)

        findViewById<Button>(R.id.btnWeb).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://developer.android.com")))
        }
    }
}
