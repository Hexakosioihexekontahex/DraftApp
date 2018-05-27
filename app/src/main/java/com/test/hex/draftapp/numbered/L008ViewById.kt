package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.test.hex.draftapp.R

class L008ViewById : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l008_view_by_id)

        textView = findViewById(R.id.helloWorld)
        textView.text = getString(R.string.easy_android)

        button = findViewById(R.id.mybtn)
        button.text = getString(R.string.my_button)
        button.isEnabled = false

        checkBox = findViewById(R.id.checkBox)
        checkBox.isChecked = true
    }
}
