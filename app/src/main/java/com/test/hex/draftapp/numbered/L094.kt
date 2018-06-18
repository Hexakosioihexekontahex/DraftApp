package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l094.*

class L094 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l094)

        button1.setOnClickListener {
            startService(Intent("com.test.hex.draftapp.numbered.L094Service")
                    .putExtra("name", "value"))
        }
    }
}
