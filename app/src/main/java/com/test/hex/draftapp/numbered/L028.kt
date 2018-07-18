package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l028.*

class L028 : AppCompatActivity(), View.OnClickListener {

    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l028)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        intention = Intent(this, L028Activity::class.java)
        intention.putExtra("name", etName.text.toString())
        startActivity(intention)
    }
}
