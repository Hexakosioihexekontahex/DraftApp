package com.test.hex.draftapp.numbered

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.test.hex.draftapp.R

class L041 : AppCompatActivity() {

    private val name = arrayOf("Иван", "Мария", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь")
    private val position = arrayOf("Программер", "Бухгалтер", "Программер", "Программер", "Бухгалтер",
            "Директор", "Программер", "Охранник")
    private val salary = arrayOf(13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000)
    private val colors = arrayOf(
            Color.parseColor("#559966CC"),
            Color.parseColor("#55336699"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l041)

        val linLayout = findViewById<LinearLayout>(R.id.linLayout)

        for (y in 0..1) {// для большого экрана
            for(i in 0 until name.size) {
                Log.d("myLogs", "i = $i")
                val item = layoutInflater.inflate(R.layout.l041_item, linLayout, false)
                item.findViewById<TextView>(R.id.tvName).text = name[i]
                item.findViewById<TextView>(R.id.tvPosition).text = position[i]
                item.findViewById<TextView>(R.id.tvSalary).text = salary[i].toString()
                item.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                item.setBackgroundColor(colors[i%2])
                linLayout.addView(item)
            }
        }
    }
}
