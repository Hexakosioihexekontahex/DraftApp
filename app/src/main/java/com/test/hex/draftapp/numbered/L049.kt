package com.test.hex.draftapp.numbered

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.test.hex.draftapp.R

private const val ATTR_NAME_TEXT = "text"
private const val ATTR_NAME_VALUE = "value"
private const val ATTR_NAME_IMAGE = "image"

private const val POSITIVE = android.R.drawable.arrow_up_float
private const val NEGATIVE = android.R.drawable.arrow_down_float

class L049 : AppCompatActivity() {

    lateinit var lvSimple: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l049)

        val values = intArrayOf(8, 4, -3, 2, -5, 0, 3, -6, 1, -1)
        val data = mutableListOf<Map<String, Any>>()
        var map = mutableMapOf<String, Any>()
//        var img = 0
        for(i in 0 until values.size) {
            map = mutableMapOf()
            map[ATTR_NAME_TEXT] = "Day ${i+1}"
            map[ATTR_NAME_VALUE] = values[i]
//            img = if (values[i] == 0) {
//                0
//            } else {
//                if (values[i] > 0) POSITIVE else NEGATIVE
//            }
            map[ATTR_NAME_IMAGE] = if (values[i] == 0) {
                0
            } else {
                if (values[i] > 0) POSITIVE else NEGATIVE
            }
            data.add(map)
        }
        val from = arrayOf(ATTR_NAME_TEXT, ATTR_NAME_VALUE, ATTR_NAME_IMAGE)
        val to = intArrayOf(R.id.tvText, R.id.tvValue, R.id.ivImg)

        val sAdapter = MySimpleAdapter(this, data, R.layout.l049_item, from, to)
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter


    }

    class MySimpleAdapter(context: Context,
                          data: List<Map<String, *>>,
                          resource: Int,
                          from: Array<String>,
                          to: IntArray) : SimpleAdapter(context, data, resource, from, to) {
        override fun setViewText(v: TextView?, text: String?) {
            super.setViewText(v, text)
            if (v?.id == R.id.tvValue) {
//                val i = Integer.parseInt(text)
                if (Integer.parseInt(text) < 0) {
                    v.setTextColor(Color.RED)
                } else {
                    v.setTextColor(Color.GREEN)
                }
            }
        }

        override fun setViewImage(v: ImageView?, value: Int) {
            super.setViewImage(v, value)

            if (value == NEGATIVE) {
                v?.setBackgroundColor(Color.RED)
            } else if (value == POSITIVE) {
                v?.setBackgroundColor(Color.GREEN)
            }
        }
    }
}
