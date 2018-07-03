package com.test.hex.draftapp.numbered

import android.app.ListFragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.test.hex.draftapp.R

class L109 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l109)
    }
}

class L109ListFragment : ListFragment() {
    val data = arrayOf("one", "two", "three", "four")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val aAdapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, data)

        listAdapter = aAdapter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l109_fragment, container, false)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Toast.makeText(activity, "position$position", Toast.LENGTH_SHORT).show()
    }
}
