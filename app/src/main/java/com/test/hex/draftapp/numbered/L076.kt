package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TabHost
import android.widget.Toast
import com.test.hex.draftapp.R

class L076 : AppCompatActivity() {
    private lateinit var tabHost: TabHost
    private lateinit var tabSpec: TabHost.TabSpec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l076)

        tabHost = findViewById(R.id.tabHost)
        tabHost.setup()

//tab1
        tabSpec = tabHost.newTabSpec("tag1")
        tabSpec.setIndicator("Tab 1")
        tabSpec.setContent(R.id.tvTab1)
        tabHost.addTab(tabSpec)
//tab2
        tabSpec = tabHost.newTabSpec("tag2")
        tabSpec.setIndicator("Tab 2",
                resources.getDrawable(R.drawable.l076_tab_icon_selector))
        tabSpec.setContent(R.id.tvTab2)
        tabHost.addTab(tabSpec)
//tab3
        tabSpec = tabHost.newTabSpec("tag3")
        val v: View = layoutInflater.inflate(R.layout.l076_tab_header, null)
        tabSpec.setIndicator(v)
        tabSpec.setContent(R.id.tvTab3)
        tabHost.addTab(tabSpec)
//second tab by default
        tabHost.setCurrentTabByTag("tab2")
//onTabChangeListener
        tabHost.setOnTabChangedListener {
            Toast.makeText(baseContext, "tabId = $it", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}
