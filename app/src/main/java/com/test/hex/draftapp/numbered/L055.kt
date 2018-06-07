package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L055 : AppCompatActivity() {

    lateinit var lvMain: ListView
//    lateinit var button1: Button
    private lateinit var aAdapter: ArrayAdapter<String>
    private lateinit var header1: View
    private lateinit var header2: View
    private lateinit var footer1: View
    private lateinit var footer2: View

    val data = arrayOf("one", "two", "three", "four", "five")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l055)

        findViewById<Button>(R.id.button1).setOnClickListener {
//            lvMain.removeHeaderView(header2)
//            lvMain.removeFooterView(footer2)

            var any: Any?
            val hvlAdapter: HeaderViewListAdapter = lvMain.adapter as HeaderViewListAdapter
            any = hvlAdapter.getItem(1)
            Log.d(LOG_TAG, "hvlAdapter.getItem(1) = $any")
            any = hvlAdapter.getItem(4)
            Log.d(LOG_TAG, "hvlAdapter.getItem(4) = $any")

            val alAdapter: ArrayAdapter<String> = hvlAdapter.wrappedAdapter as ArrayAdapter<String>
            any = alAdapter.getItem(1)
            Log.d(LOG_TAG, "alAdapter.getItem(1) = $any")
            any = alAdapter.getItem(4)
            Log.d(LOG_TAG, "alAdapter.getItem(4) = $any")
        }

        lvMain = findViewById(R.id.lvMain)
        aAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data)
        header1 = createHeader("header 1")
        header2 = createHeader("header 2")
        footer1 = createFooter("footer 1")
        footer2 = createFooter("footer 2")

        fillList()
    }

    private fun fillList(){
        lvMain.addHeaderView(header1)
        lvMain.addHeaderView(header2, "some text for header 2", false)
        lvMain.addFooterView(footer1)
        lvMain.addFooterView(footer2, "some text for footer 2", false)
        lvMain.adapter = aAdapter
    }

    private fun createHeader(text: String): View {
        val v = layoutInflater.inflate(R.layout.l055_header, null)
        v.findViewById<TextView>(R.id.tvText).text = text
        return v
    }

    private fun createFooter(text: String): View {
        val v = layoutInflater.inflate(R.layout.l055_footer, null)
        v.findViewById<TextView>(R.id.tvText).text = text
        return v
    }

}
