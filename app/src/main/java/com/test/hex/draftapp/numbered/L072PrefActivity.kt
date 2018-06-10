package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.preference.PreferenceActivity
import com.test.hex.draftapp.R

class L072PrefActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref72)
    }
}
