package com.test.hex.draftapp.numbered

import android.app.TabActivity
import android.content.Intent
import android.os.Bundle
import com.test.hex.draftapp.R

class L077 : TabActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l077)

        //1
        tabHost.addTab(tabHost.newTabSpec("tag1").apply {
            setIndicator("Tab 1")
            setContent(Intent(this@L077, L077First::class.java))
        })
        //2
        tabHost.addTab(tabHost.newTabSpec("tag2").apply {
            setIndicator("Tab 2")
            setContent(Intent(this@L077, L077Second::class.java))
        })
    }
}
