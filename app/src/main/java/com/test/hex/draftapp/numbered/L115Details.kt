package com.test.hex.draftapp.numbered

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class L115Details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish()
            return
        }

        if (savedInstanceState == null) {
            val details = newInstance(
                    intent.getIntExtra("position", 0)
            )
            supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, details).commit()
        }
    }
}
