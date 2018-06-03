package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L044 : AppCompatActivity() {

    lateinit var lvMain: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l044)

        lvMain = findViewById(R.id.lvMain)
        lvMain.adapter = ArrayAdapter.createFromResource(
                this,
                R.array.names,
                android.R.layout.simple_list_item_1
        )
        lvMain.setOnItemClickListener { _, _, position, id ->
            Log.d(LOG_TAG, "itemClick position = $position, id = $id")
            val toast = Toast.makeText(this,
                    "itemClick position = $position, id = $id",
                    Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM, 0, 0)
            toast.show()
        }
        lvMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d(LOG_TAG, "itemSelect: position = $position, id = $id")
                val toast = Toast.makeText(this@L044,
                        "itemSelect: position = $position, id = $id",
                        Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d(LOG_TAG, "itemSelect: nothing")
                val toast = Toast.makeText(this@L044,
                        "itemSelect: nothing",
                        Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
        }
        lvMain.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                Log.d(LOG_TAG, "scrollState = $scrollState")
                val toast = Toast.makeText(this@L044,
                        "scrollState = $scrollState",
                        Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                Log.d(LOG_TAG, "scroll firstVisibleItem = $firstVisibleItem," +
                        " visibleItemCount = $visibleItemCount," +
                        " totalItemCount = $totalItemCount")
                val toast = Toast.makeText(this@L044,
                        "scroll firstVisibleItem = $firstVisibleItem," +
                                " visibleItemCount = $visibleItemCount," +
                                " totalItemCount = $totalItemCount",
                        Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 0)
                toast.show()
            }
        })
    }
}
