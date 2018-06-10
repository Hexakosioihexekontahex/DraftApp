package com.test.hex.draftapp.numbered

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.test.hex.draftapp.R

class L071 : AppCompatActivity() {
    lateinit var tvInfo: TextView
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l071)

        tvInfo = findViewById(R.id.tvInfo)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
//        sp.edit().clear()
    }

    override fun onResume() {
        val notif: Boolean = sp.getBoolean("notif", false)
        val address: String = sp.getString("address", "")
        val text: String = "Notifications are ${if (notif) "enabled" else "disabled"}" +
                if (notif && address.isNotEmpty()) ", address = $address" else ""
        tvInfo.text = text
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi: MenuItem? = menu?.add(0, 1, 0, "Preferences")
        mi?.intent = Intent(this, L071PrefActivity::class.java)
        return super.onCreateOptionsMenu(menu)
    }
}
