package com.test.hex.draftapp.numbered

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l030_color.*

class L030Color : AppCompatActivity(), View.OnClickListener {
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l030_color)
        buttonRed.setOnClickListener(this)
        buttonBlue.setOnClickListener(this)
        buttonGreen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        intention = Intent()
        when (v?.id) {
            buttonRed.id -> {
                intention.putExtra("color", Color.RED)
            }
            buttonBlue.id -> {
                intention.putExtra("color", Color.BLUE)
            }
            buttonGreen.id -> {
                intention.putExtra("color", Color.GREEN)
            }
        }
        setResult(RESULT_OK, intention)
        finish()
    }
}
