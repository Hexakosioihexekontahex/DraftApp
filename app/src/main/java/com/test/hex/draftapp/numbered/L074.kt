package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.test.hex.draftapp.R

class L074 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l074)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi: MenuItem? = menu?.add(0, 1, 0, "Preferences")
        mi?.intent = Intent(this, L074PrefActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }
}
