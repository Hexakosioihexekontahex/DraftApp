package com.test.hex.draftapp.numbered

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R

class L030Color : AppCompatActivity(), View.OnClickListener {

    lateinit var buttonRed: Button
    lateinit var buttonBlue: Button
    lateinit var buttonGreen: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l030_color)

        buttonRed = findViewById(R.id.buttonRed)
        buttonBlue = findViewById(R.id.buttonBlue)
        buttonGreen = findViewById(R.id.buttonGreen)
        buttonRed.setOnClickListener(this)
        buttonBlue.setOnClickListener(this)
        buttonGreen.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        intention = Intent()
        when (v?.id) {
            R.id.buttonRed -> {
                intention.putExtra("color", Color.RED)
            }
            R.id.buttonBlue -> {
                intention.putExtra("color", Color.BLUE)
            }
            R.id.buttonGreen -> {
                intention.putExtra("color", Color.GREEN)
            }
        }
        setResult(RESULT_OK, intention)
        finish()
    }
}
