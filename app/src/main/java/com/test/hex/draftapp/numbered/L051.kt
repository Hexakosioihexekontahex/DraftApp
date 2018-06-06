package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import com.test.hex.draftapp.R

private const val ATTR_NAME_TEXT = "text"
private const val ATTR_NAME_IMAGE = "image"
private const val CM_DELETE_ID = 123

class L051 : AppCompatActivity() {

    lateinit var lvSimple: ListView
    lateinit var sAdapter: SimpleAdapter
    lateinit var data: MutableList<MutableMap<String, Any>>
    lateinit var map: MutableMap<String, Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l051)

        data = mutableListOf()
        for(i in 0..4) {
            map = mutableMapOf()
            map[ATTR_NAME_TEXT] = "Note #${i + 1}"
            map[ATTR_NAME_IMAGE] = R.drawable.image001
            data.add(map)
        }

        val from = arrayOf(ATTR_NAME_TEXT, ATTR_NAME_IMAGE)
        val to = intArrayOf(R.id.tvText, R.id.ivImg)

        sAdapter = SimpleAdapter(this, data, R.layout.l051_item, from, to)
        lvSimple = findViewById(R.id.lvSimple)
        lvSimple.adapter = sAdapter
        registerForContextMenu(lvSimple)
    }

    fun onButtonClick(v: View?){
        map = mutableMapOf()
        map[ATTR_NAME_TEXT] = "Note #${data.size + 1}"
        map[ATTR_NAME_IMAGE] = R.drawable.android_kotlin2
        data.add(map)
        sAdapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, CM_DELETE_ID, 0, "Delete entry")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == CM_DELETE_ID) {
            val acmi =
                    item.menuInfo as AdapterView.AdapterContextMenuInfo
            data.removeAt(acmi.position)
            sAdapter.notifyDataSetChanged()
            return true
        }
        return super.onContextItemSelected(item)
    }
}
