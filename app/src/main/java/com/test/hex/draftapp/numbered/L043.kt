package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L043 : AppCompatActivity() {

    lateinit var lvMain: ListView
    lateinit var names: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l043)

        lvMain = findViewById(R.id.lvMain)
        lvMain.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        lvMain.adapter = ArrayAdapter.createFromResource(
                this,
                R.array.names,
                android.R.layout.simple_list_item_multiple_choice
        )

        names = resources.getStringArray(R.array.names)

        findViewById<Button>(R.id.btnChecked).setOnClickListener{
            Log.d(LOG_TAG, "checked: ")
            val spbArray = lvMain.checkedItemPositions
            for (i in 0 until spbArray.size()) {
                val key = spbArray.keyAt(i)
                if (spbArray[key]) {
                    Log.d(LOG_TAG, names[key])
                }
            }
        }
    }
}
