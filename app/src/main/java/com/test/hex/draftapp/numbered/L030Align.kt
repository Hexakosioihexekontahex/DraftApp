package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R

class L030Align : AppCompatActivity(), View.OnClickListener {

    lateinit var left: Button
    lateinit var center: Button
    lateinit var right: Button
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l030_align)

        left = findViewById(R.id.left)
        center = findViewById(R.id.center)
        right = findViewById(R.id.right)

        left.setOnClickListener(this)
        center.setOnClickListener(this)
        right.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        intention = Intent()
        when (v?.id) {
            R.id.left -> {
                intention.putExtra("alignment", Gravity.LEFT)
            }
            R.id.center -> {
                intention.putExtra("alignment", Gravity.CENTER)
            }
            R.id.right -> {
                intention.putExtra("alignment", Gravity.RIGHT)
            }
        }
        setResult(Activity.RESULT_OK, intention)
        finish()
    }
}
