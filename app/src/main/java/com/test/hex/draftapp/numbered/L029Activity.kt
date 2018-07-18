package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l029_activity.*

class L029Activity : AppCompatActivity() {
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l029_activity)

        okbtn.setOnClickListener {
            intention = Intent()
            intention.putExtra("name", etName.text.toString())
            setResult(Activity.RESULT_OK, intention)
            finish()
        }
    }
}
