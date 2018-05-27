package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.test.hex.draftapp.R

class L016 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        val linLayoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(linearLayout, linLayoutParams)

        val lpView = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val textView = TextView(this)
        textView.text = getString(R.string.text)
        textView.layoutParams = lpView
        linearLayout.addView(textView)

        val leftMarginParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        leftMarginParams.leftMargin = 50

        val button = Button(this)
        button.text = getString(R.string.button)
        linearLayout.addView(button, leftMarginParams)

        val gravityParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        gravityParams.gravity = Gravity.CENTER

        val button1 = Button(this)
        button1.text = getString(R.string.button1)
        linearLayout.addView(button1, gravityParams)
    }
}
