package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import com.test.hex.draftapp.R

private const val ATTRIBUTE_NAME_TEXT = "text"
private const val ATTRIBUTE_NAME_CHECKED = "checked"
private const val ATTRIBUTE_NAME_IMAGE = "image"

class L048 : AppCompatActivity() {

//    lateinit var lvSimple: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l048)
        //data arrays
        val texts = arrayOf("sometext 1", "sometext 2", "sometext 3",
                "sometext 4", "sometext 5")
        val checked = booleanArrayOf(true, false, false, true, false)
        val img = R.drawable.android_kotlin1
        //adapt data to adapter
        val data = mutableListOf<MutableMap<String, Any>>()
        var map: MutableMap<String, Any>
        for (i in 0 until texts.size) {
            map = mutableMapOf()
            map[ATTRIBUTE_NAME_TEXT] = texts[i]
            map[ATTRIBUTE_NAME_CHECKED] = checked[i]
            map[ATTRIBUTE_NAME_IMAGE] = img
            data.add(map)
        }

        val from = arrayOf(ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE)
        val to = intArrayOf(R.id.tvText, R.id.cbChecked, R.id.ivImg)

//        val sAdapter = SimpleAdapter(this, data, R.layout.l048_item,
//                from, to)
//
//        lvSimple = findViewById(R.id.lvSimple)
//        lvSimple.adapter = sAdapter

        findViewById<ListView>(R.id.lvSimple).adapter = SimpleAdapter(
                this, data, R.layout.l048_item, from, to
        )
    }
}
