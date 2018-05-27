package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import com.test.hex.draftapp.R

class L018DynamicWeight : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    lateinit var sbWeight: SeekBar
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var lParams1: LinearLayout.LayoutParams
    lateinit var lParams2: LinearLayout.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l018_dynamic_weight)

        sbWeight = findViewById(R.id.sbWeight)
        sbWeight.setOnSeekBarChangeListener(this)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        lParams1 = button1.layoutParams as LinearLayout.LayoutParams
        lParams2 = button2.layoutParams as LinearLayout.LayoutParams
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val value = seekBar?.max!! - progress

        lParams1.weight = progress.toFloat()
        lParams2.weight = value.toFloat()

        button1.text = progress.toString()
        button2.text = value.toString()
        button1.layoutParams = lParams2
        button2.layoutParams = lParams1
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}
