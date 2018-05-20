package com.test.hex.draftapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(R.layout.l008_view_by_id)
    }

    private fun useLayout(layout: Int){
        when (layout) {
            R.layout.l008_view_by_id -> {
                setContentView(layout)

                textView = findViewById(R.id.helloWorld)
                textView.text = getString(R.string.easy_android)

                button = findViewById(R.id.mybtn)
                button.text = getString(R.string.my_button)
                button.isEnabled = false

                checkBox = findViewById(R.id.checkBox)
                checkBox.isChecked = true
            }

            else -> setContentView(layout)
        }
    }

}
