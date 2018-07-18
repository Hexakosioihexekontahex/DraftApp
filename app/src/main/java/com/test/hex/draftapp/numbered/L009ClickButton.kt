package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l009_click_button.*

class L009ClickButton : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l009_click_button)

        textView.text = getString(R.string.click_any_button)

        button1.setOnClickListener {
            textView.text = "Нажата кнопка ${button1.text}"
        }

        button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            button2.id -> {
                textView.text = "Нажата кнопка ${button2.text}"
            }
        }
    }

    fun onClickButton3(v: View?) {
        textView.text = "Нажата кнопка ${button3.text}"
    }
}
