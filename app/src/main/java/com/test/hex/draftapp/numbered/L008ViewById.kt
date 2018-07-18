package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l008_view_by_id.*

class L008ViewById : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l008_view_by_id)

        helloWorld.text = getString(R.string.easy_android)

        mybtn.text = getString(R.string.my_button)
        mybtn.isEnabled = false

        checkBox.isChecked = true
    }
}
