package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l030_align.*

class L030Align : AppCompatActivity(), View.OnClickListener {
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l030_align)

        left.setOnClickListener(this)
        center.setOnClickListener(this)
        right.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        intention = Intent()
        when (v?.id) {
            left.id -> {
                intention.putExtra("alignment", Gravity.LEFT)
            }
            center.id -> {
                intention.putExtra("alignment", Gravity.CENTER)
            }
            right.id -> {
                intention.putExtra("alignment", Gravity.RIGHT)
            }
        }
        setResult(Activity.RESULT_OK, intention)
        finish()
    }
}
