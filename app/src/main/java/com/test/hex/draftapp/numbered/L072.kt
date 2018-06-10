package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.test.hex.draftapp.R

class L072 : AppCompatActivity() {

    lateinit var tvInfo: TextView
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l072)

        tvInfo = findViewById(R.id.tvInfo)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        val listValue = sp.getString("list", "wasn't selected")
        tvInfo.text = "Value of list - $listValue"
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi: MenuItem? = menu?.add(0, 1, 0, "Preferences")
        mi?.intent = Intent(this, L072PrefActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }
}
