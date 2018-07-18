package com.test.hex.draftapp.numbered

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l029.*

class L029 : AppCompatActivity() {
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l029)

        button.setOnClickListener {
            intention = Intent(this, L029Activity::class.java)
            startActivityForResult(intention, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) {
            return
        }
        textView.text = "Your name is ${data.getStringExtra("name")}"
    }
}
