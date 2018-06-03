package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.test.hex.draftapp.R

class L042 : AppCompatActivity() {

    private val names = arrayOf("Иван", "Мария", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь", "Иван", "Мария", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь")

    lateinit var lvMain: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l042)

        lvMain = findViewById(R.id.lvMain)

        val adapter = ArrayAdapter<String>(this,
                R.layout.l042_list_item,
                names)

        lvMain.adapter = adapter
    }
}
