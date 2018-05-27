package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.test.hex.draftapp.R

class L029 : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l029)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
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
