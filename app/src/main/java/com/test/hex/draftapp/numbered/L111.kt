package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import com.test.hex.draftapp.R

class L111 : PreferenceActivity() {
    override fun onBuildHeaders(target: MutableList<Header>?) {
        loadHeadersFromResource(R.xml.pref111_head, target)
    }

    override fun isValidFragment(fragmentName: String?): Boolean {
        return L111PreferenceFragment1::class.java.name == fragmentName ||
                L111PreferenceFragment2::class.java.name == fragmentName
    }
}

class L111PreferenceFragment1 : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref111_1)
    }
}

class L111PreferenceFragment2 : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref111_2)
    }
}
