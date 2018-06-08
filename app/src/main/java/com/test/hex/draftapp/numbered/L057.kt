package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import com.test.hex.draftapp.R

class L057 : AppCompatActivity() {
//    val data = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
    val data: Array<String> = ('a'..'k').map { it + "" }.toTypedArray()

    lateinit var gvMain: GridView
    private lateinit var aAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l057)

        aAdapter = ArrayAdapter(this, R.layout.l057_item, R.id.tvText, data)
        gvMain = findViewById(R.id.gvMain)
        gvMain.adapter = aAdapter
        adjustGridView()
    }

    private fun adjustGridView(){
        gvMain.apply {
            numColumns = GridView.AUTO_FIT
//            numColumns = 3
            columnWidth = 80
            verticalSpacing = 5
            horizontalSpacing = 5
//            stretchMode = GridView.NO_STRETCH
//            stretchMode = GridView.STRETCH_COLUMN_WIDTH //default
//            stretchMode = GridView.STRETCH_SPACING
            stretchMode = GridView.STRETCH_SPACING_UNIFORM
        }
    }
}
