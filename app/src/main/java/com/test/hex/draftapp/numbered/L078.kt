package com.test.hex.draftapp.numbered

import android.app.TabActivity
import android.os.Bundle
import android.widget.TabHost
import android.widget.TextView
import com.test.hex.draftapp.R

class L078 : TabActivity() {
    private val TABS_TAG_1 = "Tab 1"
    private val TABS_TAG_2 = "Tab 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l078)

        tabHost.addTab(tabHost.newTabSpec(TABS_TAG_1).apply {
            setContent(tabFactory)
            setIndicator("Tab 1")
        })
        tabHost.addTab(tabHost.newTabSpec(TABS_TAG_2).apply {
            setContent(tabFactory)
            setIndicator("Tab 2")
        })
    }

    private val tabFactory = TabHost.TabContentFactory {
        when (it) {
            TABS_TAG_1 -> layoutInflater.inflate(R.layout.l078_tab, null)
            TABS_TAG_2 -> {
                val tv = TextView(this@L078)
                tv.text = getString(R.string.l078_text2)
                tv
            }
            else -> null
        }
    }
}
