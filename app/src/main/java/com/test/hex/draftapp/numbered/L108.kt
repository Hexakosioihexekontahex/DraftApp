package com.test.hex.draftapp.numbered

import android.app.ActionBar
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L108 : AppCompatActivity(), ActionBar.TabListener {
    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        Log.d(LOG_TAG, "${tab?.text} onTabReselected")
    }

    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        Log.d(LOG_TAG, "${tab?.text} onTabUnselected")
    }

    override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        Log.d(LOG_TAG, "${tab?.text} onTabSelected")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l108)

        actionBar?.apply {
            navigationMode = ActionBar.NAVIGATION_MODE_TABS
            addTab(newTab().apply {
                text = "tab1"
                setTabListener(this@L108)
            })
            addTab(newTab().apply {
                text = "tab2"
                setTabListener(this@L108)
            })
        }
    }
}
