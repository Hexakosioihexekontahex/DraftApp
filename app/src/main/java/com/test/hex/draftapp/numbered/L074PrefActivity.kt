package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.preference.*
import com.test.hex.draftapp.R

class L074PrefActivity : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* content of previous lessen - l073.xml - in comments */
//<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
        val rootScreen = preferenceManager.createPreferenceScreen(this)
        preferenceScreen = rootScreen
//    <CheckBoxPreference
        val chb1 = CheckBoxPreference(this)
//        android:key="chb1"
        chb1.key = "chb1"
//        android:summaryOff="Description of checkbox 1 off"
        chb1.summaryOff = "Description of checkbox 1 off"
//        android:summaryOn="Description of checkbox 1 on"
        chb1.summaryOn = "Description of checkbox 1 on"
//        android:title="CheckBox 1" />
        chb1.title = "CheckBox 1"
        rootScreen.addPreference(chb1)
//    <ListPreference
        val list = ListPreference(this)
//        android:key="list"
        list.key = "list"
//        android:summary="Description of list"
        list.summary = "Description of list"
//        android:title="List"
        list.title = "List"
//        android:dependency="chb1"
//        list.dependency = "chb1" //to the end
//        android:entries="@array/entries"
        list.setEntries(R.array.entries)
//        android:entryValues="@array/entry_values" />
        list.setEntryValues(R.array.entry_values)
        rootScreen.addPreference(list)
//    <CheckBoxPreference
        val chb2 = CheckBoxPreference(this)
//        android:key="chb2"
        chb2.key = "chb2"
//        android:summary="Description of checkbox 2"
        chb2.summary = "Description of checkbox 2"
//        android:title="CheckBox 2" />
        chb2.title = "CheckBox 2"
        rootScreen.addPreference(chb2)
//    <PreferenceScreen
        val screen = preferenceManager.createPreferenceScreen(this)
//        android:key="screen"
        screen.key = "screen"
//        android:summary="Description of screen"
        screen.summary = "Description of screen"
//        android:title="Screen"
        screen.title = "Screen"
//        android:dependency="chb2">
//        screen.dependency = "chb2" //to the end
        rootScreen.addPreference(screen)
//        <CheckBoxPreference
        val chb3 = CheckBoxPreference(this)
//            android:key="chb3"
        chb3.key = "chb3"
//            android:summary="Description of checkbox 3"
        chb3.summary = "Description of checkbox 3"
//            android:title="CheckBox 3"/>
        chb3.title = "CheckBox 3"
        screen.addPreference(chb3)
//        <PreferenceCategory
        val categ1 = PreferenceCategory(this)
//            android:key="categ1"
        categ1.key = "categ1"
//            android:summary="Description of category 1"
        categ1.summary = "Description of category 1"
//            android:title="Category 1">
        categ1.title = "Category 1"
        screen.addPreference(categ1)
//            <CheckBoxPreference
        val chb4 = CheckBoxPreference(this)
//                android:key="chb4"
        chb4.key = "chb4"
//                android:summary="Description of checkbox 4"
        chb4.summary = "Description of checkbox 4"
//                android:title="CheckBox 4" />
        chb4.title = "CheckBox 4"
        categ1.addPreference(chb4)
//        </PreferenceCategory>
//        <PreferenceCategory
        val categ2 = PreferenceCategory(this)
//            android:key="categ2"
        categ2.key = "categ2"
//            android:summary="Description of category 2"
        categ2.summary = "Description of category 2"
//            android:title="Category 2">
        categ2.title = "Category 2"
        screen.addPreference(categ2)
//            <CheckBoxPreference
        val chb5 = CheckBoxPreference(this)
//                android:key="chb5"
        chb5.key = "chb5"
//                android:summary="Description of checkbox 5"
        chb5.summary = "Description of checkbox 5"
//                android:title="CheckBox 5" />
        chb5.title = "CheckBox 5"
        categ2.addPreference(chb5)
//            <CheckBoxPreference
        val chb6 = CheckBoxPreference(this)
//                android:key="chb6"
        chb6.key = "chb6"
//                android:summary="Description of checkbox 6"
        chb6.summary = "Description of checkbox 6"
//                android:title="CheckBox 6" />
        chb6.title = "CheckBox 6"
        categ2.addPreference(chb6)
//        </PreferenceCategory>
        rootScreen.addPreference(screen)
//    </PreferenceScreen>
        list.dependency = "chb1"
        screen.dependency = "chb2"
//</PreferenceScreen>

        categ2.isEnabled = chb3.isChecked
        chb3.setOnPreferenceClickListener {
            categ2.isEnabled = chb3.isChecked
            false
        }
    }
}