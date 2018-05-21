package com.test.hex.draftapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(R.layout.l009_click_button)
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
            R.layout.l009_click_button -> {
                setContentView(layout)

                textView = findViewById(R.id.textView)
                textView.text = getString(R.string.something)
                button1 = findViewById(R.id.button1)
                button2 = findViewById(R.id.button2)
                button3 = findViewById(R.id.button3)

                button1.setOnClickListener {
                    textView.text = "Нажата кнопка ${button1.text}"
                }

                button2.setOnClickListener(this)
            }

            else -> setContentView(layout)
        }
    }

    override fun onClick(v: View?) {
        textView.text = "Нажата кнопка ${button2.text}"
    }

    fun onClickButton3(view: View) {
        textView.text = "Нажата кнопка ${button3.text}"
    }
}
