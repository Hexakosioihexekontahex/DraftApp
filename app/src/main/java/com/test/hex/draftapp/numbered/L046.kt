package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L046 : AppCompatActivity() {

    lateinit var elvMain: ExpandableListView
    lateinit var ah: L046AdapterHelper
    lateinit var adapter: SimpleExpandableListAdapter
    lateinit var tvInfo: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l046)

        tvInfo = findViewById(R.id.tvInfo)

        ah = L046AdapterHelper(this)
        adapter = ah.getAdapter()

        elvMain = findViewById(R.id.elvMain)
        elvMain.setAdapter(adapter)

        elvMain.setOnChildClickListener { _, _, groupPosition, childPosition, id ->
            Log.d(LOG_TAG, "onChildClick groupPosition=$groupPosition childPosition=$childPosition id=$id")
            tvInfo.text = ah.getGroupChildText(groupPosition, childPosition)
            false
        }
        elvMain.setOnGroupClickListener { _, _, groupPosition ,id ->
            Log.d(LOG_TAG, "onGroupClick groupPosition=$groupPosition id=$id")
//            groupPosition == 1
            false
        }
        elvMain.setOnGroupCollapseListener {
            Log.d(LOG_TAG, "onGroupCollapse groupPosition = $it")
            tvInfo.text = "Collapse ${ah.getGroupText(it)}"
        }
        elvMain.setOnGroupExpandListener {
            Log.d(LOG_TAG, "onGroupExpand groupPosition = $it")
            tvInfo.text = "Expand ${ah.getGroupText(it)}"
        }

        elvMain.expandGroup(2)
    }
}

const val ATTR_GROUP_NAME = "groupName"
const val ATTR_PHONE_NAME = "phoneName"

class L046AdapterHelper(var ctx: Context) {
    //company names (groups)
    private val groups = arrayOf("HTC", "Samsung", "LG")
    //phone names (elements)
    private val phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero", "One")
    private val phonesSamsung = arrayOf("Galaxy S II", "Galaxy S3", "Galaxy Nexus", " Wave", "Galaxy Note")
    private val phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    //group collection
    private lateinit var groupData: MutableList<MutableMap<String, String>>
    //child collection
    private lateinit var childDataItem: MutableList<MutableMap<String, String>>
    //common collection
    private lateinit var childData: MutableList<MutableList<MutableMap<String, String>>>
    //attributes of group or element
    private lateinit var map: MutableMap<String, String>

    private lateinit var adapter: SimpleExpandableListAdapter

    fun getAdapter(): SimpleExpandableListAdapter {
        groupData = mutableListOf()
        for (group in groups) {
            map = mutableMapOf()
            map[ATTR_GROUP_NAME] = group
            groupData.add(map)
        }
        val groupFrom = arrayOf(ATTR_GROUP_NAME)
        val groupTo = intArrayOf(android.R.id.text1)

        childData = mutableListOf()

        childDataItem = mutableListOf()
        for (phone in phonesHTC) {
            map = mutableMapOf()
            map[ATTR_PHONE_NAME] = phone
            childDataItem.add(map)
        }
        childData.add(childDataItem)

        childDataItem = mutableListOf()
        for (phone in phonesSamsung) {
            map = mutableMapOf()
            map[ATTR_PHONE_NAME] = phone
            childDataItem.add(map)
        }
        childData.add(childDataItem)

        childDataItem = mutableListOf()
        for (phone in phonesLG) {
            map = mutableMapOf()
            map[ATTR_PHONE_NAME] = phone
            childDataItem.add(map)
        }
        childData.add(childDataItem)

        val childFrom = arrayOf(ATTR_PHONE_NAME)
        val childTo = intArrayOf(android.R.id.text1)

        adapter = SimpleExpandableListAdapter(
                ctx, groupData, android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1, childFrom, childTo
        )

        return adapter
    }

    @Suppress("UNCHECKED_CAST")
    fun getGroupText(groupPos: Int) =
            (adapter.getGroup(groupPos) as Map<String, String>)[ATTR_GROUP_NAME]

    @Suppress("UNCHECKED_CAST")
    fun getChildText(groupPos: Int, childPos: Int) =
            (adapter.getChild(groupPos, childPos) as Map<String, String>)[ATTR_PHONE_NAME]

    @Suppress("UNCHECKED_CAST")
    fun getGroupChildText(groupPos: Int, childPos: Int) =
            "${getGroupText(groupPos)} ${getChildText(groupPos, childPos)}"

}
