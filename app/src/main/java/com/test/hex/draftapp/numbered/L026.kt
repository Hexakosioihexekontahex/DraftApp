package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l026.*

class L026 : AppCompatActivity(), View.OnClickListener {

    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l026)

        buttonTime.setOnClickListener(this)
        buttonDate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            buttonTime.id -> {
                intention = Intent("com.test.hex.intent.action.time")
                startActivity(intention)
            }
            buttonDate.id -> {
                intention = Intent("com.test.hex.intent.action.date")
                startActivity(intention)
            }
        }
    }
}
