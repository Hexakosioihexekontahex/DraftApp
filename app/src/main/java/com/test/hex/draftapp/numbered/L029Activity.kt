package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.test.hex.draftapp.R

class L029Activity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var okbtn: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l029_activity)

        etName = findViewById(R.id.etName)
        okbtn = findViewById(R.id.okbtn)
        okbtn.setOnClickListener {
            intention = Intent()
            intention.putExtra("name", etName.text.toString())
            setResult(Activity.RESULT_OK, intention)
            finish()
        }
    }
}
