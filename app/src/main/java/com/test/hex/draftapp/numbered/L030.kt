package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l030.*

const val REQUEST_CODE_COLOR = 1
const val REQUEST_CODE_ALIGN = 2

class L030 : AppCompatActivity() {
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l030)

        buttonColor.setOnClickListener {
            intention = Intent(this, L030Color::class.java)
            startActivityForResult(intention, REQUEST_CODE_COLOR)
        }
        buttonAlign.setOnClickListener {
            intention = Intent(this, L030Align::class.java)
            startActivityForResult(intention, REQUEST_CODE_ALIGN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_COLOR -> {
                    val color = data?.getIntExtra("color", Color.WHITE)
                    tvText.setTextColor(color!!)
                }
                REQUEST_CODE_ALIGN -> {
                    val gravity = data?.getIntExtra("alignment", Gravity.LEFT)
                    tvText.gravity = gravity!!
                }
                else -> {
                    Toast.makeText(this,
                            "Something went wrong",
                            Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }
    }
}
