package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.PreferenceActivity
import android.preference.PreferenceCategory
import com.test.hex.draftapp.R

class L073PrefActivity : PreferenceActivity() {

    private lateinit var chb3: CheckBoxPreference
    private lateinit var categ2: PreferenceCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref73)

        chb3 = findPreference("chb3") as CheckBoxPreference
        categ2 = findPreference("categ2") as PreferenceCategory
        categ2.isEnabled = chb3.isChecked

        chb3.setOnPreferenceClickListener {
            categ2.isEnabled = chb3.isChecked
            false
        }
    }
}