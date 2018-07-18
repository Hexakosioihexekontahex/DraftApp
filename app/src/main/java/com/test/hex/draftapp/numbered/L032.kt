package com.test.hex.draftapp.numbered

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l032.*

class L032 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l032)

        btnWeb.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://developer.android.com")))
        }
    }
}
