package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.test.hex.draftapp.R

class L009ClickButton : AppCompatActivity(), View.OnClickListener {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var checkBox: CheckBox
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var textView1: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l009_click_button)

        textView = findViewById(R.id.textView)
        textView.text = getString(R.string.click_any_button)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        button1.setOnClickListener {
            textView.text = "Нажата кнопка ${button1.text}"
        }

        button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                textView.text = "Нажата кнопка ${button2.text}"
            }
        }
    }

    fun onClickButton3(v: View?) {
        textView.text = "Нажата кнопка ${button3.text}"
    }
}
