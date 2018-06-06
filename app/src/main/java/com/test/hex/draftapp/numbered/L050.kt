package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter
import com.test.hex.draftapp.R

private const val ATTR_NAME_TEXT = "text"
private const val ATTR_NAME_PB = "pb"
private const val ATTR_NAME_LL = "ll"

class L050 : AppCompatActivity() {

    lateinit var lvSimple: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l050)

        val load = intArrayOf(41, 48, 22, 35, 30, 67, 51, 88)

        val data: MutableList<MutableMap<String, Any>> = mutableListOf()

        var map: MutableMap<String, Any>

        for(i in 0 until load.size) {
            map = mutableMapOf()
            map[ATTR_NAME_TEXT] = "Day ${i+1}. load ${load[i]}%"
            map[ATTR_NAME_PB] = load[i]
            map[ATTR_NAME_LL] = load[i]
            data.add(map)
        }
        val from = arrayOf(ATTR_NAME_TEXT, ATTR_NAME_PB, ATTR_NAME_LL)
        val to = intArrayOf(R.id.tvLoad, R.id.pbLoad, R.id.llLoad)

        val sAdapter = SimpleAdapter(this, data, R.layout.l050_item, from, to)

        sAdapter.viewBinder = MyViewBinder()

        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter
    }

    inner class MyViewBinder : SimpleAdapter.ViewBinder {
        private val red = resources.getColor(R.color.Red)
        private val orange = resources.getColor(R.color.Orange)
        private val green = resources.getColor(R.color.Green)

        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {
            val i: Int
            when (view?.id) {
                R.id.llLoad -> {
                    i = data as Int
                    when {
                        i < 40 -> view.setBackgroundColor(green)
                        i < 70 -> view.setBackgroundColor(orange)
                        else -> view.setBackgroundColor(red)
                    }
                    return true
                }
                R.id.pbLoad -> {
                    i = data as Int
                    (view as ProgressBar).progress = i
                    return true
                }
            }
            return false
        }
    }
}
