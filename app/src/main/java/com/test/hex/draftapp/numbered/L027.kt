package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l027.*

class L027 : AppCompatActivity(), View.OnClickListener {

    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l027)
        time.setOnClickListener(this)
        date.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            time.id -> {
                intention = Intent("com.test.hex.intent.action.time2")
                startActivity(intention)
            }
            date.id -> {
                intention = Intent("com.test.hex.intent.action.date2")
                startActivity(intention)
            }
        }
    }
}
